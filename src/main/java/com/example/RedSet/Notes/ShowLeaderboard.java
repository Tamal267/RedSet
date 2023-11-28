package com.example.RedSet.Notes;

import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.MAIN;
import com.example.RedSet.SceneTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ShowLeaderboard implements Initializable {

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
    private Button study;

    @FXML
    private VBox vbox;

    @FXML
    void contestBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("/com/example/RedSet/Lattice/showcontestsupcoming-view.fxml",event,lattice,"CONTEST");
    }

    @FXML
    void dashboardBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("/com/example/RedSet/dashboard.fxml",event,lattice,"DASHBOARD");
    }

    @FXML
    void latticeBtn(MouseEvent event) throws IOException {
        SceneTransition.loadSceneTranslate("/com/example/RedSet/Lattice/hello-view.fxml",event,lattice,"LatticeLine");
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
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();

    }

    @FXML
    void profileBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("/com/example/RedSet/Profile/viewProfile.fxml",event,profile,"PROFILE");
    }

    @FXML
    void studyBtn(MouseEvent event) throws IOException {
        SceneTransition.loadscenefade("/com/example/RedSet/Study/topic.fxml",event,study,"STUDY");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = DBconnect.getConnect();
            String query = "SELECT * FROM `users`";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<leaderinfo> leaderList = new ArrayList<>();
            while(resultSet.next()){
                String time = resultSet.getString("time");
                String name = resultSet.getString("username");
                double tm = 0;
                Scanner sc = new Scanner(time);
                while(sc.hasNext()){
                    tm += sc.nextDouble();
                }
                leaderList.add(new leaderinfo(tm, name));
            }
            leaderList.sort(leaderinfo::comp);
            int iter = 1;
            for(leaderinfo i:leaderList){
                FXMLLoader fxmlLoader = new FXMLLoader(ShowLeaderboard.class.getResource("/com/example/RedSet/Notes/singleleaderboard.fxml"));
                GridPane gridPane = fxmlLoader.load();
                SingleLeaderboard singleNote = fxmlLoader.getController();
                singleNote.setData(String.valueOf(iter), i.usernmame);
                iter++;
                vbox.getChildren().add(gridPane);
                System.out.println(i.time);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
