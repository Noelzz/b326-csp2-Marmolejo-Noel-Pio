package com.joysis.tvi.recordingapp.service;

import com.joysis.tvi.recordingapp.model.Song;
import com.joysis.tvi.recordingapp.repository.SongRepo;

import java.util.List;

public class SongServiceImpl implements SongService {

    private final SongRepo songRepo;

    // Constructor injection
    public SongServiceImpl(SongRepo songRepo) {
        this.songRepo = songRepo;
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepo.getAllSongs();
    }

    @Override
    public Song getSongById(int id) {

        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return null;
        }

        Song song = songRepo.readSongById(id);

        if (song == null) {
            System.out.println("Song not found.");
        }

        return song;
    }

    @Override
    public List<Song> searchSong(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return songRepo.searchSong(keyword.trim());
    }

    @Override
    public boolean createSong(Song song) {

        if (song == null) {
            System.out.println("Song object cannot be null.");
            return false;
        }

        if (song.getTitle() == null || song.getTitle().trim().isEmpty()) {
            System.out.println("Song title is required.");
            return false;
        }

        if (song.getLength() <= 0) {
            System.out.println("Invalid song length.");
            return false;
        }

        if (song.getGenre() == null || song.getGenre().trim().isEmpty()) {
            System.out.println("Song genre is required.");
            return false;
        }

        if (song.getAlbumId() <= 0) {
            System.out.println("Invalid album ID.");
            return false;
        }

        return songRepo.createSong(
                song.getTitle().trim(),
                song.getLength(),
                song.getGenre().trim(),
                song.getAlbumId()
        );
    }

    @Override
    public boolean updateSong(Song song) {

        if (song == null || song.getId() <= 0) {
            System.out.println("Invalid song data for update.");
            return false;
        }

        if (song.getTitle() == null || song.getTitle().trim().isEmpty()) {
            System.out.println("Song title cannot be empty.");
            return false;
        }

        if (song.getLength() <= 0) {
            System.out.println("Invalid song length.");
            return false;
        }

        if (song.getGenre() == null || song.getGenre().trim().isEmpty()) {
            System.out.println("Song genre cannot be empty.");
            return false;
        }

        if (song.getAlbumId() <= 0) {
            System.out.println("Invalid album ID.");
            return false;
        }

        return songRepo.updateSong(
                song.getTitle().trim(),
                song.getLength(),
                song.getGenre().trim(),
                song.getAlbumId(),
                song.getId()
        );
    }

    @Override
    public boolean archiveSong(int id) {

        if (id <= 0) {
            System.out.println("Invalid song ID for archive.");
            return false;
        }

        return songRepo.archiveSong(id);
    }

    @Override
    public boolean restoreSong(int id) {

        if (id <= 0) {
            System.out.println("Invalid song ID for restore.");
            return false;
        }

        return songRepo.restoreSong(id);
    }

    @Override
    public boolean deleteSong(int id) {

        if (id <= 0) {
            System.out.println("Invalid song ID for deletion.");
            return false;
        }

        return songRepo.deleteSong(id);
    }

    @Override
    public List<Song> getAllArchivedSongs() {
        return songRepo.readAllArchivedSongs();
    }
}