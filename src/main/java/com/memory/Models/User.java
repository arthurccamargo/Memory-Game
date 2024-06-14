package com.memory.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class User {
    private int id;
    private StringProperty name;
    private String password;
    private LocalDate entryDate;
    private int attempts;
    private String timeEasyGame;
    private String attemptsEasyGame;

    public User(String name) {
        this.name = new SimpleStringProperty(this, "name", name);
        this.password = "";
        this.attempts = 0;
        this.entryDate = null;
    }

    public int getAttempts() {return attempts;}

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public StringProperty getName() {return name;}

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeEasyGame() {return timeEasyGame;}

    public void setTimeEasyGame(String timeEasyGame) {
        this.timeEasyGame = timeEasyGame;
    }

    public String getAttemptsEasyGame() {return attemptsEasyGame;}

    public void setAttemptsEasyGame(String attemptsEasyGame) {
        this.attemptsEasyGame = attemptsEasyGame;
    }
}
