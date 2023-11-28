package com.example.RedSet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ERRORcontrolller {

    private int val = 0;

    @FXML
    private Label MSG;

    @FXML
    private Button okbtn;

    @FXML
    private Button cancelbtn;

    @FXML
    void ok(MouseEvent event){
        val = 1;
        Stage stage = (Stage) okbtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cancel(MouseEvent event) {
        val = 0;
        Stage stage = (Stage) okbtn.getScene().getWindow();
        stage.close();
    }

    public void setMSG(String msg){
        MSG.setText(msg);
    }

    public int getVal(){
        return val;
    }
}
