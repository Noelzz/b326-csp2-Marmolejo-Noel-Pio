package com.joysis.tvi.recordingapp.repository;

import com.joysis.tvi.recordingapp.model.Song;

import java.util.List;

public interface SongRepo {

    List<Song> getAllSongs();

    Song readSongById(int id);

    List<Song> searchSong(String keyword);

    boolean createSong(
            String title,
            int length,
            String genre,
            int albumId
    );

    boolean updateSong(
            String title,
            int length,
            String genre,
            int albumId,
            int id
    );

    boolean archiveSong(int id);

    boolean restoreSong(int id);

    boolean deleteSong(int id);

    List<Song> readAllArchivedSongs();
}