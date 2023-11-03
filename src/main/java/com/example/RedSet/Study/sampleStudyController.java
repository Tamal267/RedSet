package com.example.RedSet.Study;

import com.example.RedSet.MAIN;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class sampleStudyController {

    @FXML
    private Label mainTopic;

    @FXML
    private Label subTopic;

    @FXML
    private Button video;
    @FXML
    private ImageView image;
    public String mntopic;

    public void setData(String mn, String sb){
        mainTopic.setText(mn);
        mntopic = mn;
        subTopic.setText(sb);
    }

    @FXML
    void videoBtn(MouseEvent event) throws IOException {
        openWebpage.open("www.github.com/hasnat0006");
    }

}
