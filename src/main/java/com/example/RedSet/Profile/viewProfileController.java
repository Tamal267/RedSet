package com.example.RedSet.Profile;

import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.MAIN;
import com.example.RedSet.SceneTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class viewProfileController implements Initializable {
    @FXML
    private Button contest;
    @FXML
    private Button dashboard;
    @FXML
    private Button logout;
    @FXML
    private Button lattice;
    @FXML
    private Button edit;
    @FXML
    private Button study;
    @FXML
    private TextField dept;
    @FXML
    private TextField username;
    @FXML
    private TextField stdId;
    @FXML
    private TextField email;
    @FXML
    private TextField fullname;
    String usname;
    @FXML
    private Circle in;
    @FXML
    private Circle out;


    @FXML
    void latticeBtn(MouseEvent event) throws IOException {
        SceneTransition.loadSceneTranslate("/com/example/RedSet/Lattice/hello-view.fxml",event,lattice,"LATTICELINE");
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
    void dashboardBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("dashboard.fxml",event,dashboard,"DASHBOARD");
    }


    @FXML
    void editBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("/com/example/RedSet/Profile/editProfile.fxml",event,edit,"PROFILE");
    }

    @FXML
    void contestBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("/com/example/RedSet/Lattice/showcontestsupcoming-view.fxml",event,lattice,"LATTICELINE");
    }



    @FXML
    void studyBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("/com/example/RedSet/Study/topic.fxml",event,study,"STUDY");
    }

    private void setContinuousRotate(Circle c, int angle, double duration) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(duration), event -> {
            c.setRotate(c.getRotate() + angle);
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setContinuousRotate(out, 1, 0.015);
        setContinuousRotate(in,-1,0.015);
        File file = new File("userinfo.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        usname = sc.next();
        Connection connection = null;
        try {
            connection = DBconnect.getConnect();
            String query= "SELECT * FROM `users` WHERE username='" + usname + "';";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            String password = new String(), fullNamee = new String(), studentIde = new String(),  emaile = new String(), institutee = new String();
            while (resultSet.next()){
                password = resultSet.getString("password");
                fullNamee = resultSet.getString("fullName");
                studentIde = resultSet.getString("studentId");
                emaile = resultSet.getString("email");
                institutee = resultSet.getString("institute");
                System.out.println(fullNamee + " " + studentIde);
            }
            username.setText(usname);
            fullname.setText(fullNamee);
            stdId.setText(studentIde);
            email.setText(emaile);
            dept.setText(institutee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
