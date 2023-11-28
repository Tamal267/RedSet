package com.example.RedSet.Notes;

import com.example.RedSet.ERRORcontrolller;
import com.example.RedSet.Lattice.DBconnect;
import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.Lattice.encodeDecode;
import com.example.RedSet.MAIN;
import com.example.RedSet.SceneTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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

    Pane parentPane;

    noteinfo info;

    void setPane(Pane p){
        parentPane = p;
    }

    public void setData(noteinfo i){
        info = i;
        notebox.setText(i.getNote());
        titlebox.setText(i.getTitle());
    }

    @FXML
    void delete(MouseEvent event) throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/RedSet/popUp.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            ERRORcontrolller erroRcontrolller = fxmlLoader.getController();
            erroRcontrolller.setMSG("Do you want to delete?");
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            parentPane.setDisable(true);
            stage.showAndWait();
            parentPane.setDisable(false);
            if(erroRcontrolller.getVal() == 0) return;
        } catch (Exception E){
            System.out.println(E);
        }
        Connection connection = DBconnect.getConnect();
        String qu = "DELETE FROM `notes` WHERE title='" + encodeDecode.encode(titlebox.getText()) + "' && (user='" + info.getUser() + "' && date ='" + info.getDate() + "');";
        PreparedStatement preparedStatement = connection.prepareStatement(qu);
        preparedStatement.executeUpdate();
//        Stage stage = (Stage) dlt.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Notes/notesview.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Notes");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        SceneTransition.loadscenefade("/com/example/RedSet/Notes/notesview.fxml",event,dlt,"NOTES");
    }

    @FXML
    void update(MouseEvent event) throws SQLException, IOException {
        Connection connection = DBconnect.getConnect();
        String qu = "UPDATE `notes` SET title='" + encodeDecode.encode(titlebox.getText()) + "', note='" + encodeDecode.encode(notebox.getText()) + "' WHERE title='" + encodeDecode.encode(titlebox.getText()) + "' && (user='" + info.getUser() + "' && date ='" + info.getDate() + "');";
        PreparedStatement preparedStatement = connection.prepareStatement(qu);
        preparedStatement.executeUpdate();
//        Stage stage = (Stage) dlt.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Notes/notesview.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Notes");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        SceneTransition.loadscenefade("/com/example/RedSet/Notes/notesview.fxml",event,dlt,"NOTES");
    }

}
