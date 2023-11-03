package com.example.RedSet.Activites;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class samplePanelController implements Initializable {

    @FXML
    private ImageView img;

    public void setImg(Image info){
        img.setImage(info);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
