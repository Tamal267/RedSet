package com.example.RedSet;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Transition {
    public void SCENECHANGE(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("redsetwelcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("RedSet");
        stage.centerOnScreen();
        stage.setScene(scene);
        Image icon = new Image("logoRedset.png");
        stage.getIcons().add(icon);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
