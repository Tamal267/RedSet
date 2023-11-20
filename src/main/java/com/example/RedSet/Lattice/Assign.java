package com.example.RedSet.Lattice;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.reactfx.Subscription;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Assign extends editorUI implements Initializable {


    @FXML
    private Button acceptedbtn;

    @FXML
    private Button backbtn;

    @FXML
    private AnchorPane compilerbtn;

    @FXML
    private AnchorPane groupbtn;

    @FXML
    private TextArea outBox;

    @FXML
    private Text problemId;

    @FXML
    private AnchorPane problemsbtn;

    @FXML
    private Button statusbtn;

    @FXML
    private HBox statusbtns;

    @FXML
    private Button submitbtn;

    @FXML
    private TextArea text;

    @FXML
    private Button editbtn;

    assigninfo asinfo = assigninfo.getInstance();
    FileChooser fileChooser = new FileChooser();

    String id, users, txt, acceptedCode, inp, timelimit;


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane redsetbtn;

    prevpage prevpg = prevpage.getInstance();



    @FXML
    void redset(MouseEvent event) throws IOException {
        Stage stage = (Stage) redsetbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void problems(MouseEvent event) throws IOException {
        Stage stage = (Stage) problemsbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void chooseFile(MouseEvent event) throws FileNotFoundException {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            codeArea.clear();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                codeArea.appendText(scanner.nextLine() + "\n");
            }
        }
    }

    @FXML
    void back(MouseEvent event) throws IOException {
        Stage stage = (Stage) backbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("eachgroup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void compiler(MouseEvent event) throws IOException {
        Stage stage = (Stage) compilerbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("compiler-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(HelloApplication.class.getResource("java-keywords.css").toExternalForm());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void group(MouseEvent event) throws IOException {
        Stage stage = (Stage) groupbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("groups-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void edit(MouseEvent event) throws IOException {
        asinfo.setId(id);
        asinfo.setText(txt);
        asinfo.setTimelimit(timelimit);
        asinfo.setCode(encodeDecode.decode(acceptedCode));
        asinfo.setInp(inp);
        prevpg.setPrev("assign-view.fxml");
        Stage stage = (Stage) editbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("crtassign-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void run(MouseEvent event) throws IOException, SQLException {
//        outputBox.setWrapText(true);
//        String out = CppCompiler.compileAndRunFromFile(codeBox.getText(), inputBox.getText());
//        outputBox.appendText(out);

        outBox.clear();
        LocalDateTime tmm = LocalDateTime.now();
        DateTimeFormatter pat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String time = tmm.format(pat);

        File file = new File("assign.txt");
        String encodedCode = Base64.getEncoder().encodeToString(codeArea.getText().getBytes());
        int ac = 1;
        int mxTime = 0;
        int mxMemory = 0;
        Scanner sc = new Scanner(inp);
        while (sc.hasNext()) {
            String inp = sc.next();
            byte[] decodeInp = Base64.getDecoder().decode(inp);
            String decodedInp = new String(decodeInp);
            Map<String, String> mapAc = CompilerOnline.compile(acceptedCode, inp, "cpp", timelimit);
            Map<String, String> mapUc = CompilerOnline.compile(encodedCode, inp, "cpp", timelimit);
            if (!Objects.equals(mapUc.get("status"), "Accepted")) {
                String msg = mapUc.get("status") + "\n";
                outBox.setText(msg);
                ac = 0;
                break;
            }
            if (!Objects.equals(mapAc.get("stdout"), mapUc.get("stdout"))) {
                String msg = "Wrong Answer\n\n" +
                        "Input:\n" +
                        decodedInp +
                        "\n\n" +
                        "Accecpted Answer:\n" +
                        mapAc.get("stdout") +
                        "\n\n" +
                        "Your Answer:\n" +
                        mapUc.get("stdout") +
                        "\n";
                outBox.setText(msg);
                ac = 0;
                break;
            }
            double t = parseDouble(mapUc.get("time")) * 1000;
            int tm = (int) t;
            mxTime = Math.max(mxTime, tm);
            mxMemory = Math.max(mxMemory, parseInt(mapUc.get("memory")));
        }
        if (ac == 1) {
            String msg = time + "\nAccepted\n" + "Time: " + Integer.toString(mxTime) + "ms\n" + "Memory: " + Integer.toString(mxMemory) + "KB\n";
            outBox.setText(msg);
            Connection connection = DBconnect.getConnect();
            String query = "SELECT * FROM `assignment` WHERE assignId='" + id + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String usrs = resultSet.getString("users");
                String encodedMsg = Base64.getEncoder().encodeToString(msg.getBytes());
                file = new File("userinfo.txt");
                sc = new Scanner(file);
                usrs += " " + sc.nextLine() + " " + encodedMsg + " " + encodedCode;
                query = "UPDATE assignment SET users='" + usrs + "' WHERE assignId='" + id + "';";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        }
    }

    @FXML
    void Status(MouseEvent event) throws IOException {
        prevpg.setPrev("assign-view.fxml");
        Stage stage = (Stage) statusbtns.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("status-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
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


        codeArea.replaceText(0, 0, sampleCode);


        File usFile = new File("userinfo.txt");
        Scanner usSc = null;
        try {
            usSc = new Scanner(usFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String usn = usSc.nextLine();
        File file = new File("assign.txt");
        try {
            Scanner sc = new Scanner(file);
            id = sc.nextLine();
            System.out.println(id);
            problemId.setText(id);
            txt = encodeDecode.decode(sc.nextLine());
            acceptedCode = sc.nextLine();
            inp = sc.nextLine();
            timelimit = sc.nextLine();
            text.setText("Time Limit: " + timelimit + "s\n\n" + txt);
            users = sc.nextLine();

            String fname;
            String temp = "";
            String atext = "";
            int flag = 0;
            usSc = new Scanner(users);
            String abc = usSc.next();
            while(usSc.hasNext()){
                fname = usSc.next();
                temp = usSc.next();
                atext = usSc.next();
                System.out.println(fname + "\n" + temp + "\n" + atext);
                if(Objects.equals(fname, usn)){
                    flag = 1;
                    break;
                }
            }
            if(flag == 1){
                outBox.setText(encodeDecode.decode(temp));
                codeArea.clear();
                codeArea.replaceText(0, 0, encodeDecode.decode(atext));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        File fl = new File("isteacher.txt");
        Scanner scT = null;
        try {
            scT = new Scanner(fl);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String fndTch = scT.next();
        if (Objects.equals(fndTch, "teacher")) {
            statusbtns.setVisible(true);
        } else {
            statusbtns.setVisible(false);
        }
        borderPane.setCenter(codeArea);
    }
}
