package com.example.RedSet.Lattice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
    private static final String url = "jdbc:mysql://localhost:3306/lattice";

    private static final String username = "eagle";
    private static final String password = "eagle";

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
