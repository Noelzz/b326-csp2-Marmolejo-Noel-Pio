package com.joysis.tvi.recordingapp.repository;

import com.joysis.tvi.recordingapp.config.DbConnection;
import com.joysis.tvi.recordingapp.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepoImpl implements ArtistRepo {

    private final DbConnection db;

    public ArtistRepoImpl(DbConnection db) {
        this.db = db;
    }

    @Override
    public List<Artist> getAllArtists() {

        List<Artist> artists = new ArrayList<>();

        String query = "SELECT * FROM artists WHERE is_archived = 0";

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getInt("id"),
                        rs.getString("name")
                );

                artists.add(artist);
            }

        } catch (SQLException e) {
            System.out.println("Get All Artists: " + e.getMessage());
        }

        return artists;
    }

    @Override
    public Artist readArtistById(int id) {

        String query = "SELECT * FROM artists WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Artist(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }

        } catch (SQLException e) {
            System.out.println("Read Artist: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Artist> searchArtist(String keyword) {

        List<Artist> artists = new ArrayList<>();

        String query = "SELECT * FROM artists WHERE name LIKE ? AND is_archived = 0";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getInt("id"),
                        rs.getString("name")
                );

                artists.add(artist);
            }

        } catch (SQLException e) {
            System.out.println("Search Artist: " + e.getMessage());
        }

        return artists;
    }

    @Override
    public boolean createArtist(String name) {

        String query = "INSERT INTO artists (name) VALUES (?)";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Create Artist: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean updateArtist(String name, int id) {

        String query = "UPDATE artists SET name = ? WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update Artist: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean archiveArtist(int id) {

        String query = "UPDATE artists SET is_archived = 1 WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Archive Artist: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean restoreArtist(int id) {

        String query = "UPDATE artists SET is_archived = 0 WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Restore Artist: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteArtist(int id) {

        String query = "DELETE FROM artists WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete Artist: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Artist> readAllArchivedArtist() {

        List<Artist> artists = new ArrayList<>();

        String query = "SELECT * FROM artists WHERE is_archived = 1";

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getInt("id"),
                        rs.getString("name")
                );

                artists.add(artist);
            }

        } catch (SQLException e) {
            System.out.println("Get Archived Artists: " + e.getMessage());
        }

        return artists;
    }
}