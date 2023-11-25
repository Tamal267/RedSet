package com.example.RedSet;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RedSetWelcome implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label loadingPercentage;

    @FXML
    private ProgressBar loadingbar;

    @FXML
    private Circle outer;

    int val = 0;

    private void setContinuousRotate(Circle c, int angle, double duration) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(duration), event -> {
            c.setRotate(c.getRotate() + angle);
            if(val <= 95) {
                loadingbar.setProgress(loadingbar.getProgress() + 0.0032);
                val = (int) (loadingbar.getProgress() * 100);
                loadingPercentage.setText(Integer.toString(val) + " %");
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadingbar.setProgress(0.0000);
        loadingPercentage.setText("0 %");
        setContinuousRotate(outer, 1, 0.010);
        splash();
    }

    private void splash(){
        new Thread(){
            public void run(){
                try{
                    Thread.sleep(3000);
                } catch(Exception e){
                    System.out.println(e);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Stage stage = new Stage();
                        File file = new File("userinfo.txt");
                        Scanner sc = null;
                        try {
                            sc = new Scanner(file);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        FXMLLoader fxmlLoader;
                        if(!sc.hasNext()) {
                            fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/logInSignUp.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            stage.setTitle("LOG IN");
                            stage.centerOnScreen();
                            stage.setResizable(false);
                            stage.setScene(scene);
                        }
                        else{
                            fxmlLoader = new FXMLLoader(MAIN.class.getResource("dashboard.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            stage.setTitle("DASHBOARD");
                            stage.setScene(scene);
                        }
                        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/PIC/redset.png")));
                        stage.getIcons().add(icon);
                        stage.show();
                        anchorPane.getScene().getWindow().hide();
                    }
                });
            }
        }.start();
    }
}
