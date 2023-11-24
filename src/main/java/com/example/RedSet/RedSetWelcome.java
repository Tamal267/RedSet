package com.example.RedSet;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
