package com.joysis.tvi.recordingapp.controller;

import com.joysis.tvi.recordingapp.model.Album;
import com.joysis.tvi.recordingapp.service.AlbumService;

import java.util.List;

public class AlbumController {

    private final AlbumService albumService;

    // Constructor injection
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    public List<Album> handleViewAllAlbums() {
        return albumService.getAllAlbums();
    }

    public Album handleGetAlbumById(int id) {
        return albumService.getAlbumById(id);
    }

    public List<Album> searchAlbum(String keyword) {
        return albumService.searchAlbum(keyword);
    }

    public boolean handleCreateAlbum(Album album) {
        return albumService.createAlbum(album);
    }

    public boolean handleUpdateAlbum(Album album) {
        return albumService.updateAlbum(album);
    }

    public boolean handleArchiveAlbum(int id) {
        return albumService.archiveAlbum(id);
    }

    public boolean handleRestoreAlbum(int id) {
        return albumService.restoreAlbum(id);
    }

    public boolean handleDeleteAlbum(int id) {
        return albumService.deleteAlbum(id);
    }

    public List<Album> handleViewAllArchivedAlbums() {
        return albumService.getAllArchivedAlbums();
    }
}