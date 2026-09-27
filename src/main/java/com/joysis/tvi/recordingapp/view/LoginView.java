package com.joysis.tvi.recordingapp.view;

import com.joysis.tvi.recordingapp.controller.UserController;
import com.joysis.tvi.recordingapp.model.User;

import java.util.Scanner;

public class LoginView {

    private final UserController userController;
    private final Scanner scanner;

    public LoginView(UserController userController, Scanner scanner) {
        this.userController = userController;
        this.scanner = scanner;
    }

    public User login() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("             LOGIN");
        System.out.println("=================================");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userController.handleLogin(username, password);

        if (user != null) {

            System.out.println();
            System.out.println("Login successful!");
            System.out.println("Welcome, " + user.getUsername() + "!");

            return user;
        }

        System.out.println();
        System.out.println("Invalid username or password.");

        return null;
    }
}