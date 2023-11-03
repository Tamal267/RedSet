package com.example.RedSet.LogIn_SignUp_Pass;

import com.example.RedSet.MAIN;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class OTPmatchingController {

    @FXML
    private Button LogIn;

    @FXML
    private Button confirm;

    @FXML
    private TextField otpCode;

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
    void confirmBtn(MouseEvent event) throws IOException {
        File file = new File("randomNum.txt");
        Scanner sc = new Scanner(file);
        String randomCode = sc.nextLine();
        if(Objects.equals(otpCode.getText(), randomCode)){
            Stage stage = (Stage) confirm.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/newPasswordCreating.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("New Password");
            stage.setScene(scene);
            stage.centerOnScreen();
        }
        else{
            otpCode.setText("OTP not matched!");
        }
    }

}
