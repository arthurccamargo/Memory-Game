package com.memory.Models;

import java.time.LocalDate;

public class User {
    private String name;
    private String password;
    private LocalDate entryDate;
    private int attempts;

    public User() {
        this.name = "";
        this.password = "";
        this.attempts = 0;
        this.entryDate = null;
    }

    public int getAttempts() {return attempts;}

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
}
