package com.example.RedSet.Notes;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class SingleNote {

    @FXML
    private TextArea notebox;

    @FXML
    private TextArea titlebox;

    public void setData(String title, String note){
        notebox.setText(note);
        titlebox.setText(title);
    }

}
