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
    private StringProperty timeEasyGame;
    private String attemptsEasyGame;

    public User(String name, String timeEasyGame) {
        this.name = new SimpleStringProperty(this, "name", name);
        this.password = "";
        this.attempts = 0;
        this.entryDate = null;
        this.timeEasyGame = new SimpleStringProperty(this, "timeEasyGame", timeEasyGame);
    }

    public int getAttempts() {return attempts;}

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public StringProperty nameProperty() {return name;}

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public StringProperty timeEasyGameProperty() {return timeEasyGame;}

    public String getAttemptsEasyGame() {return attemptsEasyGame;}

    public void setAttemptsEasyGame(String attemptsEasyGame) {
        this.attemptsEasyGame = attemptsEasyGame;
    }
}
