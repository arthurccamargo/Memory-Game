package com.memory.Models;

public class User {
    private String name;
    private String password;
    private int attempts;

    public User() {
        this.name = "";
        this.password = "";
        this.attempts = 0;
    }

    public int getAttempts() {return attempts;}

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
