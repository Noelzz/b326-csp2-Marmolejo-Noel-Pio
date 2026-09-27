package com.joysis.tvi.recordingapp.repository;

import com.joysis.tvi.recordingapp.config.DbConnection;
import com.joysis.tvi.recordingapp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepo {

    private final DbConnection db;

    public UserRepoImpl(DbConnection db) {
        this.db = db;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users WHERE is_archived = 0";

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );

                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Get All Users: " + e.getMessage());
        }

        return users;
    }

    @Override
    public User readUserById(int id) {

        String query = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            System.out.println("Read User: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<User> searchUser(String keyword) {

        List<User> users = new ArrayList<>();

        String query =
                "SELECT * FROM users " +
                        "WHERE username LIKE ? AND is_archived = 0";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );

                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Search User: " + e.getMessage());
        }

        return users;
    }

    @Override
    public boolean createUser(String username, String password) {

        String query =
                "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Create User: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean updateUser(
            String username,
            String password,
            int id) {

        String query =
                "UPDATE users " +
                        "SET username = ?, password = ? " +
                        "WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update User: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean archiveUser(int id) {

        String query =
                "UPDATE users SET is_archived = 1 WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Archive User: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean restoreUser(int id) {

        String query =
                "UPDATE users SET is_archived = 0 WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Restore User: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteUser(int id) {

        String query = "DELETE FROM users WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete User: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<User> readAllArchivedUsers() {

        List<User> users = new ArrayList<>();

        String query =
                "SELECT * FROM users WHERE is_archived = 1";

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );

                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Get Archived Users: " + e.getMessage());
        }

        return users;
    }
}