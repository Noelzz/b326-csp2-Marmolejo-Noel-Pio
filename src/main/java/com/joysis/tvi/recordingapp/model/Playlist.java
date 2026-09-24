package com.joysis.tvi.recordingapp.model;

public class Playlist {

    private int id;
    private String dateCreated;
    private int userId;

    public Playlist(int id, String dateCreated, int userId) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public int getUserId() {
        return userId;
    }
}