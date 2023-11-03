package com.example.RedSet.Lattice;

public class rankInfo {
    String username;
    long solve, penalty, prepenalty;

    public rankInfo(String username, long solve, long penalty, long prepenalty) {
        this.username = username;
        this.solve = solve;
        this.penalty = penalty;
        this.prepenalty = prepenalty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getSolve() {
        return solve;
    }

    public void setSolve(int solve) {
        this.solve = solve;
    }

    public long getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public long getPrepenalty() {
        return prepenalty;
    }

    public void setPrepenalty(int prepenalty) {
        this.prepenalty = prepenalty;
    }

    public static int comp(rankInfo a, rankInfo b){
        if(a.solve == b.solve){
            return Long.compare(a.penalty, b.penalty);
        }
        else return -Long.compare(a.solve, b.solve);
    }
}
