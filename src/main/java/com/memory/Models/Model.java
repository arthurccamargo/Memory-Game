package com.memory.Models;

import com.memory.Views.ViewFactory;

import java.sql.ResultSet;
import java.time.LocalDate;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final GameTimer timer;
    private final Board board;
    private final User user;
    private int startGame;
    private final DatabaseDriver databaseDriver;
    private boolean userLoginSuccessFlag;


    private Model() {
        this.board = new Board(4, 4);
        this.viewFactory = new ViewFactory();
        this.timer = new GameTimer();
        this.user = new User();
        this.startGame = 0;
        this.databaseDriver = new DatabaseDriver();
        this.userLoginSuccessFlag = false;
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {return viewFactory;}

    public GameTimer getGameTimer() {return timer;}

    public Board getBoard() {return board;}

    public User getUser() {return user;}

    public int getStartGame() {return startGame;}

    public void setStartGame(int startGame) {
        this.startGame = startGame;
    }

    public DatabaseDriver getDatabaseDriver() {return databaseDriver;}

    public boolean getUserLoginSuccessFlag() {return userLoginSuccessFlag;}

    public void setUserLoginSuccessFlag(boolean userLoginSuccessFlag) {
        this.userLoginSuccessFlag = userLoginSuccessFlag;
    }

    public void evaluateUserCred(String username, String password) {
        ResultSet resultSet = databaseDriver.getUserData(username, password);
        try {
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    this.user.setId(resultSet.getInt("id"));
                    this.user.setName(resultSet.getString("user_name"));
                    String[] dateParts = resultSet.getString("entry_date").split("-");
                    LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]),
                            Integer.parseInt(dateParts[2]));
                    this.user.setEntryDate(date);
                    this.userLoginSuccessFlag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean userExists(String username) {
        ResultSet resultSet = databaseDriver.getUserExistsData(username);
        try {
            return resultSet.isBeforeFirst();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void userTimes(int id) {
        ResultSet resultSet = databaseDriver.getUserTimeData(id);
        try {
            if (resultSet.isBeforeFirst()) {
                this.user.setTimeEasyGame(resultSet.getString("easy_game"));
            } else {
                this.user.setTimeEasyGame("00:00");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
