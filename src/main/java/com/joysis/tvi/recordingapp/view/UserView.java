package com.joysis.tvi.recordingapp.view;

import com.joysis.tvi.recordingapp.controller.UserController;
import com.joysis.tvi.recordingapp.model.User;

import java.util.InputMismatchException;
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

        int choice = -1;

        do {

            System.out.println();
            System.out.println("=================================");
            System.out.println("          USER MANAGEMENT");
            System.out.println("=================================");
            System.out.println("1. View All Users");
            System.out.println("2. View User By ID");
            System.out.println("3. Search User");
            System.out.println("4. Add User");
            System.out.println("5. Update User");
            System.out.println("6. Archive User");
            System.out.println("7. Restore User");
            System.out.println("8. Delete User");
            System.out.println("9. View Archived Users");
            System.out.println("0. Back");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            try {

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:
                        viewAllUsers();
                        break;

                    case 2:
                        viewUserById();
                        break;

                    case 3:
                        searchUser();
                        break;

                    case 4:
                        addUser();
                        break;

                    case 5:
                        updateUser();
                        break;

                    case 6:
                        archiveUser();
                        break;

                    case 7:
                        restoreUser();
                        break;

                    case 8:
                        deleteUser();
                        break;

                    case 9:
                        viewArchivedUsers();
                        break;

                    case 0:
                        System.out.println();
                        System.out.println("Returning to Admin Dashboard...");
                        break;

                    default:
                        System.out.println();
                        System.out.println("Invalid choice.");
                        System.out.println("Please enter a number from 0 to 9.");
                        break;
                }

            } catch (InputMismatchException e) {

                System.out.println();
                System.out.println("Invalid input.");
                System.out.println("Please enter a number.");

                scanner.nextLine();
                choice = -1;
            }

        } while (choice != 0);
    }

    private void viewAllUsers() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("           ALL USERS");
        System.out.println("=================================");

        List<User> users = userController.handleViewAllUsers();

        printUsers(users);
    }

    private void viewUserById() {

        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        User user = userController.handleViewUserById(id);

        System.out.println();

        if (user != null) {

            System.out.println("=================================");
            System.out.println("             USER");
            System.out.println("=================================");

            System.out.println("ID: " + user.getId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Role: " + user.getRole());

        } else {

            System.out.println("User not found.");
        }
    }

    private void searchUser() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          SEARCH USER");
        System.out.println("=================================");

        System.out.print("Enter username to search: ");
        String keyword = scanner.nextLine();

        List<User> users =
                userController.handleSearchUser(keyword);

        printUsers(users);
    }

    private void addUser() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("            ADD USER");
        System.out.println("=================================");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(
                username,
                password
        );

        boolean success =
                userController.handleCreateUser(user);

        if (success) {

            System.out.println();
            System.out.println("User added successfully.");

        } else {

            System.out.println();
            System.out.println("Failed to add user.");
        }
    }

    private void updateUser() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          UPDATE USER");
        System.out.println("=================================");

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

            System.out.println();
            System.out.println("User updated successfully.");

        } else {

            System.out.println();
            System.out.println("Failed to update user.");
        }
    }

    private void archiveUser() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          ARCHIVE USER");
        System.out.println("=================================");

        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success =
                userController.handleArchiveUser(id);

        if (success) {

            System.out.println();
            System.out.println("User archived successfully.");

        } else {

            System.out.println();
            System.out.println("Failed to archive user.");
        }
    }

    private void restoreUser() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          RESTORE USER");
        System.out.println("=================================");

        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success =
                userController.handleRestoreUser(id);

        if (success) {

            System.out.println();
            System.out.println("User restored successfully.");

        } else {

            System.out.println();
            System.out.println("Failed to restore user.");
        }
    }

    private void deleteUser() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("           DELETE USER");
        System.out.println("=================================");

        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success =
                userController.handleDeleteUser(id);

        if (success) {

            System.out.println();
            System.out.println("User deleted successfully.");

        } else {

            System.out.println();
            System.out.println("Failed to delete user.");
        }
    }

    private void viewArchivedUsers() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("        ARCHIVED USERS");
        System.out.println("=================================");

        List<User> users =
                userController.handleViewAllArchivedUsers();

        printUsers(users);
    }

    private void printUsers(List<User> users) {

        if (users == null || users.isEmpty()) {

            System.out.println("No users found.");
            return;
        }

        System.out.println();
        System.out.println("+------+----------------------+------------+");
        System.out.printf(
                "| %-4s | %-20s | %-10s |%n",
                "ID",
                "Username",
                "Role"
        );
        System.out.println("+------+----------------------+------------+");

        for (User user : users) {

            System.out.printf(
                    "| %-4d | %-20s | %-10s |%n",
                    user.getId(),
                    user.getUsername(),
                    user.getRole()
            );
        }

        System.out.println("+------+----------------------+------------+");
    }
}