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
    private StringProperty attemptsEasyGame;

    public User(String name, String timeEasyGame, String attemptsEasyGame) {
        this.name = new SimpleStringProperty(this, "name", name);
        this.password = "";
        this.attempts = 0;
        this.entryDate = null;
        this.timeEasyGame = new SimpleStringProperty(this, "timeEasyGame", timeEasyGame);
        this.attemptsEasyGame = new SimpleStringProperty(this, "attemptsEasyGame", attemptsEasyGame);
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

    public StringProperty attemptsEasyGameProperty() {return attemptsEasyGame;}
}
