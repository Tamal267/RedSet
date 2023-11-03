package com.example.RedSet.Lattice;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class CrtConPrb {
    @FXML
    private AnchorPane compilerbtn;

    @FXML
    private BorderPane borderText;

    @FXML
    private BorderPane borderText1;

    @FXML
    private TextArea codeBox;

    @FXML
    private TextArea idBox;

    @FXML
    private TextArea inputBox;

    @FXML
    private AnchorPane problemsbtn;

    @FXML
    private TextArea txtBox;

    @FXML
    private Label status;

    @FXML
    private Button backbtn;

    @FXML
    private AnchorPane groupbtn;


    @FXML
    private TextArea timelimitbox;

    FileChooser fileChooser = new FileChooser();

    String id, users, txt, acceptedCode, inp = "", timelimit;

    contestInfo info = contestInfo.getInstance();

    @FXML
    void nxtInput(MouseEvent event) {
        inp += encodeDecode.encode(inputBox.getText()) + " ";
        inputBox.setText("");
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
    void submit(MouseEvent event) throws IOException {
        id = idBox.getText();
        txt = encodeDecode.encode(txtBox.getText());
        acceptedCode = encodeDecode.encode(codeBox.getText());
        inp += encodeDecode.encode(inputBox.getText()) + " ";
        users = "-- ";
        if(Objects.equals(id, "") || Objects.equals(txt, "") || Objects.equals(acceptedCode, "") ){
            showErrMsg.msg(status, "All the boxes must be filled up");
            return;
        }
        timelimit = timelimitbox.getText();
        try {
            int tl = Integer.parseInt(timelimit);
        } catch (NumberFormatException e) {
            showErrMsg.msg(status, "Enter Time Limit Correctly");
        }
        File file = new File("groupname.txt");
        Scanner sc = new Scanner(file);
        String gpname = sc.next();
        String insertStr = "'" + id + "', '" + txt + "', '" + acceptedCode + "', '" + inp + "', '" + users + "', '" + timelimit + "'";
        System.out.println(insertStr);
        System.out.println(insertStr);
        Connection connection = null;
        try {
            connection = DBconnect.getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String query = "INSERT INTO `conProb` VALUES (" + insertStr + ");";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            showErrMsg.msg(status, "An error occured. Duplication may occur. Check it.");
        }
        try {
            preparedStatement.executeUpdate();
            status.setText("Passed");
        } catch (SQLException e) {
            showErrMsg.msg(status, "An error occured. Duplication may occur. Check it.");
        }
        String ids = info.getProblemsIds();
        ids += " " + encodeDecode.encode(id);
        System.out.println(ids);
        info.setProblemsIds(ids);
    }
    @FXML
    void chooseFile(MouseEvent event) throws FileNotFoundException {
        File file = fileChooser.showOpenDialog(new Stage());
        if(file != null){
            codeBox.clear();
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                codeBox.appendText(scanner.nextLine() + "\n");
            }
        }
    }
    @FXML
    void group(MouseEvent event) throws IOException {
        Stage stage = (Stage) groupbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("groups-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void back(MouseEvent event) throws IOException {
        System.out.println(info.getContestName());
        Stage stage = (Stage) backbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("crtcontest-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }
}
