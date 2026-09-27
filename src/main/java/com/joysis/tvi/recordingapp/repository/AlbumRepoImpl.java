package com.joysis.tvi.recordingapp.repository;

import com.joysis.tvi.recordingapp.config.DbConnection;
import com.joysis.tvi.recordingapp.model.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepoImpl implements AlbumRepo {

    private final DbConnection db;

    public AlbumRepoImpl(DbConnection db) {
        this.db = db;
    }

    @Override
    public List<Album> getAllAlbums() {

        List<Album> albums = new ArrayList<>();

        String query = "SELECT * FROM albums WHERE is_archived = 0";

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                Album album = new Album(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getInt("artist_id")
                );

                albums.add(album);
            }

        } catch (SQLException e) {
            System.out.println("Get All Albums: " + e.getMessage());
        }

        return albums;
    }

    @Override
    public Album readAlbumById(int id) {

        String query = "SELECT * FROM albums WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Album(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getInt("artist_id")
                );
            }

        } catch (SQLException e) {
            System.out.println("Read Album: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Album> searchAlbum(String keyword) {

        List<Album> albums = new ArrayList<>();

        String query =
                "SELECT * FROM albums " +
                        "WHERE name LIKE ? AND is_archived = 0";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Album album = new Album(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getInt("artist_id")
                );

                albums.add(album);
            }

        } catch (SQLException e) {
            System.out.println("Search Album: " + e.getMessage());
        }

        return albums;
    }

    @Override
    public boolean createAlbum(String name, int year, int artistId) {

        String query =
                "INSERT INTO albums (name, year, artist_id) " +
                        "VALUES (?, ?, ?)";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setInt(2, year);
            stmt.setInt(3, artistId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Create Album: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean updateAlbum(
            String name,
            int year,
            int artistId,
            int id) {

        String query =
                "UPDATE albums " +
                        "SET name = ?, year = ?, artist_id = ? " +
                        "WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setInt(2, year);
            stmt.setInt(3, artistId);
            stmt.setInt(4, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update Album: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean archiveAlbum(int id) {

        String query =
                "UPDATE albums SET is_archived = 1 WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Archive Album: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean restoreAlbum(int id) {

        String query =
                "UPDATE albums SET is_archived = 0 WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Restore Album: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteAlbum(int id) {

        String query = "DELETE FROM albums WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete Album: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Album> readAllArchivedAlbums() {

        List<Album> albums = new ArrayList<>();

        String query =
                "SELECT * FROM albums WHERE is_archived = 1";

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                Album album = new Album(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getInt("artist_id")
                );

                albums.add(album);
            }

        } catch (SQLException e) {
            System.out.println("Get Archived Albums: " + e.getMessage());
        }

        return albums;
    }
}