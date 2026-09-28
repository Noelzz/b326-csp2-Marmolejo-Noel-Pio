package com.joysis.tvi.recordingapp.controller;

import com.joysis.tvi.recordingapp.model.User;
import com.joysis.tvi.recordingapp.service.UserService;

import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> handleViewAllUsers() {
        return userService.getAllUsers();
    }

    public User handleViewUserById(int id) {
        return userService.readUserById(id);
    }

    public List<User> handleSearchUser(String keyword) {
        return userService.searchUser(keyword);
    }

    public boolean handleCreateUser(User user) {
        return userService.createUser(
                user.getUsername(),
                user.getPassword()
        );
    }

    public boolean handleUpdateUser(User user) {
        return userService.updateUser(
                user.getUsername(),
                user.getPassword(),
                user.getId()
        );
    }

    public boolean handleArchiveUser(int id) {
        return userService.archiveUser(id);
    }

    public boolean handleRestoreUser(int id) {
        return userService.restoreUser(id);
    }

    public boolean handleDeleteUser(int id) {
        return userService.deleteUser(id);
    }

    public List<User> handleViewAllArchivedUsers() {
        return userService.readAllArchivedUsers();
    }

    public User handleLogin(String username, String password) {
        return userService.login(username, password);
    }
}