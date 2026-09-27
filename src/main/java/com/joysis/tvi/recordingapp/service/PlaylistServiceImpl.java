package com.joysis.tvi.recordingapp.service;

import com.joysis.tvi.recordingapp.model.Playlist;
import com.joysis.tvi.recordingapp.repository.PlaylistRepo;

import java.util.List;

public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepo playlistRepo;

    // Constructor injection
    public PlaylistServiceImpl(PlaylistRepo playlistRepo) {
        this.playlistRepo = playlistRepo;
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepo.getAllPlaylists();
    }

    @Override
    public Playlist getPlaylistById(int id) {

        if (id <= 0) {
            System.out.println("Invalid playlist ID.");
            return null;
        }

        Playlist playlist = playlistRepo.readPlaylistById(id);

        if (playlist == null) {
            System.out.println("Playlist not found.");
        }

        return playlist;
    }

    @Override
    public List<Playlist> searchPlaylist(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return playlistRepo.searchPlaylist(keyword.trim());
    }

    @Override
    public boolean createPlaylist(Playlist playlist) {

        if (playlist == null) {
            System.out.println("Playlist object cannot be null.");
            return false;
        }

        if (playlist.getDateCreated() == null ||
                playlist.getDateCreated().trim().isEmpty()) {

            System.out.println("Playlist date is required.");
            return false;
        }

        if (playlist.getUserId() <= 0) {
            System.out.println("Invalid user ID.");
            return false;
        }

        return playlistRepo.createPlaylist(
                playlist.getDateCreated().trim(),
                playlist.getUserId()
        );
    }

    @Override
    public boolean updatePlaylist(Playlist playlist) {

        if (playlist == null || playlist.getId() <= 0) {
            System.out.println("Invalid playlist data for update.");
            return false;
        }

        if (playlist.getDateCreated() == null ||
                playlist.getDateCreated().trim().isEmpty()) {

            System.out.println("Playlist date cannot be empty.");
            return false;
        }

        if (playlist.getUserId() <= 0) {
            System.out.println("Invalid user ID.");
            return false;
        }

        return playlistRepo.updatePlaylist(
                playlist.getDateCreated().trim(),
                playlist.getUserId(),
                playlist.getId()
        );
    }

    @Override
    public boolean archivePlaylist(int id) {

        if (id <= 0) {
            System.out.println("Invalid playlist ID for archive.");
            return false;
        }

        return playlistRepo.archivePlaylist(id);
    }

    @Override
    public boolean restorePlaylist(int id) {

        if (id <= 0) {
            System.out.println("Invalid playlist ID for restore.");
            return false;
        }

        return playlistRepo.restorePlaylist(id);
    }

    @Override
    public boolean deletePlaylist(int id) {

        if (id <= 0) {
            System.out.println("Invalid playlist ID for deletion.");
            return false;
        }

        return playlistRepo.deletePlaylist(id);
    }

    @Override
    public List<Playlist> getAllArchivedPlaylists() {
        return playlistRepo.readAllArchivedPlaylists();
    }
}