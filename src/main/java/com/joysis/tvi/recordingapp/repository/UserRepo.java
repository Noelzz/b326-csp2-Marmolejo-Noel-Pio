package com.joysis.tvi.recordingapp.repository;

import com.joysis.tvi.recordingapp.model.User;

import java.util.List;

public interface UserRepo {

    List<User> getAllUsers();

    User readUserById(int id);

    List<User> searchUser(String keyword);

    boolean createUser(String username, String password);

    boolean updateUser(String username, String password, int id);

    boolean archiveUser(int id);

    boolean restoreUser(int id);

    boolean deleteUser(int id);

    List<User> readAllArchivedUsers();

    User login(String username, String password);
}