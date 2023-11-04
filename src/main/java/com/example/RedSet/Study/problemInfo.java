package com.example.RedSet.Study;

public class problemInfo {
    String prbName, statement, timeLimt, acceptedCode, inp = "", solution, users;

    private static final problemInfo instance = new problemInfo();
    public static problemInfo getInstance(){
        return instance;
    }

    public String getPrbName() {
        return prbName;
    }

    public void setPrbName(String prbName) {
        this.prbName = prbName;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getTimeLimt() {
        return timeLimt;
    }

    public void setTimeLimt(String timeLimt) {
        this.timeLimt = timeLimt;
    }

    public String getAcceptedCode() {
        return acceptedCode;
    }

    public void setAcceptedCode(String acceptedCode) {
        this.acceptedCode = acceptedCode;
    }

    public String getInp() {
        return inp;
    }

    public void setInp(String inp) {
        this.inp = inp;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
