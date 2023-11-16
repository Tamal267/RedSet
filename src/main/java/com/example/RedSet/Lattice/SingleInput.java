package com.example.RedSet.Lattice;

import com.example.RedSet.MAIN;
import com.example.RedSet.Notes.noteinfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class SingleInput {

    @FXML
    private TextArea notebox;


    @FXML
    private Button upd;

    @FXML
    private Button dlt;

    String inp;
    assigninfo asinfo = assigninfo.getInstance();

    public void setData(String i){
        inp = i;
        notebox.setText(encodeDecode.decode(i));
    }

    @FXML
    void delete(MouseEvent event) throws SQLException, IOException {
        String t = "";
        Scanner sc = new Scanner(asinfo.getInp());
        while(sc.hasNext()){
            String temp = sc.next();
            if(!Objects.equals(temp, inp)) t += temp + " ";
        }
        asinfo.setInp(t);
        Stage stage = (Stage) dlt.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showinputs-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void update(MouseEvent event) throws SQLException, IOException {
        String t = "";
        Scanner sc = new Scanner(asinfo.getInp());
        while(sc.hasNext()){
            String temp = sc.next();
            if(!Objects.equals(temp, inp)) t += temp + " ";
            else t += encodeDecode.encode(notebox.getText()) + " ";
        }
        inp = encodeDecode.encode(notebox.getText());
        asinfo.setInp(t);
    }

}
