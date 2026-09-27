package com.joysis.tvi.recordingapp.controller;

import com.joysis.tvi.recordingapp.model.Song;
import com.joysis.tvi.recordingapp.service.SongService;

import java.util.List;

public class SongController {

    private final SongService songService;

    // Constructor injection
    public SongController(SongService songService) {
        this.songService = songService;
    }

    public List<Song> handleViewAllSongs() {
        return songService.getAllSongs();
    }

    public Song handleGetSongById(int id) {
        return songService.getSongById(id);
    }

    public List<Song> searchSong(String keyword) {
        return songService.searchSong(keyword);
    }

    public boolean handleCreateSong(Song song) {
        return songService.createSong(song);
    }

    public boolean handleUpdateSong(Song song) {
        return songService.updateSong(song);
    }

    public boolean handleArchiveSong(int id) {
        return songService.archiveSong(id);
    }

    public boolean handleRestoreSong(int id) {
        return songService.restoreSong(id);
    }

    public boolean handleDeleteSong(int id) {
        return songService.deleteSong(id);
    }

    public List<Song> handleViewAllArchivedSongs() {
        return songService.getAllArchivedSongs();
    }
}