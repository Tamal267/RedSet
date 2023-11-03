package com.example.RedSet.Lattice;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class SingleGrid {


    @FXML
    private Text penaltybox;

    @FXML
    private Text serialbox;

    @FXML
    private Text solvebox;

    @FXML
    private Text usernamebox;

    public void setGrid(rankInfo a, int serial){
        serialbox.setText(String.valueOf(serial));
        usernamebox.setText(a.username);
        solvebox.setText(String.valueOf(a.solve));
        penaltybox.setText(String.valueOf(a.penalty));
    }

}