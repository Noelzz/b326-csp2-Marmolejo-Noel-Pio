package com.joysis.tvi.recordingapp.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.joysis.tvi.recordingapp.model.User;
import com.joysis.tvi.recordingapp.repository.UserRepo;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public User readUserById(int id) {
        return userRepo.readUserById(id);
    }

    @Override
    public List<User> searchUser(String keyword) {
        return userRepo.searchUser(keyword);
    }

    @Override
    public boolean createUser(String username, String password) {

        String hashedPassword =
                BCrypt.withDefaults().hashToString(
                        12,
                        password.toCharArray()
                );

        return userRepo.createUser(username, hashedPassword);
    }

    @Override
    public boolean updateUser(String username, String password, int id) {

        String hashedPassword =
                BCrypt.withDefaults().hashToString(
                        12,
                        password.toCharArray()
                );

        return userRepo.updateUser(username, hashedPassword, id);
    }

    @Override
    public boolean archiveUser(int id) {
        return userRepo.archiveUser(id);
    }

    @Override
    public boolean restoreUser(int id) {
        return userRepo.restoreUser(id);
    }

    @Override
    public boolean deleteUser(int id) {
        return userRepo.deleteUser(id);
    }

    @Override
    public List<User> readAllArchivedUsers() {
        return userRepo.readAllArchivedUsers();
    }

    @Override
    public User login(String username, String password) {

        List<User> users = userRepo.searchUser(username);

        for (User user : users) {

            if (user.getUsername().equals(username)) {

                BCrypt.Result result =
                        BCrypt.verifyer().verify(
                                password.toCharArray(),
                                user.getPassword()
                        );

                if (result.verified) {
                    return user;
                }

                return null;
            }
        }

        return null;
    }
}