package com.example.RedSet.Ranking;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeamRankingController implements Initializable {

    @FXML
    private VBox TeamInfo;
    @FXML
    private AnchorPane setHeight;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<TeamInformation> contacts = new ArrayList<>(contacts());
        setHeight.setMaxHeight(65 * contacts.size());
        for(int i = 0; i < contacts.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/RedSet/Ranking/sampleTeamRanking.fxml"));
            try {
                AnchorPane box = fxmlLoader.load();
                sampleTeamRankingController cic = fxmlLoader.getController();
                cic.setData(contacts.get(i));
                TeamInfo.getChildren().add(box);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
    private List<TeamInformation> contacts(){
        List<TeamInformation> ls = new ArrayList<>();
        TeamInformation contacts = new TeamInformation();

        contacts.setTeamName("MIST1");
        contacts.setPenalty("08");
        contacts.setTotalSolve("09");
        contacts.setPosition("0");
        ls.add(contacts);

        contacts = new TeamInformation();
        contacts.setTeamName("MIST2");
        contacts.setPenalty("08");
        contacts.setTotalSolve("09");
        contacts.setPosition("0");
        ls.add(contacts);

        contacts = new TeamInformation();
        contacts.setTeamName("MIST3");
        contacts.setPenalty("08");
        contacts.setTotalSolve("09");
        contacts.setPosition("0");
        ls.add(contacts);

        contacts = new TeamInformation();
        contacts.setTeamName("MIST4");
        contacts.setPenalty("08");
        contacts.setTotalSolve("09");
        contacts.setPosition("0");
        ls.add(contacts);

        contacts = new TeamInformation();
        contacts.setTeamName("MIST5");
        contacts.setPenalty("08");
        contacts.setTotalSolve("09");
        contacts.setPosition("0");
        ls.add(contacts);

        contacts.setTeamName("MIST6");
        contacts.setPenalty("08");
        contacts.setTotalSolve("09");
        contacts.setPosition("0");
        ls.add(contacts);

        contacts = new TeamInformation();
        contacts.setTeamName("MIST7");
        contacts.setPenalty("08");
        contacts.setTotalSolve("09");
        contacts.setPosition("0");
        ls.add(contacts);

        contacts = new TeamInformation();
        contacts.setTeamName("MIST8");
        contacts.setPenalty("08");
        contacts.setTotalSolve("09");
        contacts.setPosition("0");
        ls.add(contacts);

        contacts = new TeamInformation();
        contacts.setTeamName("MIST9");
        contacts.setPenalty("08");
        contacts.setTotalSolve("09");
        contacts.setPosition("0");
        ls.add(contacts);

        contacts = new TeamInformation();
        contacts.setTeamName("MIST10");
        contacts.setPenalty("08");
        contacts.setTotalSolve("09");
        contacts.setPosition("0");
        ls.add(contacts);
        return ls;
    }
}
