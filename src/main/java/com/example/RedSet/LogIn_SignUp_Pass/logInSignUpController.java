package com.example.RedSet.LogIn_SignUp_Pass;

import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.Lattice.showErrMsg;
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
import javafx.scene.layout.VBox;
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
    private VBox vbox;

    @FXML
    TextField textField;


    @FXML
    private TextField signupshowpasstextfeild1;

    @FXML
    private TextField signupshowpasstextfeild2;

    @FXML
    private Label showerr;



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
        String actual_pass = "";
        if(loginShowpass.isSelected()){
            actual_pass = textField.getText();
        }
        else{
            actual_pass = loginpasswordbox.getText();
        }
        System.out.println(actual_pass);
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
                if (Objects.equals(password, actual_pass)) {
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
                    if (Objects.equals(password, actual_pass)) {
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
                Stage stage = (Stage) loginLogin.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("dashboard.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setResizable(true);
                stage.setTitle("Dashboard");
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
            signupshowpasstextfeild1.setText(signupPassword.getText());
            signupshowpasstextfeild2.setText(signupRetypePassword.getText());
            signupshowpasstextfeild1.setVisible(true);
            signupshowpasstextfeild2.setVisible(true);
        }
        else{
            signupPassword.setText(signupshowpasstextfeild1.getText());
            signupRetypePassword.setText(signupshowpasstextfeild2.getText());
            signupshowpasstextfeild1.setVisible(false);
            signupshowpasstextfeild2.setVisible(false);
        }
    }
    @FXML
    private CheckBox loginShowpass;
    @FXML
    public void loginShowpassBtn(MouseEvent event) {
        if(loginShowpass.isSelected()){
            textField.setText(loginpasswordbox.getText());
            vbox.getChildren().remove(loginpasswordbox);
            textField.setVisible(true);
            vbox.getChildren().add(textField);
        }
        else{
            loginpasswordbox.setText(textField.getText());
            vbox.getChildren().remove(textField);
            textField.setVisible(false);
            vbox.getChildren().add(loginpasswordbox);
        }
    }
    boolean isNumber(String s){
        int cnt = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <='9')
                cnt++;
            else
                return false;
        }
        return cnt > 0;
    }

    void isEmail(String s) throws LoginSignupException, SQLException {
        if(!textValidation.isValid(s, "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            throw new LoginSignupException(s);
        }
        Connection connection = DBconnect.getConnect();
        String query = "SELECT * FROM `users` WHERE email='" + s + "';";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) throw new LoginSignupException(s);
    }

    void isFullname(String s) throws LoginSignupException {
        if(!textValidation.isValid(s, "^[a-zA-Z\\s]+")) throw new LoginSignupException(s);
    }

    void isValidPassword(String s) throws LoginSignupException {
        if(!textValidation.isValid(s, "^.{8,}$")) throw new LoginSignupException(s);
    }

    @FXML
    void signupBtn(MouseEvent event) throws IOException {
        String actual_sign_up_pass = "", actual_sign_up_retype_pass = "";
        String USERPASS = "", USERNAME, FULL_NAME, STD_ID, EMAIL, INSTITUTION;
        if(signupShowpass.isSelected()){
            actual_sign_up_pass = signupshowpasstextfeild1.getText();
            actual_sign_up_retype_pass = signupshowpasstextfeild2.getText();
        }else {
            actual_sign_up_pass = signupPassword.getText();
            actual_sign_up_retype_pass = signupRetypePassword.getText();
        }

        try{
            isEmail(signupEmail.getText());
        } catch (LoginSignupException e){
            System.out.println(e);
            signupEmail.setText("Invalid mail address!");
            return;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try{
            isFullname(String.valueOf(signupFullname.getText().isEmpty()));
        } catch (LoginSignupException e) {
            System.out.println(e);
            signupFullname.setText("Full Name can't be empty!");
        }



        if(!isNumber(signupStdID.getText())) {
            signupStdID.setText("Invalid student ID!");
        }else if(signupUsername.getText().isEmpty()) {
            signupUsername.setText("Username can't be empty!");
        }else if(signupUniversity.getText().isEmpty()){
            signupUniversity.setText("University name can't be empty!");
        }else if(Objects.equals(actual_sign_up_pass,actual_sign_up_retype_pass)) {
            try {
                isValidPassword(actual_sign_up_pass);
                USERPASS = actual_sign_up_pass;
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

                    String mailSub = "Thanks for creating an account in RedSet";

                    String mailBody = "Dear " + FULL_NAME + ",\n\n\n" +
                            "We are thrilled to welcome you to RedSet!" +
                            "Your recent decision to create an account with us has made our day," +
                            "and we wanted to express our heartfelt thanks for choosing to be a part of our community." +
                            "\n\n" +
                            "At RedSet, we are committed to providing an exceptional online judge [LATTICE_LINE] and community experience," +
                            "and we believe you've made a fantastic choice in joining us. Your new account opens the door to a world of" +
                            "exciting opportunities, and we can't wait to share them with you." +
                            "\n" +
                            "\n\n" +
                            "Warm regards,\n" +
                            "RedSet TEAM\n" +
                            "Yusuf Reza Hasnat\n" +
                            "Syed Mafijul Islam\n" +
                            "Tanvin Sarkar Pallab\n" +
                            "[official.redset@gmail.com]\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n";

                    String mail = EMAIL;
                    String user = USERNAME;
                    SendMail.sendEmail(mailBody, mailSub, mail, user);

                    Stage stage = (Stage) signupLogin.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/logInSignUp.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setTitle("Log In");
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    stage.show();
                } catch (SQLException E) {
                    throw new RuntimeException(E);
                }
            } catch (IOException | RuntimeException e) {
                throw new RuntimeException(e);
            } catch (LoginSignupException e) {
                signupPassword.setPromptText("Password should be 8 character long");
                signupRetypePassword.setPromptText("Password should be 8 character long");
                System.out.println("hello");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
