package com.example.RedSet.Activites;

import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.MAIN;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class panelController implements Initializable {
    @FXML
    private Button contest;
    @FXML
    private Button dashboard;
    @FXML
    private Button lattice;
    @FXML
    private Button logout;
    @FXML
    private Button profile;
    @FXML
    private Button rank;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button study;
    @FXML
    private TilePane tilepane;

    @FXML
    void dashboardBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) dashboard.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
    }


    @FXML
    void latticeBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) lattice.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Lattice/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1520,780);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void logoutBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }


    @FXML
    void rankBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) rank.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Ranking/individualRanking.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }


    @FXML
    void studyBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) study.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Study/study.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void profileBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) profile.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Profile/viewProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ranking");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void contestBtn(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tilepane.setMaxWidth(Region.USE_PREF_SIZE);
        scrollPane.setFitToWidth(true);
        List<Image> member = new ArrayList<>(member());
        for(int i = 0; i < member.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/RedSet/Activities/samplePanel.fxml"));
            try {
                AnchorPane box = fxmlLoader.load();
                samplePanelController cic = fxmlLoader.getController();
                cic.setImg(member.get(i));
                tilepane.getChildren().add(box);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private List<Image> member(){
        // image path need to be full address.
        List<Image> ls = new ArrayList<>();

        Image img = new Image("rizu.png");
        ls.add(img);
        img = new Image("zarif.png");
        ls.add(img);
        img = new Image("faisal.png");
        ls.add(img);
        img = new Image("raisa.png");
        ls.add(img);
        img = new Image("khaled.png");
        ls.add(img);
        img = new Image("sara.png");
        ls.add(img);
        img = new Image("sabbir.png");
        ls.add(img);
        img = new Image("adib.png");
        ls.add(img);
        img = new Image("ellora.png");
        ls.add(img);
        img = new Image("sevas.png");
        ls.add(img);
        img = new Image("abdullah.png");
        ls.add(img);
        img = new Image("maria.png");
        ls.add(img);
        img = new Image("sadiq.png");
        ls.add(img);
        img = new Image("arik.png");
        ls.add(img);
        img = new Image("raisul.png");
        ls.add(img);
        img = new Image("sumaiya.png");
        ls.add(img);
        img = new Image("rashid.png");
        ls.add(img);
        img = new Image("abrar (2).png");
        ls.add(img);
        img = new Image("abrar.png");
        ls.add(img);
        img = new Image("rubaid.png");
        ls.add(img);
        img = new Image("iftee.png");
        ls.add(img);
        img = new Image("saifur.png");
        ls.add(img);
        img = new Image("nizami.png");
        ls.add(img);
        img = new Image("rafi.png");
        ls.add(img);
        img = new Image("humayra.png");
        ls.add(img);
        img = new Image("nabil.png");
        ls.add(img);
        img = new Image("alam.png");
        ls.add(img);
        img = new Image("rifat.png");
        ls.add(img);
        img = new Image("aunindya.png");
        ls.add(img);
        img = new Image("porag.png");
        ls.add(img);
        img = new Image("sakif.png");
        ls.add(img);
        img = new Image("mayeesha.png");
        ls.add(img);
        img = new Image("maisha.png");
        ls.add(img);
        img = new Image("sifat.png");
        ls.add(img);
        img = new Image("shoyeb.png");
        ls.add(img);
        img = new Image("reffah.png");
        ls.add(img);
        return ls;
    }


}
