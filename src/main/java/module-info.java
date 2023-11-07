module com.example.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires activation;
    requires java.mail;

    requires com.dlsc.formsfx;
    requires javafx.web;
    requires okhttp3;
    requires okio;
    requires com.google.gson;
    requires javafx.media;
    requires java.scripting;
    requires org.fxmisc.richtext;
    requires reactfx;

    opens com.example.RedSet to javafx.fxml;
    exports com.example.RedSet;
    exports com.example.RedSet.LogIn_SignUp_Pass;
    opens com.example.RedSet.LogIn_SignUp_Pass to javafx.fxml;
    exports com.example.RedSet.Ranking;
    opens com.example.RedSet.Ranking to javafx.fxml;
    exports com.example.RedSet.Lattice;
    opens com.example.RedSet.Lattice to javafx.fxml;
    exports com.example.RedSet.Study;
    opens com.example.RedSet.Study to javafx.fxml;
    exports com.example.RedSet.Profile;
    opens com.example.RedSet.Profile to javafx.fxml;
    exports com.example.RedSet.Activites;
    opens com.example.RedSet.Activites to javafx.fxml;
    exports com.example.RedSet.Notes;
    opens com.example.RedSet.Notes to javafx.fxml;
}