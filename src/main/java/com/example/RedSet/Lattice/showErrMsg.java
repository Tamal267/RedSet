package com.example.RedSet.Lattice;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class showErrMsg {
    public static void msg(Text status, String str){
        status.setVisible(true);
        status.setText(str);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), e -> {
            status.setVisible(false);
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();

    }
    public static void msg(Label status, String str){
        status.setVisible(true);
        status.setText(str);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), e -> {
            status.setVisible(false);
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();

    }
}
