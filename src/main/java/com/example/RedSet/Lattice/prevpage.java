package com.example.RedSet.Lattice;

import java.util.Stack;

public class prevpage {
    static Stack<String> prev = new Stack<>();
    private static final prevpage instance = new prevpage();
    public static prevpage getInstance(){
        return instance;
    }

    public String getPrev() {
        return prev.pop();
    }

    public void setPrev(String prev) {
        this.prev.push(prev);
    }

    public static String peek(){
        return prev.peek();
    }
}
