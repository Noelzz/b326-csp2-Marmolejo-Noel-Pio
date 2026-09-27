package com.joysis.tvi.recordingapp.service;

import com.joysis.tvi.recordingapp.model.User;
import com.joysis.tvi.recordingapp.repository.UserRepo;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    // Constructor injection
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public User getUserById(int id) {

        if (id <= 0) {
            System.out.println("Invalid user ID.");
            return null;
        }

        User user = userRepo.readUserById(id);

        if (user == null) {
            System.out.println("User not found.");
        }

        return user;
    }

    @Override
    public List<User> searchUser(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return userRepo.searchUser(keyword.trim());
    }

    @Override
    public boolean createUser(User user) {

        if (user == null) {
            System.out.println("User object cannot be null.");
            return false;
        }

        if (user.getUsername() == null ||
                user.getUsername().trim().isEmpty()) {

            System.out.println("Username is required.");
            return false;
        }

        if (user.getPassword() == null ||
                user.getPassword().trim().isEmpty()) {

            System.out.println("Password is required.");
            return false;
        }

        return userRepo.createUser(
                user.getUsername().trim(),
                user.getPassword().trim()
        );
    }

    @Override
    public boolean updateUser(User user) {

        if (user == null || user.getId() <= 0) {
            System.out.println("Invalid user data for update.");
            return false;
        }

        if (user.getUsername() == null ||
                user.getUsername().trim().isEmpty()) {

            System.out.println("Username cannot be empty.");
            return false;
        }

        if (user.getPassword() == null ||
                user.getPassword().trim().isEmpty()) {

            System.out.println("Password cannot be empty.");
            return false;
        }

        return userRepo.updateUser(
                user.getUsername().trim(),
                user.getPassword().trim(),
                user.getId()
        );
    }

    @Override
    public boolean archiveUser(int id) {

        if (id <= 0) {
            System.out.println("Invalid user ID for archive.");
            return false;
        }

        return userRepo.archiveUser(id);
    }

    @Override
    public boolean restoreUser(int id) {

        if (id <= 0) {
            System.out.println("Invalid user ID for restore.");
            return false;
        }

        return userRepo.restoreUser(id);
    }

    @Override
    public boolean deleteUser(int id) {

        if (id <= 0) {
            System.out.println("Invalid user ID for deletion.");
            return false;
        }

        return userRepo.deleteUser(id);
    }

    @Override
    public List<User> getAllArchivedUsers() {
        return userRepo.readAllArchivedUsers();
    }
}