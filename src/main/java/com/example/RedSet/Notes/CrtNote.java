package com.example.RedSet.Notes;

import com.example.RedSet.Lattice.DBconnect;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CrtNote {

    @FXML
    private Button addbtn;

    @FXML
    private Button contest;

    @FXML
    private Button dashboard;

    @FXML
    private Button lattice;

    @FXML
    private Button logout;

    @FXML
    private TextArea noteBox;

    @FXML
    private Button profile;

    @FXML
    private Button study;

    @FXML
    private TextArea titleBox;

    String usname;

    @FXML
    void add(MouseEvent event) throws SQLException, FileNotFoundException {
        File file = new File("userinfo.txt");
        Scanner sc = new Scanner(file);
        usname = sc.next();
        LocalDateTime localTime = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
        String date = localTime.format(fmt);
        Connection connection = DBconnect.getConnect();
        String query = "INSERT INTO `notes` VALUES('" + titleBox.getText() + "', '" + noteBox.getText() + "', '" + usname + "', '" + date + "');";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
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

    @FXML
    void studyBtn(MouseEvent event) {

    }

}
