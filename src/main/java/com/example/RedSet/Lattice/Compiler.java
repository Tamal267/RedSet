package com.example.RedSet.Lattice;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.reactfx.Subscription;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler extends editorUI implements Initializable {

    @FXML
    private WebView webview;
    @FXML
    private AnchorPane goGp;
    @FXML
    private TextArea inputBox;

    @FXML
    private TextArea outputBox;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane redsetbtn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine webengine = webview.getEngine();

        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Fireworks</title>\n" +
                "    <style>\n" +
                "    \tbody{\n" +
                "    \tbackground:#000;\n" +
                "    \tmargin:0;\n" +
                "    \twidth:100%;\n" +
                "    \toverflow:hidden;\n" +
                "    \t}\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    \n" +
                "    <canvas></canvas>\n" +
                "\n" +
                "    <script>\n" +
                "        var t = 0;\n" +
                "        var c = document.querySelector(\"canvas\");     \n" +
                "        var $ = c.getContext('2d');\n" +
                "        c.width = window.innerWidth;\n" +
                "        c.height = window.innerHeight;\n" +
                "        $.fillStyle = 'hsla(0,0%,0%,1)';\n" +
                "\n" +
                "        window.addEventListener('resize', function() {\n" +
                "          c.width = window.innerWidth;\n" +
                "          c.height = window.innerHeight;\n" +
                "        }, false);\n" +
                "\n" +
                "        function draw() {\n" +
                "          $.globalCompositeOperation = 'source-over';\n" +
                "          $.fillStyle = 'hsla(0,0%,0%,.1)';\n" +
                "          $.fillRect(0, 0, c.width, c.height);\n" +
                "          var foo, i, j, r;\n" +
                "          foo = Math.sin(t) * 0.1 * Math.PI;\n" +
                "          for (i=0; i<400; ++i) {\n" +
                "            r = 800 * Math.sin(i * foo);\n" +
                "            $.globalCompositeOperation = '';\n" +
                "            $.fillStyle = 'hsla(' + i + 12 + ',100%, 60%,1)';\n" +
                "            $.beginPath();\n" +
                "            $.arc(Math.sin(i) * r + (c.width / 2), \n" +
                "                  Math.cos(i) * r + (c.height / 2), \n" +
                "                  1.5, 0, Math.PI * 2);\n" +
                "            $.fill();\n" +
                "\n" +
                "          }\n" +
                "          t += 0.000005;\n" +
                "          return t %= 2 * Math.PI;\n" +
                "\n" +
                "        };\n" +
                "\n" +
                "        function run() {\n" +
                "          window.requestAnimationFrame(run);\n" +
                "          draw();\n" +
                "        }\n" +
                "        run();\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";

        webengine.loadContent(htmlContent);


        executor = Executors.newSingleThreadExecutor();
        codeArea = new CodeArea();
        codeArea.setStyle("-fx-font-size:18;");
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

        codeArea.replaceText(0, 0, sampleCode);

        borderPane.setCenter(codeArea);

        outputBox.setEditable(false);
    }

    FileChooser fileChooser = new FileChooser();

    @FXML
    void chooseFile(MouseEvent event) throws FileNotFoundException {
        File file = fileChooser.showOpenDialog(new Stage());
        if(file != null){
            codeArea.clear();
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                codeArea.appendText(scanner.nextLine() + "\n");
            }
        }
    }



    @FXML
    void run(MouseEvent event) throws IOException {
//        outputBox.setWrapText(true);
//        String out = CppCompiler.compileAndRunFromFile(codeBox.getText(), inputBox.getText());
//        outputBox.clear();
//        outputBox.appendText(out);
        outputBox.clear();
        String encodedCode = Base64.getEncoder().encodeToString(codeArea.getText().getBytes());
        String encodedInput = Base64.getEncoder().encodeToString(inputBox.getText().getBytes());
        Map<String, String> map = CompilerOnline.compile(encodedCode, encodedInput, "cpp", "1");
        outputBox.appendText(map.get("stdout") + "\nTime: " + (int)(Double.parseDouble(map.get("time")) * 1000) + "ms\nMemory: " + map.get("memory") + "KB\nStatus: " + map.get("status"));
    }


    @FXML
    private AnchorPane problemsbtn;


    @FXML
    void problems(MouseEvent event) throws IOException {
        Stage stage = (Stage) problemsbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    private AnchorPane groupbtn;

    @FXML
    void group(MouseEvent event) throws IOException {
        Stage stage = (Stage) groupbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("groups-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
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

}