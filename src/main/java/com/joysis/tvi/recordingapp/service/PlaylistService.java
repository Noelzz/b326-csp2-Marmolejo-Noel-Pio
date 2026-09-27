package com.joysis.tvi.recordingapp.service;

import com.joysis.tvi.recordingapp.model.Playlist;

import java.util.List;

public interface PlaylistService {

    List<Playlist> getAllPlaylists();

    Playlist getPlaylistById(int id);

    List<Playlist> searchPlaylist(String keyword);

    boolean createPlaylist(Playlist playlist);

    boolean updatePlaylist(Playlist playlist);

    boolean archivePlaylist(int id);

    boolean restorePlaylist(int id);

    boolean deletePlaylist(int id);

    List<Playlist> getAllArchivedPlaylists();
}