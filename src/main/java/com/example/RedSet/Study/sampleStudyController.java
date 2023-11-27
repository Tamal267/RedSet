package com.example.RedSet.Study;

import com.example.RedSet.MAIN;
import com.example.RedSet.SceneTransition;
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


    public String mntopic, problemids;

    topicInfo info = topicInfo.getInstance();

    public void setData(String mn, String sb, String problemIds){
        mainTopic.setText(mn);
        mntopic = mn;
        subTopic.setText(sb);
        problemids = problemIds;
    }

    @FXML
    void videoBtn(MouseEvent event) throws IOException {
            info.setMainTopic(mntopic);
            info.setSubTopic(problemids);
//            Stage stage = (Stage) video.getScene().getWindow();
//            FXMLLoader fxmlLoader1 = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Study/problem.fxml"));
//            Scene scene = new Scene(fxmlLoader1.load());
//            stage.setScene(scene);
//            stage.setTitle("PROBLEM");
//            stage.show();
            SceneTransition.loadscenefade("/com/example/RedSet/Study/problem.fxml",event,video,"STUDY");
    }

}
