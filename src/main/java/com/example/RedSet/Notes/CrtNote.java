package com.example.RedSet.Notes;

import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.Lattice.encodeDecode;
import com.example.RedSet.MAIN;
import com.example.RedSet.SceneTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CrtNote {

    @FXML
    private Button addbtn;

    @FXML
    private Button contest;

    @FXML
    private Button dashboard;

    @FXML
    private Button lattice;

    @FXML
    private Button logout;

    @FXML
    private TextArea noteBox;

    @FXML
    private Button profile;

    @FXML
    private Button study;

    @FXML
    private TextArea titleBox;

    String usname;

    @FXML
    void add(MouseEvent event) throws SQLException, IOException {
        File file = new File("userinfo.txt");
        Scanner sc = new Scanner(file);
        usname = sc.next();
        LocalDateTime localTime = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
        String date = localTime.format(fmt);
        Connection connection = DBconnect.getConnect();
        String query = "INSERT INTO `notes` VALUES('" + encodeDecode.encode(titleBox.getText()) + "', '" + encodeDecode.encode(noteBox.getText()) + "', '" + usname + "', '" + date + "');";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        Stage stage = (Stage) addbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Notes/notesview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("NOTES");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void contestBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefadewithstyle("/com/example/RedSet/Lattice/showcontestsupcoming-view.fxml", event, lattice, "CONTEST");
    }

    @FXML
    void dashboardBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefadewithstyle("/com/example/RedSet/dashboard.fxml", event, lattice, "DASHBOARD");
    }

    @FXML
    void latticeBtn(MouseEvent event) throws IOException {
        SceneTransition.loadSceneTranslate("/com/example/RedSet/Lattice/hello-view.fxml", event, lattice, "LATTICELINE");
    }

    @FXML
    void logoutBtn(MouseEvent event) throws IOException {
        FileWriter fileWriter = new FileWriter("userinfo.txt");
        fileWriter.write("");
        fileWriter.close();
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/logInSignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LOG IN");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void profileBtn(MouseEvent event) throws IOException {
        SceneTransition.loadSceneTranslate("/com/example/RedSet/Profile/viewProfile.fxml",event,profile,"PROFILE");
    }

    @FXML
    void studyBtn(MouseEvent event) throws IOException {
        SceneTransition.loadSceneTranslate("/com/example/RedSet/Study/topic.fxml",event,study,"PROBLEMS");
    }

}
