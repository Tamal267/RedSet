package com.example.RedSet.Lattice;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JoinGrp implements Initializable {
    @FXML
    private AnchorPane compilerbtn;

    @FXML
    private AnchorPane problemsbtn;

    @FXML
    private Label status;

    @FXML
    private TextField gpBox;

    @FXML
    private AnchorPane redsetbtn;

    @FXML
    void redset(MouseEvent event) throws IOException {
        Stage stage = (Stage) redsetbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void crtGroup(MouseEvent event) throws FileNotFoundException, SQLException {
        File file = new File("userinfo.txt");
        Scanner sc = new Scanner(file);
        String student = sc.next();
        Connection connection = null;
        try {
            connection = DBconnect.getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM `gp` WHERE name='" + gpBox.getText() + "';";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            showErrMsg.msg(status, "An error occured. Duplication may occur. Check it.");
        }
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
            showErrMsg.msg(status, "Passed");
        } catch (SQLException e) {
            status.setText("An error occured. Duplication may occur. Check it.");
        }
        int flag = 0;
        String chk = "";
        while(resultSet.next()){
            String stdents = resultSet.getString("stdents");
            sc = new Scanner(stdents);
            while(sc.hasNext()){
                chk = sc.next();
                if(Objects.equals(chk, student)) {
                    flag = 1;
                    break;
                }
            }
            if(flag == 1) break;
            stdents += " " + student;
            query = "UPDATE gp SET stdents='" + stdents + "' WHERE name='" + gpBox.getText() + "';";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        }
        if(flag == 0) {
            query = "SELECT * FROM `users` WHERE username='" + student + "';";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String connect = resultSet.getString("connect");
                connect += " " + gpBox.getText();
                query = "UPDATE users SET connect='" + connect + "' WHERE username='" + student + "';";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        }
    }
    @FXML
    void problems(MouseEvent event) throws IOException {
        Stage stage = (Stage) problemsbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void compiler(MouseEvent event) throws IOException {
        Stage stage = (Stage) compilerbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("compiler-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(HelloApplication.class.getResource("java-keywords.css").toExternalForm());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }
    @FXML
    private AnchorPane groupsbtn;
    @FXML
    void group(MouseEvent event) throws IOException {
        Stage stage = (Stage) groupsbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("groups-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    private Button backbtn;
    @FXML
    void back(MouseEvent event) throws IOException {
        Stage stage = (Stage) backbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("groups-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
