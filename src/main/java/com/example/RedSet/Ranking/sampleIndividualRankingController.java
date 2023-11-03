package com.example.RedSet.Ranking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class sampleIndividualRankingController implements Initializable {


    @FXML
    private Label dept;

    @FXML
    private Label level;

    @FXML
    private Label name;

    @FXML
    private Label penalty;

    @FXML
    private Label rank;

    @FXML
    private Label totalSolve;

    public void setData(IndividualInformation contestant){
        dept.setText(contestant.getDept());
        level.setText(contestant.getLevel());
        rank.setText(contestant.getRank());
        name.setText(contestant.getName());
        penalty.setText(contestant.getPenalty());
        totalSolve.setText(contestant.getTotalSolve());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
