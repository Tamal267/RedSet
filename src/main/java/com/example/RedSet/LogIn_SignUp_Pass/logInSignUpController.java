package com.example.RedSet.LogIn_SignUp_Pass;

import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.MAIN;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class logInSignUpController implements Initializable {


    @FXML
    private Label IncorrectLabel;

    @FXML
    private Button createacc;

    @FXML
    private Button forgetpass;

    @FXML
    private GridPane img;

    @FXML
    private GridPane loginBox;

    @FXML
    private Button loginLogin;

    @FXML
    private TextField loginUsernameBox;

    @FXML
    private PasswordField loginpasswordbox;

    @FXML
    private Button signup;

    @FXML
    private GridPane signupBox;

    @FXML
    private TextField signupEmail;

    @FXML
    private TextField signupFullname;

    @FXML
    private Button signupLogin;

    @FXML
    private PasswordField signupPassword;

    @FXML
    private PasswordField signupRetypePassword;

    @FXML
    private TextField signupStdID;

    @FXML
    private TextField signupUniversity;

    @FXML
    private TextField signupUsername;

    @FXML
    void createaccBtn(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide .setNode(img);
        slide .setByX(-450);
        slide .setDuration(Duration.seconds(0.5));
        slide.play();
    }

    @FXML
    void forgetpassBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) forgetpass.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/forgotPassword.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Recover password!");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    int flag = 0;
    ArrayList<Users> users = new ArrayList<Users>();

    @FXML
    void loginLoginBtn(MouseEvent event) throws IOException {
        // if matched then go to dashboard;
        Connection connection = null;
        String usname = "";

        try {
            connection = DBconnect.getConnect();
            String query = "SELECT * FROM `users` WHERE username='" + loginUsernameBox.getText() + "';";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            int flag = 0;
            while (resultSet.next()) {
                String password = resultSet.getString("password");
                if (Objects.equals(password, loginpasswordbox.getText())) {
                    flag = 1;
                    usname = resultSet.getString("username");
                    break;
                }
            }
            if (flag == 0) {
                connection = DBconnect.getConnect();
                query = "SELECT * FROM `users` WHERE email='" + loginUsernameBox.getText() + "';";
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String password = resultSet.getString("password");
                    if (Objects.equals(password, loginpasswordbox.getText())) {
                        flag = 1;
                        usname = resultSet.getString("username");
                        break;
                    }
                }
            }
            if (flag == 1) {
                FileWriter fileWriter = new FileWriter("userinfo.txt");
                fileWriter.write(usname);
                fileWriter.close();
                Stage stage = (Stage) forgetpass.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("dashboard.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Recover password!");
                stage.setScene(scene);
                stage.centerOnScreen();
            }
            else{
                loginShowpass.setVisible(false);
                showErrorMessage();
                loginShowpass.setVisible(true);
            }
        } catch (SQLException e) {
            loginShowpass.setVisible(false);
            showErrorMessage();
            loginShowpass.setVisible(true);
        }
    }
    private void showErrorMessage() {
        IncorrectLabel.setText("Incorrect Username or Password");
        IncorrectLabel.setVisible(true);
        int seconds= 2;
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(seconds), event -> {
            IncorrectLabel.setVisible(false);
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();
        loginShowpass.setVisible(true);
    }

    @FXML
    void signupLoginBtn(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide .setNode(img);
        slide .setByX(450);
        slide .setDuration(Duration.seconds(0.5));
        slide.play();
    }

    @FXML
    private CheckBox signupShowpass;

    @FXML
    void signupShowpassBtn(MouseEvent event) {
        if(signupShowpass.isSelected()){
            signupPassword.setPromptText(signupPassword.getText());
            signupPassword.setText("");
            signupPassword.setDisable(true);
            signupRetypePassword.setPromptText(signupRetypePassword.getText());
            signupRetypePassword.setText("");
            signupRetypePassword.setDisable(true);
        }
        else{
            signupPassword.setText(signupPassword.getPromptText());
            signupPassword.setPromptText("Password");
            signupPassword.setDisable(false);
            signupRetypePassword.setText(signupRetypePassword.getPromptText());
            signupRetypePassword.setPromptText("Password");
            signupRetypePassword.setDisable(false);
        }
    }
    @FXML
    private CheckBox loginShowpass;

    @FXML
    void loginShowpassBtn(MouseEvent event) {
        if(loginShowpass.isSelected()){
            IncorrectLabel.setVisible(false);
            loginpasswordbox.setPromptText(loginpasswordbox.getText());
            loginpasswordbox.setText("");
            IncorrectLabel.setText("");
            loginpasswordbox.setDisable(true);
        }
        else{
            IncorrectLabel.setVisible(true);
            loginpasswordbox.setText(loginpasswordbox.getPromptText());
            loginpasswordbox.setPromptText("Password");
            loginpasswordbox.setDisable(false);
        }
    }


    boolean isNumber(String s){
        System.out.println(s);
        int cnt = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <='9')
                cnt++;
            else
                return false;
        }
        return cnt > 0;
    }

    boolean isEmail(String s){
        int cnt = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '@')
                cnt++;
            else if(s.charAt(i) == ' ')
                return false;
        }
        return cnt == 1;
    }

    @FXML
    void signupBtn(MouseEvent event) throws IOException {
        String USERPASS, USERNAME, FULL_NAME, STD_ID, EMAIL, INSTITUTION;
        if(signupFullname.getText().isEmpty()) {
            signupFullname.setText("Full Name can't be empty!");
        }else if(!isNumber(signupStdID.getText())) {
            signupStdID.setText("Invalid student ID!");
        }else if(signupUsername.getText().isEmpty()) {
            signupUsername.setText("Username can't be empty!");
        }else if(signupEmail.getText().isEmpty() || !isEmail(signupEmail.getText())){
            signupEmail.setText("Invalid mail address!");
        }else if(signupUniversity.getText().isEmpty()){
            signupUniversity.setText("University name can't be empty!");
        }
        else if(Objects.equals(signupPassword.getText(), signupRetypePassword.getText()) && !signupPassword.getText().isEmpty()){
            USERPASS = signupPassword.getText();
            USERNAME = signupUsername.getText();
            FULL_NAME = signupFullname.getText();
            STD_ID = signupStdID.getText();
            EMAIL = signupEmail.getText();
            INSTITUTION = signupUniversity.getText();

            Connection connection = null;
            try {
                connection = DBconnect.getConnect();
                String query = "INSERT INTO `users` VALUES('" + USERNAME + "', '" + USERPASS + "', '" + "', '" + FULL_NAME + "', '" + STD_ID + "', '" + EMAIL + "', '" + INSTITUTION + "', '0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0');";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                signupUsername.setText("Username already taken");
            }

            Stage stage = (Stage) signupLogin.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/logInSignUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Log In");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            String mailSub = "Thanks for creating an account in REDSET";

            String mailBody = "Dear " + FULL_NAME + ",\n\n\n"+
                    "We are thrilled to welcome you to REDSET!" +
                    "Your recent decision to create an account with us has made our day," +
                    "and we wanted to express our heartfelt thanks for choosing to be a part of our community." +
                    "\n\n"+
                    "At REDSET, we are committed to providing an exceptional online judge [LATTICE_LINE] and community experience," +
                    "and we believe you've made a fantastic choice in joining us. Your new account opens the door to a world of" +
                    "exciting opportunities, and we can't wait to share them with you."+
                    "\n"+
                    "\n\n"+
                    "Warm regards,\n" +
                    "REDSET TEAM\n" +
                    "Yusuf Reza Hasnat\n" +
                    "Syed Mafijul Islam\n" +
                    "Tanvin Sarkar Pallab\n" +
                    "[clubcognita@gmail.com]\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n";

            String mail = EMAIL;
            String user = USERNAME;
            SendMail.sendEmail(mailBody,mailSub,mail,user);
        }
        else{
            signupPassword.setText("Password can't be empty!");
            signupRetypePassword.setText("Password can't be empty!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
