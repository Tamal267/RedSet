package com.example.RedSet.LogIn_SignUp_Pass;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public interface showPopUp {
    @FXML
    Button cancel = null;

    @FXML
    Label msglabel = null;

    @FXML
    Button ok = null;

    @FXML
    default void cancelBtn(MouseEvent event) {

    }

    @FXML
    default void okBtn(MouseEvent event) {

    }
    void showErr(String msg);
}
