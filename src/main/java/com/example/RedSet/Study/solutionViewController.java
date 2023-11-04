package com.example.RedSet.Study;

import com.example.RedSet.Lattice.encodeDecode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class solutionViewController implements Initializable {


    @FXML
    private Button back;

    @FXML
    private Button contest;

    @FXML
    private Button dashboard;

    @FXML
    private TextArea editorial;

    @FXML
    private Button lattice;

    @FXML
    private Button logout;

    @FXML
    private Button profile;

    @FXML
    private TextArea solution;

    problemInfo infoprb = problemInfo.getInstance();


    @FXML
    void backBtn(MouseEvent event) {

    }

    @FXML
    void contestBtn(MouseEvent event) {

    }

    @FXML
    void dashboardBtn(MouseEvent event) {

    }

    @FXML
    void latticeBtn(MouseEvent event) {

    }

    @FXML
    void logoutBtn(MouseEvent event) {

    }

    @FXML
    void profileBtn(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Scanner sc = new Scanner(infoprb.getSolution());
        editorial.setText(encodeDecode.decode(sc.next()));
        solution.setText(encodeDecode.decode(infoprb.getAcceptedCode()));
    }
}
