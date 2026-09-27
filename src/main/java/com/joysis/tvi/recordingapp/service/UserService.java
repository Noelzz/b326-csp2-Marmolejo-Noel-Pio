package com.joysis.tvi.recordingapp.service;

import com.joysis.tvi.recordingapp.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    List<User> searchUser(String keyword);

    boolean createUser(User user);

    boolean updateUser(User user);

    boolean archiveUser(int id);

    boolean restoreUser(int id);

    boolean deleteUser(int id);

    List<User> getAllArchivedUsers();
}