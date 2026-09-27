package com.joysis.tvi.recordingapp.service;

import com.joysis.tvi.recordingapp.model.Album;

import java.util.List;

public interface AlbumService {

    List<Album> getAllAlbums();

    Album getAlbumById(int id);

    List<Album> searchAlbum(String keyword);

    boolean createAlbum(Album album);

    boolean updateAlbum(Album album);

    boolean archiveAlbum(int id);

    boolean restoreAlbum(int id);

    boolean deleteAlbum(int id);

    List<Album> getAllArchivedAlbums();
}