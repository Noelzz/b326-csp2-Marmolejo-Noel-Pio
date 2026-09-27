package com.joysis.tvi.recordingapp.view;

import com.joysis.tvi.recordingapp.controller.UserController;
import com.joysis.tvi.recordingapp.model.User;

import java.util.Scanner;

public class RegisterView {

    private final UserController userController;
    private final Scanner scanner;

    public RegisterView(UserController userController, Scanner scanner) {
        this.userController = userController;
        this.scanner = scanner;
    }

    public void register() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("           REGISTER");
        System.out.println("=================================");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = new User(username, password);

        boolean success = userController.handleCreateUser(user);

        if (success) {
            System.out.println();
            System.out.println("Registration successful!");
            System.out.println("You can now login.");
        } else {
            System.out.println();
            System.out.println("Registration failed.");
        }
    }
}