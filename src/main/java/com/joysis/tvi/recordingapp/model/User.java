package com.joysis.tvi.recordingapp.model;

public class User {

    private int id;
    private String username;
    private String password;
    private String role;

    // Constructor for registering a new user
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "USER";
    }

    // Existing constructor used by your old code
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = "USER";
    }

    // Constructor for reading a user with role from database
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}