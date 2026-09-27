package com.joysis.tvi.recordingapp.view;

import com.joysis.tvi.recordingapp.controller.UserController;
import com.joysis.tvi.recordingapp.model.User;

import java.util.List;
import java.util.Scanner;

public class UserView {

    private final UserController userController;
    private final Scanner scanner;

    public UserView(UserController userController, Scanner scanner) {
        this.userController = userController;
        this.scanner = scanner;
    }

    public void run() {

        int choice;

        do {

            System.out.println();
            System.out.println("===== USER MENU =====");
            System.out.println("1. View All Users");
            System.out.println("2. Search User");
            System.out.println("3. Add User");
            System.out.println("4. Update User");
            System.out.println("5. Archive User");
            System.out.println("6. Restore User");
            System.out.println("7. Delete User");
            System.out.println("8. View All Archived Users");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewAllUsers();
                    break;

                case 2:
                    searchUser();
                    break;

                case 3:
                    addUser();
                    break;

                case 4:
                    updateUser();
                    break;

                case 5:
                    archiveUser();
                    break;

                case 6:
                    restoreUser();
                    break;

                case 7:
                    deleteUser();
                    break;

                case 8:
                    viewAllArchivedUsers();
                    break;

                case 0:
                    System.out.println("Returning...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void viewAllUsers() {

        List<User> users =
                userController.handleViewAllUsers();

        printUsers(users);
    }

    private void searchUser() {

        System.out.print("Enter username to search: ");
        String keyword = scanner.nextLine();

        List<User> users =
                userController.searchUser(keyword);

        printUsers(users);
    }

    private void addUser() {

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(
                0,
                username,
                password
        );

        boolean success =
                userController.handleCreateUser(user);

        if (success) {
            System.out.println("User added successfully.");
        } else {
            System.out.println("Failed to add user.");
        }
    }

    private void updateUser() {

        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new username: ");
        String username = scanner.nextLine();

        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

        User user = new User(
                id,
                username,
                password
        );

        boolean success =
                userController.handleUpdateUser(user);

        if (success) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Failed to update user.");
        }
    }

    private void archiveUser() {

        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success =
                userController.handleArchiveUser(id);

        if (success) {
            System.out.println("User archived successfully.");
        } else {
            System.out.println("Failed to archive user.");
        }
    }

    private void restoreUser() {

        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success =
                userController.handleRestoreUser(id);

        if (success) {
            System.out.println("User restored successfully.");
        } else {
            System.out.println("Failed to restore user.");
        }
    }

    private void deleteUser() {

        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success =
                userController.handleDeleteUser(id);

        if (success) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user.");
        }
    }

    private void viewAllArchivedUsers() {

        List<User> users =
                userController.handleViewAllArchivedUsers();

        printUsers(users);
    }

    private void printUsers(List<User> users) {

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println();
        System.out.println("===== USERS =====");

        for (User user : users) {

            System.out.println(
                    "ID: " + user.getId() +
                            " | Username: " + user.getUsername() +
                            " | Password: " + user.getPassword()
            );
        }
    }
}