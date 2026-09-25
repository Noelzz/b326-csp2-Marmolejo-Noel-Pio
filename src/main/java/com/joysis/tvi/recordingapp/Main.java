package com.joysis.tvi.recordingapp;
import com.joysis.tvi.recordingapp.config.DbConnection;
import com.joysis.tvi.recordingapp.dao.ArtistDao;
import com.joysis.tvi.recordingapp.model.Artist;
import com.joysis.tvi.recordingapp.repository.ArtistRepo;
import com.joysis.tvi.recordingapp.repository.ArtistRepoImpl;

import java.util.List;

/*
public class Main {

    public static void main(String[] args) {

        DbConnection db = new DbConnection();

        ArtistRepo artistRepo = new ArtistRepoImpl(db);

        List<Artist> artists = artistRepo.getAllArtists();

        for (Artist artist : artists) {
            System.out.println(artist);
        }

    }
}*/
