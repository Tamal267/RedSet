package com.example.RedSet.Profile;

import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.MAIN;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class editProfileController implements Initializable{

    @FXML
    private Button contest;

    @FXML
    private Button dashboard;

    @FXML
    private TextField email;

    @FXML
    private TextField fullname;

    @FXML
    private Button lattice;

    @FXML
    private TextField level;

    @FXML
    private Button logout;

    @FXML
    private PasswordField newpass;

    @FXML
    private PasswordField oldpass;

    @FXML
    private TextField phone;

    @FXML
    private PasswordField renewpass;

    @FXML
    private Button saveBtn;

    @FXML
    private Button study;

    @FXML
    private Circle in;
    @FXML
    private Circle out;

    @FXML
    private TextField username;

    @FXML
    private TextField dept;

    @FXML
    private TextField stdId;

    String password;

    String usname;

    @FXML
    void dashboardBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) dashboard.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DASHBOARD");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void latticeBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) lattice.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Lattice/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1520,780);
        stage.setScene(scene);
        stage.setTitle("LATTICELINE");
        stage.centerOnScreen();
    }

    @FXML
    void logoutBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/logInSignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LOG IN");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void studyBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) study.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Study/topic.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PROBLEMS");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void saveBtn(MouseEvent event) throws IOException, SQLException {
        if(Objects.equals(password, oldpass.getText())){
            if(Objects.equals(newpass.getText(), "")){
                Connection connection = DBconnect.getConnect();
                String query = "UPDATE users SET fullName='" + fullname.getText() + "', email='" + email.getText() + "', institute='" + dept.getText() + "' WHERE username='" + usname + "';";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
            else if(Objects.equals(newpass.getText(), renewpass.getText())){
                Connection connection = DBconnect.getConnect();
                String query = "UPDATE users SET fullName='" + fullname.getText() + "', email='" + email.getText() + "', password='" + newpass.getText() + "', institute='" + dept.getText() + "' WHERE username='" + usname + "';";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        }
        else{
            level.setText("Invalid info");
            return;
        }
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Profile/viewProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PROFILE");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void contestBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) lattice.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Lattice/showcontestsupcoming-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CONTEST");
        stage.setScene(scene);
        stage.centerOnScreen();

    }
    @FXML
    private Button back;
    @FXML
    void backBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Profile/viewProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PROFILE");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    private void setContinuousRotate(Circle c, int angle, double duration) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(duration), event -> {
            c.setRotate(c.getRotate() + angle);
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setContinuousRotate(out, 1, 0.015);
        setContinuousRotate(in,-1,0.015);
        File file = new File("userinfo.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        usname = sc.next();
        Connection connection = null;
        try {
            connection = DBconnect.getConnect();
            String query= "SELECT * FROM `users` WHERE username='" + usname + "';";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            String fullNamee = new String(), studentIde = new String(),  emaile = new String(), institutee = new String();
            while (resultSet.next()){
                password = resultSet.getString("password");
                fullNamee = resultSet.getString("fullName");
                studentIde = resultSet.getString("studentId");
                emaile = resultSet.getString("email");
                institutee = resultSet.getString("institute");
                System.out.println(fullNamee + " " + studentIde);
            }
            username.setText(usname);
            fullname.setText(fullNamee);
            stdId.setText(studentIde);
            email.setText(emaile);
            dept.setText(institutee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
