package com.memory.Models;

import java.sql.*;
import java.time.LocalDate;

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

    public ResultSet getUserExistsData(String username) {
        ResultSet resultSet;
        try {
            String sql = "SELECT * FROM user WHERE user_name= ? ";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getUserTimeData(int id) {
        ResultSet resultSet;
        try {
            String sql = "SELECT * FROM game_time WHERE user_id= ? ";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getUserAttemptsData(int id) {
        ResultSet resultSet;
        try {
            String sql = "SELECT * FROM points WHERE user_id= ? ";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createUser(String username, String password, LocalDate date) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "user (user_name, user_password, entry_date)" +
                    "VALUES ('"+username+"' , '"+password+"', '"+date.toString()+"');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUserTime(int id, String time) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "game_time (user_id, easy_game)" +
                    "VALUES ('"+id+"' , '"+time+"');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUserAttempts(int id, int attempts) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "points (user_id, easy_game)" +
                    "VALUES ('"+id+"' , '"+attempts+"');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserAttempts(int id, int attempts) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("UPDATE points SET easy_game = '"+attempts+"'  WHERE user_id = '"+id+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserTime(int id, String time) {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("UPDATE game_time SET easy_game = '"+time+"'  WHERE user_id = '"+id+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

