package com.example.RedSet.Study;

import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.Lattice.editorUI;
import com.example.RedSet.Lattice.encodeDecode;
import com.example.RedSet.MAIN;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.reactfx.Subscription;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class solutionViewController extends editorUI implements Initializable {


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
    private BorderPane borderPane;

    problemInfo infoprb = problemInfo.getInstance();


    @FXML
    void backBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) lattice.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Study/problem.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
        stage.centerOnScreen();

    }

    @FXML
    void contestBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) lattice.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Lattice/showcontestsupcoming-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    @FXML
    void dashboardBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) lattice.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
        stage.centerOnScreen();

    }

    @FXML
    void latticeBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) lattice.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Lattice/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
        stage.centerOnScreen();

    }

    @FXML
    void logoutBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/logInSignUp.fxml"));
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        executor = Executors.newSingleThreadExecutor();
        codeArea = new CodeArea();
        codeArea.setStyle("-fx-font-size:14;");
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        Pattern whiteSpace = Pattern.compile( "^\\s+" );

        codeArea.addEventFilter( KeyEvent.KEY_PRESSED, KE ->
        {
            if ( KE.getCode() == KeyCode.ENTER )
            {
                Matcher m = whiteSpace.matcher( codeArea.getParagraph( codeArea.getCurrentParagraph() ).getSegments().get( 0 ) );
                if ( m.find() ) Platform.runLater( () -> codeArea.insertText( codeArea.getCaretPosition(), m.group() ) );
            }
        });
        Subscription cleanupWhenDone = codeArea.multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .retainLatestUntilLater(executor)
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(codeArea.multiPlainChanges())
                .filterMap(t -> {
                    if(t.isSuccess()) {
                        return Optional.of(t.get());
                    } else {
                        t.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                })
                .subscribe(this::applyHighlighting);

        // call when no longer need it: `cleanupWhenFinished.unsubscribe();`



        codeArea.replaceText(0, 0, encodeDecode.decode(infoprb.getAcceptedCode()));

        Scanner sc = new Scanner(infoprb.getSolution());
        editorial.setText(encodeDecode.decode(sc.next()));
        borderPane.setCenter(codeArea);
        codeArea.setEditable(false);
    }
}
