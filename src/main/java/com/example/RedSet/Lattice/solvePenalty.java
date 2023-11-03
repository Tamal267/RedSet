package com.example.RedSet.Lattice;

public class solvePenalty {
    private int solve, penalty;

    public solvePenalty(int solve, int penalty) {
        this.solve = solve;
        this.penalty = penalty;
    }

    public int getSolve() {
        return solve;
    }

    public void setSolve(int solve) {
        this.solve = solve;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public static int comp(solvePenalty a, solvePenalty b){
        if(a.solve == b.solve) return Integer.compare(a.penalty, b.penalty);
        else return -Integer.compare(a.solve, b.solve);
    }
}
