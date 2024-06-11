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

    public ResultSet getUserData(String username, String password) {
        ResultSet resultSet;
        try {
            String sql = "SELECT * FROM user WHERE user_name= ? AND user_password= ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}

