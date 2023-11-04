package com.example.RedSet.Study;

import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.MAIN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SampleProblem implements Initializable {

    @FXML
    private Label freq;

    @FXML
    private Label problemName;

    @FXML
    private Label serial;

    @FXML
    private Button solve;

    @FXML
    public Button solution;

    String problemid, statement, code, input, timelimit, users, sol;

    topicInfo info = topicInfo.getInstance();

    problemInfo infoprb = problemInfo.getInstance();

    @FXML
    void solutionBtn(MouseEvent event) throws IOException {
        infoprb.setPrbName(problemid);
        infoprb.setStatement(statement);
        infoprb.setAcceptedCode(code);
        infoprb.setInp(input);
        infoprb.setTimeLimt(timelimit);
        infoprb.setUsers(users);
        infoprb.setSolution(sol);
        Stage stage = (Stage) solution.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Study/solutionview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void solveBtn(MouseEvent event) throws IOException {
        infoprb.setPrbName(problemid);
        infoprb.setStatement(statement);
        infoprb.setAcceptedCode(code);
        infoprb.setInp(input);
        infoprb.setTimeLimt(timelimit);
        infoprb.setUsers(users);
        infoprb.setSolution(sol);
        FileWriter fileWriter = new FileWriter("assign.txt");
        fileWriter.write(infoprb.getPrbName());
        fileWriter.write("\n");
        fileWriter.write(infoprb.getStatement());
        fileWriter.write("\n");
        fileWriter.write(infoprb.getAcceptedCode());
        fileWriter.write("\n");
        fileWriter.write(infoprb.getInp());
        fileWriter.write("\n");
        fileWriter.write(infoprb.getTimeLimt());
        fileWriter.write("\n");
        fileWriter.write(infoprb.getUsers());
        fileWriter.close();
        Stage stage = (Stage) solve.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Study/individualProblemview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(HelloApplication.class.getResource("java-keywords.css").toExternalForm());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    public void setData(String sr, String name, String fr, String problemids, String statement, String code, String timelimit, String input, String users, String sol){
        freq.setText(fr);
        problemName.setText(name);
        serial.setText(sr);
        System.out.println(name);
        this.problemid = problemids;
        this.statement = statement;
        this.code = code;
        this.timelimit = timelimit;
        this.input = input;
        this.users = users;
        this.sol = sol;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        solve.setText("Solve");
        solution.setText("Solution");
    }
}