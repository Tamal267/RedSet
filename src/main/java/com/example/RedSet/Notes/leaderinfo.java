package com.example.RedSet.Notes;

public class leaderinfo {
    Double time;
    String usernmame;

    public leaderinfo(Double time, String usernmame) {
        this.time = time;
        this.usernmame = usernmame;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getUsernmame() {
        return usernmame;
    }

    public void setUsernmame(String usernmame) {
        this.usernmame = usernmame;
    }
    public static int comp(leaderinfo a, leaderinfo b){
        return -Double.compare(a.time, b.time);
    }
}
