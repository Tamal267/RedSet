package com.example.RedSet;

import com.example.RedSet.Lattice.DBconnect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MAIN extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        File file = new File("userinfo.txt");
        Scanner sc = new Scanner(file);
        FXMLLoader fxmlLoader;
        if(!sc.hasNext()) {
            fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/logInSignUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Log In");
            stage.setScene(scene);
        }
        else{
            fxmlLoader = new FXMLLoader(MAIN.class.getResource("dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Dashboard");
            stage.setScene(scene);
        }
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/PIC/clubcognita.jpg")));
        stage.getIcons().add(icon);
        stage.centerOnScreen();
        stage.show();
    }
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        LocalDateTime stTime = LocalDateTime.now();
        launch();
        LocalDateTime endTime = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
        String st = stTime.format(fmt);
        String end = endTime.format(fmt);
        long day1 = 0, day2 = 0, year2, month2;
        Scanner sc = new Scanner(st);
        String tmp = sc.next();
        tmp = sc.next();
        day1 = sc.nextLong();
        sc = new Scanner(end);
        year2 = sc.nextLong();
        month2 = sc.nextLong();
        day2 = sc.nextLong();
        double hr = ChronoUnit.HOURS.between(endTime, stTime);
        Connection connection = DBconnect.getConnect();
        File file = new File("userinfo.txt");
        Scanner scc = new Scanner(file);
        String usname = scc.next();
        String query = "SELECT * FROM `users` WHERE username='" +  usname + "';";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
    }
}