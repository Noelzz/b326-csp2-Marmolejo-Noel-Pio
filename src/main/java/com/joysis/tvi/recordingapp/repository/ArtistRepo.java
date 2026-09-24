package com.joysis.tvi.recordingapp.repository;

import com.joysis.tvi.recordingapp.model.Artist;

import java.util.List;

public interface ArtistRepo {

    List<Artist> getAllArtists();
    Artist readArtistById(int id);
    List<Artist> searchArtist(String keyword);
    boolean createArtist(String name);
    boolean updateArtist(String name, int id);
    boolean archiveArtist(int id);
    boolean restoreArtist(int id);
    boolean deleteArtist(int id);
    List<Artist> readAllArchivedArtist();


}
