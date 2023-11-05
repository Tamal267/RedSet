package com.example.RedSet.Profile;

import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.MAIN;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

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

import java.util.ResourceBundle;

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

    @FXML
    private Circle in;
    @FXML
    private Circle out;


    @FXML
    void latticeBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) lattice.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Lattice/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1520,780);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void logoutBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/logInSignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void dashboardBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) dashboard.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void editBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) edit.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Profile/editProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void contestBtn(MouseEvent event) {

    }



    @FXML
    void studyBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) study.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Study/study.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
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
    }
}
