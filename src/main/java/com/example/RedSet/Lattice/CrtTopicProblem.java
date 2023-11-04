package com.example.RedSet.Lattice;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CrtTopicProblem implements Initializable {
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
    private AnchorPane groupbtn;

    @FXML
    private TextArea timelimitbox;
    @FXML
    private TextArea solutionBox;

    FileChooser fileChooser = new FileChooser();

    String id, users, txt, acceptedCode, inp = "", timelimit , solution= "";

    @FXML
    private AnchorPane redsetbtn;


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
    void redset(MouseEvent event) throws IOException {
        Stage stage = (Stage) redsetbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void submit(MouseEvent event) throws FileNotFoundException {
        id = idBox.getText();
        txt = encodeDecode.encode(txtBox.getText());
        acceptedCode = encodeDecode.encode(codeBox.getText());
        inp += encodeDecode.encode(inputBox.getText()) + " ";
        users = "-- ";
        timelimit = timelimitbox.getText();
        solution = " " + encodeDecode.encode(solutionBox.getText());
        File file = new File("groupname.txt");
        Scanner sc = new Scanner(file);
        String gpname = sc.next();
        String insertStr = "'" + id + "', '" + txt + "', '" + timelimit + "', '" + acceptedCode + "', '" + inp + "', '" + users + "', '" + solution + "'";
        System.out.println(insertStr);
        Connection connection = null;
        try {
            connection = DBconnect.getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String query = "INSERT INTO `studyProblems` VALUES (" + insertStr + ");";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println("....");
            status.setText("An error occured. Duplication may occur. Check it.");
        }
        try {
            preparedStatement.executeUpdate();
            status.setText("Assignment Passed");
        } catch (SQLException e) {
            status.setText("An error occured. Duplication may occur. Check it.");
        }
        System.out.println(encodeDecode.encode(id));
        inp = "";
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
