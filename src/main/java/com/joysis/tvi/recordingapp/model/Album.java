package com.joysis.tvi.recordingapp.model;

public class Album {

    private int id;
    private String name;
    private int year;
    private int artistId;

    public Album(int id, String name, int year, int artistId) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.artistId = artistId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getArtistId() {
        return artistId;
    }
}