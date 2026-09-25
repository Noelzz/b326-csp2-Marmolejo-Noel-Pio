package com.joysis.tvi.recordingapp.controller;

import com.joysis.tvi.recordingapp.model.Artist;
import com.joysis.tvi.recordingapp.service.ArtistService;

import java.util.List;

public class ArtistController {

    private final ArtistService artistService;

    // Constructor injection
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    public List<Artist> handleViewAllArtists() {
        return artistService.getAllArtists();
    }

    public Artist handleGetArtistById(int id) {
        return artistService.getArtistById(id);
    }

    public List<Artist> searchArtist(String keyword) {
        return artistService.searchArtist(keyword);
    }

    public boolean handleCreateArtist(Artist artist) {
        return artistService.createArtist(artist);
    }

    public boolean handleUpdateArtist(Artist artist) {
        return artistService.updateArtist(artist);
    }

    public boolean handleArchiveArtist(int id) {
        return artistService.archiveArtist(id);
    }

    public boolean handleRestoreArtist(int id) {
        return artistService.restoreArtist(id);
    }

    public boolean handleDeleteArtist(int id) {
        return artistService.deleteArtist(id);
    }

    public List<Artist> handleViewAllArchivedArtists() {
        return artistService.getAllArchivedArtists();
    }
}