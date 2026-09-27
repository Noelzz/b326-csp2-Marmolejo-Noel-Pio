package com.joysis.tvi.recordingapp.repository;

import com.joysis.tvi.recordingapp.model.Album;

import java.util.List;

public interface AlbumRepo {

    List<Album> getAllAlbums();

    Album readAlbumById(int id);

    List<Album> searchAlbum(String keyword);

    boolean createAlbum(String name, int year, int artistId);

    boolean updateAlbum(String name, int year, int artistId, int id);

    boolean archiveAlbum(int id);

    boolean restoreAlbum(int id);

    boolean deleteAlbum(int id);

    List<Album> readAllArchivedAlbums();
}