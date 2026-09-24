package com.joysis.tvi.recordingapp.dao;

import com.joysis.tvi.recordingapp.config.DbConnection;

public class ArtistDaoImpl {

    private final DbConnection dbConnection;

    public ArtistDaoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
}
