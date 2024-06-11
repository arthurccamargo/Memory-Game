package com.memory.Models;

import java.sql.*;


public class DatabaseDriver {
    private Connection connection;

    public DatabaseDriver() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:memorygame.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

