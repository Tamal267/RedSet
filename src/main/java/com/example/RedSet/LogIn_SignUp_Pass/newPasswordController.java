package com.example.RedSet.LogIn_SignUp_Pass;

import com.example.RedSet.MAIN;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class newPasswordController {

    @FXML
    private Button LogIn;

    @FXML
    private Button confirm;

    @FXML
    void ButtonLogIn(MouseEvent event) throws IOException {
        Stage stage = (Stage) LogIn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/LogInSignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    @FXML
    private PasswordField newpass;

    @FXML
    private PasswordField retypepass;
    String userpass;

    @FXML
    void confirmBtn(MouseEvent event) throws IOException {
        if(Objects.equals(newpass.getText(), retypepass.getText()) && !newpass.getText().isEmpty()){
            userpass = newpass.getText();
            Stage stage = (Stage) confirm.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/LogInSignUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Log In");
            stage.setScene(scene);
            stage.centerOnScreen();
        }
    }

}
