package com.joysis.tvi.recordingapp.repository;

import com.joysis.tvi.recordingapp.model.Playlist;

import java.util.List;

public interface PlaylistRepo {

    List<Playlist> getAllPlaylists();

    Playlist readPlaylistById(int id);

    List<Playlist> searchPlaylist(String keyword);

    boolean createPlaylist(String dateCreated, int userId);

    boolean updatePlaylist(String dateCreated, int userId, int id);

    boolean archivePlaylist(int id);

    boolean restorePlaylist(int id);

    boolean deletePlaylist(int id);

    List<Playlist> readAllArchivedPlaylists();
}