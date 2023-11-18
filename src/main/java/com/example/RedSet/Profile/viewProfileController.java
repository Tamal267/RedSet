package com.example.RedSet.Profile;

import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.MAIN;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileNotFoundException;
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
    @FXML
    private TextField level;
    @FXML
    private TextField phone;

    String usname;

    @FXML
    private Circle in;
    @FXML
    private Circle out;


    @FXML
    void latticeBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) lattice.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Lattice/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1520,780);
        stage.setTitle("LATTICELINE");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void logoutBtn(MouseEvent event) throws IOException {
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
        Stage stage = (Stage) dashboard.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DASHBOARD");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void editBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) edit.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Profile/editProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PROFILE");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void contestBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) lattice.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Lattice/showcontestsupcoming-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CONTEST");
        stage.setScene(scene);
        stage.centerOnScreen();

    }



    @FXML
    void studyBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) study.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Study/topic.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PROBLEMS");
        stage.setScene(scene);
        stage.centerOnScreen();
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
            System.out.println(query);
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
