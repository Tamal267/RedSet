package com.example.RedSet.Lattice;

public class startEndTime {
    String start = "", end = "", contestName = "";
    private static final startEndTime instance = new startEndTime();

    public static startEndTime getInstance(){
        return instance;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }
}
