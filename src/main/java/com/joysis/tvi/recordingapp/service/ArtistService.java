package com.joysis.tvi.recordingapp.service;

import com.joysis.tvi.recordingapp.model.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllArtists();
    Artist getArtistById(int id);
    List<Artist> searchArtist(String keyword);
    boolean createArtist(Artist artist);
    boolean updateArtist(Artist artist);
    boolean archiveArtist(int id);
    boolean restoreArtist(int id);
    boolean deleteArtist(int id);
    List<Artist> getAllArchivedArtists();
}