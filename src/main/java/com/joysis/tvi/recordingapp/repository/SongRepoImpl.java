package com.joysis.tvi.recordingapp.repository;

import com.joysis.tvi.recordingapp.config.DbConnection;
import com.joysis.tvi.recordingapp.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepoImpl implements SongRepo {

    private final DbConnection db;

    public SongRepoImpl(DbConnection db) {
        this.db = db;
    }

    @Override
    public List<Song> getAllSongs() {

        List<Song> songs = new ArrayList<>();

        String query = "SELECT * FROM songs WHERE is_archived = 0";

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                Song song = new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("length"),
                        rs.getString("genre"),
                        rs.getInt("album_id")
                );

                songs.add(song);
            }

        } catch (SQLException e) {
            System.out.println("Get All Songs: " + e.getMessage());
        }

        return songs;
    }

    @Override
    public Song readSongById(int id) {

        String query = "SELECT * FROM songs WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("length"),
                        rs.getString("genre"),
                        rs.getInt("album_id")
                );
            }

        } catch (SQLException e) {
            System.out.println("Read Song: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Song> searchSong(String keyword) {

        List<Song> songs = new ArrayList<>();

        String query =
                "SELECT * FROM songs " +
                        "WHERE title LIKE ? AND is_archived = 0";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Song song = new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("length"),
                        rs.getString("genre"),
                        rs.getInt("album_id")
                );

                songs.add(song);
            }

        } catch (SQLException e) {
            System.out.println("Search Song: " + e.getMessage());
        }

        return songs;
    }

    @Override
    public boolean createSong(
            String title,
            int length,
            String genre,
            int albumId) {

        String query =
                "INSERT INTO songs " +
                        "(title, length, genre, album_id) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, title);
            stmt.setInt(2, length);
            stmt.setString(3, genre);
            stmt.setInt(4, albumId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Create Song: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean updateSong(
            String title,
            int length,
            String genre,
            int albumId,
            int id) {

        String query =
                "UPDATE songs " +
                        "SET title = ?, length = ?, genre = ?, album_id = ? " +
                        "WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, title);
            stmt.setInt(2, length);
            stmt.setString(3, genre);
            stmt.setInt(4, albumId);
            stmt.setInt(5, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update Song: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean archiveSong(int id) {

        String query =
                "UPDATE songs SET is_archived = 1 WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Archive Song: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean restoreSong(int id) {

        String query =
                "UPDATE songs SET is_archived = 0 WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Restore Song: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteSong(int id) {

        String query = "DELETE FROM songs WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete Song: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Song> readAllArchivedSongs() {

        List<Song> songs = new ArrayList<>();

        String query =
                "SELECT * FROM songs WHERE is_archived = 1";

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                Song song = new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("length"),
                        rs.getString("genre"),
                        rs.getInt("album_id")
                );

                songs.add(song);
            }

        } catch (SQLException e) {
            System.out.println("Get Archived Songs: " + e.getMessage());
        }

        return songs;
    }
}