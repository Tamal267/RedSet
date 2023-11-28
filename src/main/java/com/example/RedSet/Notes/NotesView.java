package com.example.RedSet.Notes;

import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.Lattice.encodeDecode;
import com.example.RedSet.MAIN;
import com.example.RedSet.SceneTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class NotesView implements Initializable {


    @FXML
    private Button contest;

    @FXML
    private Button dashboard;

    @FXML
    private Button lattice;

    @FXML
    private Button logout;

    @FXML
    private Button profile;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button study;

    @FXML
    private TilePane tilepane;

    @FXML
    private Button addbtn;


    String usname;

    @FXML
    void contestBtn(MouseEvent event) throws IOException {
//        Stage stage = (Stage) lattice.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Lattice/showcontestsupcoming-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("LatticeLine");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        SceneTransition.loadscenefade("/com/example/RedSet/Lattice/showcontestsupcoming-view.fxml",event,lattice,"LatticeLine");
    }

    @FXML
    void dashboardBtn(MouseEvent event) throws IOException {
//        Stage stage = (Stage) lattice.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/dashboard.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("LatticeLine");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        SceneTransition.loadscenefade("/com/example/RedSet/Profile/viewProfile.fxml",event,lattice,"DASHBOARD");

    }

    @FXML
    void latticeBtn(MouseEvent event) throws IOException {
//        Stage stage = (Stage) lattice.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Lattice/hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("LatticeLine");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        SceneTransition.loadSceneTranslate("/com/example/RedSet/Lattice/hello-view.fxml",event,lattice,"LatticeLine");

    }

    @FXML
    void logoutBtn(MouseEvent event) throws IOException {
        FileWriter fileWriter = new FileWriter("userinfo.txt");
        fileWriter.write("");
        fileWriter.close();
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/logInSignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();

    }

    @FXML
    void profileBtn(MouseEvent event) throws IOException {
//        Stage stage = (Stage) profile.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Profile/viewProfile.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Ranking");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        SceneTransition.loadscenefadewithstyle("/com/example/RedSet/Profile/viewProfile.fxml",event,profile,"Ranking");


    }

    @FXML
    void studyBtn(MouseEvent event) throws IOException {
//        Stage stage = (Stage) study.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Study/topic.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Ranking");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        SceneTransition.loadscenefade("/com/example/RedSet/Study/topic.fxml",event,study,"Ranking");
    }

    @FXML
    void add(MouseEvent event) throws IOException {
//        Stage stage = (Stage) addbtn.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Notes/crtnote.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Add Note");
//        stage.setScene(scene);
        SceneTransition.loadscenefade("/com/example/RedSet/Notes/crtnote.fxml",event,addbtn,"Add Note");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("userinfo.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        usname = sc.next();
        try {
            Connection connection = DBconnect.getConnect();
            String query = "SELECT * FROM `notes` where user='" + usname + "' ORDER BY date DESC;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<noteinfo> arr = new ArrayList<>();

            while(resultSet.next()){
                String title = encodeDecode.decode(resultSet.getString("title"));
                String note = encodeDecode.decode(resultSet.getString("note"));
                String date = resultSet.getString("date");
                arr.add(new noteinfo(title, note, usname, date));
            }
            for(noteinfo i:arr){
                FXMLLoader fxmlLoader = new FXMLLoader(NotesView.class.getResource("/com/example/RedSet/Notes/singlenote.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                SingleNote singleNote = fxmlLoader.getController();
                singleNote.setData(i);
                tilepane.getChildren().add(anchorPane);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
