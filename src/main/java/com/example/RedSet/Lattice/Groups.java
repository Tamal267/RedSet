package com.example.RedSet.Lattice;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Groups implements Initializable {
    @FXML
    private AnchorPane compilerbtn;

    @FXML
    private WebView webview;

    @FXML
    private Button joinbtn;

    @FXML
    private TilePane tilePane;

    @FXML
    private Button crtbtn;

    @FXML
    private ScrollPane scrollPane;

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
        tilePane.setMaxWidth(Region.USE_PREF_SIZE);
        scrollPane.setFitToWidth(true);

        File file = new File("userinfo.txt");
        Scanner usinf = null;
        try {
            usinf = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String usname = usinf.next();
        String username;
        String connect = "";
        ArrayList<String > groups = new ArrayList<>();
        try {
            AtomicReference<Connection> connection = new AtomicReference<>(DBconnect.getConnect());
            AtomicReference<String> query = new AtomicReference<>("SELECT * FROM `users`");
            AtomicReference<PreparedStatement> preparedStatement = new AtomicReference<>(connection.get().prepareStatement(query.get()));
            AtomicReference<ResultSet> resultSet = new AtomicReference<>(preparedStatement.get().executeQuery());

            while(resultSet.get().next()){
                username = resultSet.get().getString("username");
                connect = resultSet.get().getString("connect");
                if(Objects.equals(username, usname)) break;
            }
            Scanner sc = new Scanner(connect);
            while(sc.hasNext()){
                groups.add(sc.next());
            }
            for (String group : groups) {
                BorderPane borderPane = new BorderPane();
                Text txt = new Text();
                txt.setText(group);
//                txt.setStyle("-fx-font-size: 30");
//                txt.setWrappingWidth(250);
                borderPane.setId(group);
                StackPane stackPane = new StackPane();
                stackPane.setOnMouseClicked(e -> {
                    try {
                        connection.set(DBconnect.getConnect());
                        query.set("SELECT * FROM `gp` WHERE name='" + group + "';");
                        preparedStatement.set(connection.get().prepareStatement(query.get()));
                        resultSet.set(preparedStatement.get().executeQuery());
                        String teachers = "";
                        while(resultSet.get().next()) {
                            teachers = resultSet.get().getString("teachers");
                        }
                        Scanner scT = new Scanner(teachers);
                        String tch = "";
                        FileWriter fileWrite = new FileWriter("isteacher.txt");
                        int flag = 0;
                        while(scT.hasNext()){
                            tch = scT.next();
                            System.out.println(tch + "...");
                            if(Objects.equals(tch, usname)){
                                fileWrite.write("teacher");
                                System.out.println(tch);
                                flag = 1;
                                break;
                            }
                        }
                        if(flag == 0) fileWrite.write("Student");
                        fileWrite.close();
                        FileWriter fileWriter = new FileWriter("groupname.txt");
                        fileWriter.write(group);
                        fileWriter.close();
                        Stage stage = (Stage) borderPane.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("eachgroup-view.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        stage.setTitle("LatticeLine");
                        stage.setScene(scene);
                    } catch (IOException | SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                stackPane.getChildren().add(txt);
                stackPane.setMaxSize(200, 90);
                stackPane.setMinSize(200, 90);
                txt.setTextAlignment(TextAlignment.CENTER);
                txt.wrappingWidthProperty().bind(stackPane.widthProperty());
                txt.setFill(Color.WHITE);
                stackPane.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-width: 2; -fx-border-color: WHITE;");
                stackPane.setOnMouseEntered(e -> {
                    stackPane.setStyle("-fx-background-radius: 10 10 30 10; -fx-border-radius: 10 10 30 10; -fx-border-width: 2; -fx-border-color: YELLOW;");
                });
                stackPane.setOnMouseExited(e -> {
                    stackPane.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-width: 2; -fx-border-color: WHITE;");
                });
                BorderPane.setMargin(stackPane, new Insets(20));
                borderPane.setCenter(stackPane);
                borderPane.setMaxSize(220, 110);
                borderPane.setMinSize(220, 110);
                tilePane.getChildren().add(borderPane);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    void crt(MouseEvent event) throws IOException {
        Stage stage = (Stage) crtbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("crtgrp-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }

    @FXML
    void join(MouseEvent event) throws IOException {
        Stage stage = (Stage) joinbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("joingrp-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
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
    void redset(MouseEvent event) throws IOException {
        Stage stage = (Stage) redsetbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LatticeLine");
        stage.setScene(scene);
    }
}