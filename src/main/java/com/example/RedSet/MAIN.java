package com.example.RedSet;

import com.example.RedSet.Lattice.DBconnect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MAIN extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("redsetwelcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("RedSet");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
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
        long hr = ChronoUnit.HOURS.between(stTime, endTime);
        long min = ChronoUnit.MINUTES.between(stTime, endTime);
        double netDiff = hr + ((double)min - hr*60) / 60;
        double diff1 = 0, diff2 = 0;
        LocalDateTime date1 = LocalDateTime.of((int) year2, (int) month2, (int) day2, 0, 0, 0);
        if(day1 == day2){
            diff1 = netDiff;
        }
        else{
            long hr2 = ChronoUnit.HOURS.between(date1, endTime);
            long min2 = ChronoUnit.MINUTES.between(date1, endTime);
            diff2 = hr2 + ((double)min2 - hr2 * 60) / 60;
            diff1 = netDiff - diff2;
        }
        System.out.println(netDiff + " " + hr + " " + min);
        Connection connection = DBconnect.getConnect();
        File file = new File("userinfo.txt");
        Scanner scc = new Scanner(file);
        String usname = scc.next();
        String query = "SELECT * FROM `users` WHERE username='" +  usname + "';";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String allHr = resultSet.getString("time");
        Scanner scHr = new Scanner(allHr);
        double storeHr[] = new double[40];
        for(int i=0;i<40;i++){
            storeHr[i] = scHr.nextDouble();
            if(i == day1) storeHr[i] += diff1;
            if(i == day2) storeHr[i] += diff2;
        }
        String updatedHr = "";
        for(int i=0;i<40;i++){
            updatedHr += Double.toString(storeHr[i]) + " ";
        }
        connection = DBconnect.getConnect();
        query = "UPDATE users SET time='" + updatedHr + "' WHERE username='" + usname + "';";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }
}