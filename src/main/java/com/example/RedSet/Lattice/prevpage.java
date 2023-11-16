package com.example.RedSet.Lattice;

public class prevpage {
    String prev;
    private static final prevpage instance = new prevpage();
    public static prevpage getInstance(){
        return instance;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }
}
