package com.example.RedSet.Notes;

import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.Lattice.encodeDecode;
import com.example.RedSet.MAIN;
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

public class SingleNote {

    @FXML
    private TextArea notebox;

    @FXML
    private TextArea titlebox;


    @FXML
    private Button upd;

    @FXML
    private Button dlt;

    noteinfo info;

    public void setData(noteinfo i){
        info = i;
        notebox.setText(i.getNote());
        titlebox.setText(i.getTitle());
    }

    @FXML
    void delete(MouseEvent event) throws SQLException, IOException {
        Connection connection = DBconnect.getConnect();
        String qu = "DELETE FROM `notes` WHERE title='" + encodeDecode.encode(titlebox.getText()) + "' && (user='" + info.getUser() + "' && date ='" + info.getDate() + "');";
        PreparedStatement preparedStatement = connection.prepareStatement(qu);
        preparedStatement.executeUpdate();
        Stage stage = (Stage) dlt.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Notes/notesview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Notes");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void update(MouseEvent event) throws SQLException, IOException {
        Connection connection = DBconnect.getConnect();
        String qu = "UPDATE `notes` SET title='" + encodeDecode.encode(titlebox.getText()) + "', note='" + encodeDecode.encode(notebox.getText()) + "' WHERE title='" + encodeDecode.encode(titlebox.getText()) + "' && (user='" + info.getUser() + "' && date ='" + info.getDate() + "');";
        PreparedStatement preparedStatement = connection.prepareStatement(qu);
        preparedStatement.executeUpdate();
        Stage stage = (Stage) dlt.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Notes/notesview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Notes");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

}
