package com.example.RedSet.Study;

import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.Lattice.encodeDecode;
import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.Lattice.ShowRank;
import com.example.RedSet.MAIN;
import com.example.RedSet.SceneTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Problem implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Button contest;

    @FXML
    private Button dashboard;

    @FXML
    private Button lattice;

    @FXML
    private Button logout;

    @FXML
    private Button profile;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button study;

    @FXML
    private VBox tilepane;

    String problemid, statement, code, input, timelimit, users, solution;

    topicInfo info = topicInfo.getInstance();

    problemInfo infoprb = problemInfo.getInstance();

    @FXML
    void backBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("/com/example/RedSet/Study/topic.fxml",event,lattice,"STUDY");
    }


    @FXML
    void contestBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("/com/example/RedSet/Lattice/showcontestsupcoming-view.fxml",event,lattice,"LATTICELINE");
    }


    @FXML
    void dashboardBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("dashboard.fxml",event,dashboard,"DASHBOARD");
    }

    @FXML
    void latticeBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("/com/example/RedSet/Lattice/hello-view.fxml",event,lattice,"LATTICELINE");
    }


    @FXML
    void logoutBtn(MouseEvent event) throws IOException {
        FileWriter fileWriter = new FileWriter("userinfo.txt");
        fileWriter.write("");
        fileWriter.close();
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/logInSignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LOG IN");
        stage.setScene(scene);
        stage.centerOnScreen();

    }

    @FXML
    void profileBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("/com/example/RedSet/Profile/viewProfile.fxml",event,profile,"PROFILE");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int iter = 1;
        tilepane.setMaxWidth(Region.USE_PREF_SIZE);
        scrollPane.setFitToWidth(true);
        Scanner newsc = new Scanner(info.subTopic);
        while (newsc.hasNext()){
            problemid = encodeDecode.decode(newsc.next());
            System.out.println(problemid);
            try {
                Connection connection = DBconnect.getConnect();
                String query = "SELECT * FROM `studyProblems` WHERE problemid='" + problemid + "';";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    System.out.println("found");
                    infoprb.setPrbName(problemid);
                    statement = resultSet.getString("statement");
                    code = resultSet.getString("code");
                    timelimit = resultSet.getString("timelimit");
                    input = resultSet.getString("input");
                    users = resultSet.getString("users");
                    solution = resultSet.getString("solution");

                    int sz = 0;
                    Scanner scz = new Scanner(users);
                    String temp = scz.next();
                    while (scz.hasNext()){
                        temp = scz.next();
                        temp = scz.next();
                        temp = scz.next();
                        sz++;
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader(Problem.class.getResource("/com/example/RedSet/Study/sampleproblem.fxml"));
                    GridPane gridPane = fxmlLoader.load();
                    SampleProblem sampleProblem = fxmlLoader.getController();
                    System.out.println(iter);
                    sampleProblem.setData(Integer.toString(iter), problemid, Integer.toString(sz), problemid, statement, code, timelimit, input, users, solution);
                    iter++;
                    tilepane.getChildren().add(gridPane);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
