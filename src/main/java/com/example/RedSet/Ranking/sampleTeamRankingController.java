package com.example.RedSet.Ranking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class sampleTeamRankingController implements Initializable {

    @FXML
    private Label Penalty;
    @FXML
    private Label Rank;

    @FXML
    private Label TeamName;

    @FXML
    private Label totalSolve;

    public void setData(TeamInformation contact){
        Penalty.setText(contact.getPenalty());
        Rank.setText(contact.getPosition());
        TeamName.setText(contact.getTeamName());
        totalSolve.setText(contact.getTotalSolve());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
