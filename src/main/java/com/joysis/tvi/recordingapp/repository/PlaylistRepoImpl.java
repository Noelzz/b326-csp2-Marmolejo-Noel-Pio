package com.joysis.tvi.recordingapp.repository;

import com.joysis.tvi.recordingapp.config.DbConnection;
import com.joysis.tvi.recordingapp.model.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepoImpl implements PlaylistRepo {

    private final DbConnection db;

    public PlaylistRepoImpl(DbConnection db) {
        this.db = db;
    }

    @Override
    public List<Playlist> getAllPlaylists() {

        List<Playlist> playlists = new ArrayList<>();

        String query =
                "SELECT * FROM playlists WHERE is_archived = 0";

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                Playlist playlist = new Playlist(
                        rs.getInt("id"),
                        rs.getString("date_created"),
                        rs.getInt("user_id")
                );

                playlists.add(playlist);
            }

        } catch (SQLException e) {
            System.out.println("Get All Playlists: " + e.getMessage());
        }

        return playlists;
    }

    @Override
    public Playlist readPlaylistById(int id) {

        String query = "SELECT * FROM playlists WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Playlist(
                        rs.getInt("id"),
                        rs.getString("date_created"),
                        rs.getInt("user_id")
                );
            }

        } catch (SQLException e) {
            System.out.println("Read Playlist: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Playlist> searchPlaylist(String keyword) {

        List<Playlist> playlists = new ArrayList<>();

        String query =
                "SELECT * FROM playlists " +
                        "WHERE date_created LIKE ? " +
                        "AND is_archived = 0";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Playlist playlist = new Playlist(
                        rs.getInt("id"),
                        rs.getString("date_created"),
                        rs.getInt("user_id")
                );

                playlists.add(playlist);
            }

        } catch (SQLException e) {
            System.out.println("Search Playlist: " + e.getMessage());
        }

        return playlists;
    }

    @Override
    public boolean createPlaylist(String dateCreated, int userId) {

        String query =
                "INSERT INTO playlists (date_created, user_id) " +
                        "VALUES (?, ?)";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dateCreated);
            stmt.setInt(2, userId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Create Playlist: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean updatePlaylist(
            String dateCreated,
            int userId,
            int id) {

        String query =
                "UPDATE playlists " +
                        "SET date_created = ?, user_id = ? " +
                        "WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dateCreated);
            stmt.setInt(2, userId);
            stmt.setInt(3, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update Playlist: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean archivePlaylist(int id) {

        String query =
                "UPDATE playlists SET is_archived = 1 WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Archive Playlist: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean restorePlaylist(int id) {

        String query =
                "UPDATE playlists SET is_archived = 0 WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Restore Playlist: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean deletePlaylist(int id) {

        String query = "DELETE FROM playlists WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete Playlist: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Playlist> readAllArchivedPlaylists() {

        List<Playlist> playlists = new ArrayList<>();

        String query =
                "SELECT * FROM playlists WHERE is_archived = 1";

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                Playlist playlist = new Playlist(
                        rs.getInt("id"),
                        rs.getString("date_created"),
                        rs.getInt("user_id")
                );

                playlists.add(playlist);
            }

        } catch (SQLException e) {
            System.out.println("Get Archived Playlists: " + e.getMessage());
        }

        return playlists;
    }
}