package com.example.RedSet;

import com.example.RedSet.Lattice.HelloApplication;
import com.example.RedSet.Notes.leaderinfo;
import com.example.RedSet.Study.openWebpage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.example.RedSet.Lattice.DBconnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private PieChart pie;
    @FXML
    private Label intro;
    @FXML
    private StackedAreaChart area;
    @FXML
    private GridPane completed;
    @FXML
    private Button contest;
    @FXML
    private Button lattice;
    @FXML
    private Button logout;
    @FXML
    private GridPane pending;
    @FXML
    private Button profile;
    @FXML
    private GridPane Notes;
    @FXML
    private GridPane standing;
    @FXML
    private Button study;
    @FXML
    private GridPane watch;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private VBox vbox;
    @FXML
    private Label th;
    @FXML
    private Label unit;
    @FXML
    private Label point;

    String usname;

    @FXML
    void latticeBtn(MouseEvent event) throws IOException {
        /*Stage stage = (Stage) lattice.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Lattice/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LATTICELINE");
        stage.setScene(scene);
        stage.centerOnScreen();*/
        SceneTransition.loadSceneTranslate("/com/example/RedSet/Lattice/hello-view.fxml",event,lattice,"LATTICELINE");
    }

    @FXML
    void logoutBtn(MouseEvent event) throws IOException {
        FileWriter fileWriter = new FileWriter("userinfo.txt");
        fileWriter.write("");
        fileWriter.close();
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/LogIn_SignUp_Pass/logInSignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LOG IN");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    @FXML
    void pendingBtn(MouseEvent event) throws IOException {
//        Stage stage = (Stage) pending.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Lattice/showcontestsrunning-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("CONTEST");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        SceneTransition.loadscenefade("/com/example/RedSet/Lattice/showcontestsrunning-view.fxml",event,pending,"CONTEST");
    }

    @FXML
    void standingBtn(MouseEvent event) throws IOException {
//        Stage stage = (Stage) standing.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Notes/showleaderboard.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("RANKING");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        SceneTransition.loadscenefade("/com/example/RedSet/Notes/showleaderboard.fxml",event,standing,"RANKING");
    }

    @FXML
    void studyBtn(MouseEvent event) throws IOException {
//        Stage stage = (Stage) study.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Study/topic.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("STUDY");
//        //stage.setScene(scene);
////        SceneTransition.loadSceneTranslate(scene,stage);
//        stage.centerOnScreen();
//           String title="Study";
          SceneTransition.loadscenefade("/com/example/RedSet/Study/topic.fxml",event,study,"Study");
    }

    @FXML
    void profileBtn(MouseEvent event) throws IOException {
//        Stage stage = (Stage) profile.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Profile/viewProfile.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("PROFILE");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        SceneTransition.loadscenefade("/com/example/RedSet/Profile/viewProfile.fxml",event,profile,"PROFILE");
    }

    @FXML
    void notes(MouseEvent event) throws IOException {
//        Stage stage = (Stage) profile.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MAIN.class.getResource("/com/example/RedSet/Notes/notesview.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("NOTES");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        SceneTransition.loadscenefade("/com/example/RedSet/Notes/notesview.fxml",event,profile,"NOTES");
    }

    @FXML
    void contestBtn(MouseEvent event) throws IOException {
//        Stage stage = (Stage) lattice.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/RedSet/Lattice/showcontestsupcoming-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("CONTEST");
//        stage.setScene(scene);
//        stage.centerOnScreen();
        SceneTransition.loadscenefade("/com/example/RedSet/Lattice/showcontestsupcoming-view.fxml",event,lattice,"CONTEST");

    }


    @FXML
    void about(MouseEvent event) {
        openWebpage.open("https://github.com/Tamal267/RedSet/tree/main");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis) {
            @Override
            public String toString(Number object) {
                return String.format("%.0f", object);
            }
        });
        List<String> timeInfo = new ArrayList<>();
        try {
            Connection connection = DBconnect.getConnect();
            File file = new File("userinfo.txt");
            Scanner scc = new Scanner(file);
            usname = scc.next();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM `users` WHERE username='" + usname + "';");
            ResultSet resultSet = preparedStatement.executeQuery();
            String user = null;
            if (resultSet.next()) {
                String timeDetails = resultSet.getString("time");
                String[] finalTimeDetails = timeDetails.split("\\s+");
                timeInfo.addAll(Arrays.asList(finalTimeDetails));
                user = resultSet.getString("username");
            }
            XYChart.Series<Number, Double> lastdays = new XYChart.Series<Number, Double>();
            LocalDateTime localTime = LocalDateTime.now();
            DateTimeFormatter dateformate = DateTimeFormatter.ofPattern("MM dd HH");
            String ans = localTime.format(dateformate);
            Scanner sc = new Scanner(ans);
            int month = sc.nextInt();
            int day = sc.nextInt();
            int hour = sc.nextInt();
            lastdays.setName("Activity of " + usname + " for Last 30 days (Hour vs Day)");
            for (int i = 1; i < 31; i++) {
                double hours = Double.parseDouble(timeInfo.get(day));
                lastdays.getData().add(new XYChart.Data<>(31 - i, hours));
                day--;
                if (day == 0) {
                    day = switch (month) {
                        case 1, 3, 5, 7, 8, 10, 12 -> 31;
                        default -> 30;
                    };
                }
            }
            area.getData().add(lastdays);
            pie.setData(creatingPieChart());
            Font labelFont = Font.font("MS Outlook", FontWeight.BOLD, 15);
            if (hour > 4 && hour < 12) {
                String greet = "Good Morning,";
                greet += user;
                greet += "!";
                intro.setText(greet);
                intro.setFont(labelFont);
            } else if (hour > 12 && hour < 4) {
                String greet = "Good Afternoon,";
                greet += user;
                greet += "!";
                intro.setText(greet);
                intro.setFont(labelFont);
            } else if (hour > 4 && hour < 7) {
                String greet = "Good Evening,";
                greet += user;
                greet += "!";
                intro.setText(greet);
                intro.setFont(labelFont);
            } else {
                String greet = "Welcome Back,";
                greet += user;
                greet += "!";
                intro.setText(greet);
                intro.setFont(labelFont);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DBconnect.getConnect();
            String query = "SELECT * FROM `users`";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<leaderinfo> leaderList = new ArrayList<>();
            while (resultSet.next()) {
                String time = resultSet.getString("time");
                String name = resultSet.getString("username");
                double tm = 0;
                Scanner sc = new Scanner(time);
                while (sc.hasNext()) {
                    tm += sc.nextDouble();
                }
                leaderList.add(new leaderinfo(tm, name));
            }
            leaderList.sort(leaderinfo::comp);
            int iter = 1;
            for(leaderinfo i:leaderList){
                if(Objects.equals(usname, i.getUsernmame())){
                    th.setText(String.valueOf(iter) + " th");
                    point.setText(String.valueOf((int)(1000*i.getTime())));
                    unit.setText("IN LEADERBOARD");
                    break;
                }
                iter++;
            }
        } catch(SQLException e){
            System.out.println(e);
        }
    }

    private ObservableList<PieChart.Data> creatingPieChart() throws SQLException {
        ObservableList<PieChart.Data> pieDate = FXCollections.observableArrayList();
        Connection connection = DBconnect.getConnect();
        String query = "SELECT * FROM `studyTopic`";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<String, Integer> map = new HashMap<>();
        int cnt = 0;
        while(resultSet.next()){
            String topicName = resultSet.getString("topicName");
            String problemids = resultSet.getString(("problemids"));
            Scanner sc = new Scanner(problemids);
            int c = 0;
            while(sc.hasNext()){
                String temp = sc.next();
                c++;
            }
            cnt += c;
            map.put(topicName, c);
            pieDate.add(new PieChart.Data(topicName, c));
            Label label = new Label(topicName + " : " + c);
            vbox.getChildren().add(label);
        }
        return pieDate;
    }
}
