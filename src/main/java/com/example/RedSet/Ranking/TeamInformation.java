package com.example.RedSet.Ranking;

public class TeamInformation {
    private String TeamName;
    private String totalSolve;
    private String Penalty;
    private String position;

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getTotalSolve() {
        return totalSolve;
    }

    public void setTotalSolve(String totalSolve) {
        this.totalSolve = totalSolve;
    }

    public String getPenalty() {
        return Penalty;
    }

    public void setPenalty(String penalty) {
        Penalty = penalty;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
