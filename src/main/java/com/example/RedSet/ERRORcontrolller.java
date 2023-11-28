package com.example.RedSet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ERRORcontrolller {

    @FXML
    private Label MSG;

    @FXML
    private Button okbtn;

    @FXML
    void ok(MouseEvent event) {
        Stage stage = (Stage) okbtn.getScene().getWindow();
        stage.close();
    }

    public void setMSG(String msg){
        MSG.setText(msg);
    }

}
