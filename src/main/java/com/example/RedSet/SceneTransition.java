package com.example.RedSet;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SceneTransition {
   public static void loadscenefade(String fxml, MouseEvent event, Button button,String title)
    {
        try{
            Stage stage=(Stage)button.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(SceneTransition.class.getResource(fxml));
            System.out.println(fxml);
            Scene scene=new Scene(loader.load());
            stage.setScene(scene);
            FadeTransition fadeTransition=new FadeTransition(Duration.millis(500), scene.getRoot());
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
            stage.setTitle(title);
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadscenefade(String fxml, MouseEvent event, GridPane button,String title)
    {
        try{
            Stage stage=(Stage)button.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(SceneTransition.class.getResource(fxml));
            System.out.println(fxml);
            Scene scene=new Scene(loader.load());
            stage.setScene(scene);
            FadeTransition fadeTransition=new FadeTransition(Duration.millis(500), scene.getRoot());
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
            stage.setTitle(title);
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadscenefadewithstyle(String fxml, MouseEvent event, AnchorPane button, String title)
    {
        try{
            Stage stage=(Stage)button.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(SceneTransition.class.getResource(fxml));
            System.out.println(fxml);
            Scene scene=new Scene(loader.load());
            scene.getStylesheets().add(SceneTransition.class.getResource("java-keywords.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(title);
            FadeTransition fadeTransition=new FadeTransition(Duration.millis(500), scene.getRoot());
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadscenefadewithstyle(String fxml, MouseEvent event, Button button, String title)
    {
        try{
            Stage stage=(Stage)button.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(SceneTransition.class.getResource(fxml));
            System.out.println(fxml);
            Scene scene=new Scene(loader.load());
            scene.getStylesheets().add(SceneTransition.class.getResource("btn.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(title);
            FadeTransition fadeTransition=new FadeTransition(Duration.millis(500), scene.getRoot());
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadscenefade(String fxml, MouseEvent event, Button button, String title,Boolean ans)
    {
        try{
            Stage stage=(Stage)button.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(SceneTransition.class.getResource(fxml));
            System.out.println(fxml);
            Scene scene=new Scene(loader.load());
            scene.getStylesheets().add(SceneTransition.class.getResource("java-keywords.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(title);
            FadeTransition fadeTransition=new FadeTransition(Duration.millis(500), scene.getRoot());
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadscenefade(String fxml, MouseEvent event, AnchorPane button, String title)
    {
        try{
            Stage stage=(Stage)button.getScene().getWindow();
            FXMLLoader loader= new FXMLLoader(SceneTransition.class.getResource(fxml));
            System.out.println(fxml);
            Scene scene=new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle(title);
            FadeTransition fadeTransition=new FadeTransition(Duration.millis(500), scene.getRoot());
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadSceneTranslate(String fxml, MouseEvent event, Button button,String title) {
        try {
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(SceneTransition.class.getResource(fxml));
            System.out.println(fxml);
            Scene newScene = new Scene(loader.load());
            Parent root = button.getScene().getRoot();
            stage.setScene(newScene);
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), newScene.getRoot());
            //translateTransition.setByX(newScene.getWidth());
            translateTransition.setFromX(newScene.getWidth());
            translateTransition.setToX(0);
            translateTransition.play();
            translateTransition.setOnFinished(e ->{
                stage.setTitle(title);
                stage.show();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadSceneTranslate(String fxml, MouseEvent event, AnchorPane button,String title) {
        try {
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(SceneTransition.class.getResource(fxml));
            System.out.println(fxml);
            Scene newScene = new Scene(loader.load());
            Parent root = button.getScene().getRoot();
            stage.setScene(newScene);
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), newScene.getRoot());
            //translateTransition.setByX(newScene.getWidth());
            translateTransition.setFromX(newScene.getWidth());
            translateTransition.setToX(0);
            translateTransition.play();
            translateTransition.setOnFinished(e ->{
                stage.setTitle(title);
                stage.show();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadSceneTranslate(String fxml, MouseEvent event, GridPane button,String title) {
        try {
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(SceneTransition.class.getResource(fxml));
            Scene newScene = new Scene(loader.load());
            Parent root = button.getScene().getRoot();
            stage.setScene(newScene);
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), newScene.getRoot());
            //translateTransition.setByX(newScene.getWidth());
            translateTransition.setFromX(newScene.getWidth());
            translateTransition.setToX(0);
            translateTransition.play();
            translateTransition.setOnFinished(e ->{
                stage.setTitle(title);
                stage.show();
        });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
