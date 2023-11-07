package com.example.RedSet.Notes;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class SingleLeaderboard {

    @FXML
    private Text serial;

    @FXML
    private Text username;

    public void setData(String sl, String usname){
        serial.setText(sl);
        username.setText(usname);
    }

}