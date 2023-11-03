package com.example.RedSet.Lattice;

import java.util.ArrayList;

public class users {
    String username;
    ArrayList<String> connect = new ArrayList<>();

    public users(String username, String password, ArrayList<String> connect) {
        this.username = username;
        this.connect = connect;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getConnect() {
        return connect;
    }

    public void setConnect(ArrayList<String> connect) {
        this.connect = connect;
    }
}