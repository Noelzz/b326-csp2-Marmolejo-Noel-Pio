package com.joysis.tvi.recordingapp.controller;

import com.joysis.tvi.recordingapp.model.Playlist;
import com.joysis.tvi.recordingapp.service.PlaylistService;

import java.util.List;

public class PlaylistController {

    private final PlaylistService playlistService;

    // Constructor injection
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    public List<Playlist> handleViewAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    public Playlist handleGetPlaylistById(int id) {
        return playlistService.getPlaylistById(id);
    }

    public List<Playlist> searchPlaylist(String keyword) {
        return playlistService.searchPlaylist(keyword);
    }

    public boolean handleCreatePlaylist(Playlist playlist) {
        return playlistService.createPlaylist(playlist);
    }

    public boolean handleUpdatePlaylist(Playlist playlist) {
        return playlistService.updatePlaylist(playlist);
    }

    public boolean handleArchivePlaylist(int id) {
        return playlistService.archivePlaylist(id);
    }

    public boolean handleRestorePlaylist(int id) {
        return playlistService.restorePlaylist(id);
    }

    public boolean handleDeletePlaylist(int id) {
        return playlistService.deletePlaylist(id);
    }

    public List<Playlist> handleViewAllArchivedPlaylists() {
        return playlistService.getAllArchivedPlaylists();
    }
}