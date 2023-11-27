package com.example.RedSet.Lattice;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CrtContest implements Initializable{
    @FXML
    private AnchorPane compilerbtn;

    @FXML
    private AnchorPane problemsbtn;

    @FXML
    private Button addbtn;

    @FXML
    private Button backbtn;

    @FXML
    private TextArea caddprbbox;

    @FXML
    private TextField cdurationbox;

    @FXML
    private TextField cnamebox;

    @FXML
    private TextField ctimebox;

    @FXML
    private AnchorPane groupsbtn;

    @FXML
    private Button submitbtn;

    @FXML
    private Text status;

    @FXML
    private AnchorPane redsetbtn;


    String contestName = "", startTime = "", duration = "", problemsId = "", gpname = "";

    contestInfo info = contestInfo.getInstance();

    assigninfo prbinfo = assigninfo.getInstance();

    prevpage prevpg = prevpage.getInstance();

    @FXML
    void problems(MouseEvent event) throws IOException {
        Stage stage = (Stage) problemsbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(HelloApplication.class.getResource("java-keywords.css").toExternalForm());
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
    void group(MouseEvent event) throws IOException {
        Stage stage = (Stage) groupsbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("groups-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void back(MouseEvent event) throws IOException {
        Stage stage = (Stage) backbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(prevpg.getPrev()));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void add(MouseEvent event) throws IOException {
        prevpg.setPrev("crtcontest-view.fxml");
        contestName = cnamebox.getText();
        startTime = ctimebox.getText();
        duration = cdurationbox.getText();
        problemsId = caddprbbox.getText();
        info.setContestName(contestName);
        info.setStartTime(startTime);
        info.setDuration(duration);
        info.setProblemsIds(problemsId);
        info.setGroupName(gpname);
        prbinfo.setCode("");
        prbinfo.setId("");
        prbinfo.setText("");
        prbinfo.setId("");
        prbinfo.setText("");
        System.out.println(contestName + " " + startTime + " " + duration + " " + problemsId);
        Stage stage = (Stage) addbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("crtconprb-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }


    @FXML
    void redset(MouseEvent event) throws IOException {
        Stage stage = (Stage) redsetbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }


    @FXML
    void submit(MouseEvent event) {
        status.setFill(Color.RED);
        String str = ctimebox.getText();
        Scanner sc = new Scanner(str);
        try {
            String year = sc.next(), month = sc.next(), day = sc.next(), hour = sc.next(), min = sc.next(), sec = sc.next();

            if (year.length() != 4 || month.length() != 2 || day.length() != 2 || hour.length() != 2 || min.length() != 2 || sec.length() != 2) {
                showErrMsg.msg(status, "Check Starting Time");
                return;
            }
        } catch (Exception e) {
            showErrMsg.msg(status, "Check Starting Time");
            return;
        }

        str = cdurationbox.getText();
        sc = new Scanner(str);
        try {
            String year = sc.next(), month = sc.next(), day = sc.next(), hour = sc.next(), min = sc.next(), sec = sc.next();

            if (year.length() != 4 || month.length() != 2 || day.length() != 2 || hour.length() != 2 || min.length() != 2 || sec.length() != 2) {
                showErrMsg.msg(status, "Check Ending Time");
                return;
            }
        } catch (Exception e) {
            showErrMsg.msg(status, "Check Ending Time");
            return;
        }

        if(Objects.equals(cnamebox.getText(), "") || Objects.equals(caddprbbox.getText(), "") ){
            showErrMsg.msg(status, "All the boxes must be filled up");
            return;
        }

        Connection connection = null;
        try {
            connection = DBconnect.getConnect();
            String query = "INSERT INTO `contest`(`contestName`, `startTime`, `duration`, `problemsIds`, `groupName`, `ranking`) VALUES ('" + cnamebox.getText() + "', '" + ctimebox.getText() + "', '" + cdurationbox.getText() + "', '" + caddprbbox.getText() + "', '" + gpname + "', '" + "');";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            status.setFill(Color.GREEN);
            status.setText("Passed");
        } catch (SQLException e) {
            try {
                String query = "UPDATE `contest` SET contestName='" + cnamebox.getText() + "', startTime='" + ctimebox.getText() + "', duration='" + cdurationbox.getText() + "', problemsIds='" + caddprbbox.getText() + "', groupName='" + gpname + "' WHERE contestName='" + cnamebox.getText() + "';";
                System.out.println(query);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                showErrMsg.msg(status, "Contest is Updated");
            } catch (SQLException E){
                showErrMsg.msg(status, "An Error Occured");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("groupname.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        gpname = sc.next();
        cnamebox.setText(info.getContestName());
        ctimebox.setText(info.getStartTime());
        cdurationbox.setText(info.getDuration());
        caddprbbox.setText(info.getProblemsIds());
        System.out.println(info.getRanking());
    }
}
