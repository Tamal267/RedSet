package com.example.RedSet.Lattice;

public class contestInfo {
    private static final contestInfo instance = new contestInfo();
    private String contestName = "", startTime = "", duration = "", problemsIds = "", groupName = "", ranking = "";

    public static contestInfo getInstance(){
        return instance;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getProblemsIds() {
        return problemsIds;
    }

    public void setProblemsIds(String problemsIds) {
        this.problemsIds = problemsIds;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
}
