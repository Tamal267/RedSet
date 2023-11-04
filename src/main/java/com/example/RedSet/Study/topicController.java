package com.example.RedSet.Study;

import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.MAIN;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class topicController implements Initializable {

    @FXML
    private TilePane tilepane;
    @FXML
    private ScrollPane scrollPane;
    String  topic, subtopic;
    topicInfo info = topicInfo.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tilepane.setMaxWidth(Region.USE_PREF_SIZE);
        scrollPane.setFitToWidth(true);
        try {
            Connection connection = DBconnect.getConnect();
            String query = "SELECT * FROM studyTopic";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            String topicName, problemids;
            while (resultSet.next()) {
                topicName =  resultSet.getString("topicName");
                problemids = resultSet.getString("problemids");
                System.out.println(topicName);
                int sz = 0;
                Scanner sc = new Scanner(problemids);
                while(sc.hasNext()) {
                    String temp = sc.next();
                    sz++;
                }
                FXMLLoader fxmlLoader =  new FXMLLoader(topicController.class.getResource("/com/example/RedSet/Study/sampleStudy.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                sampleStudyController sprb = fxmlLoader.getController();
                sprb.setData(topicName, String.valueOf(sz), problemids);
                tilepane.getChildren().add(anchorPane);
                String finalTopicName = topicName;
                info.setMainTopic(finalTopicName);
                info.setSubTopic(problemids);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button activities;

    @FXML
    private Button dashboard;

    @FXML
    private Button logout;

    @FXML
    void activitiesBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) activities.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Activities/panel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void dashboardBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) dashboard.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void logoutBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    private Button contest;

    @FXML
    private Button lattice;

    @FXML
    private Button profile;

    @FXML
    private Button rank;


    @FXML
    void contestBtn(MouseEvent event) {

    }

    @FXML
    void latticeBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) lattice.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Lattice/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void profileBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) profile.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Profile/viewProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void rankBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) rank.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Ranking/individualRanking.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
