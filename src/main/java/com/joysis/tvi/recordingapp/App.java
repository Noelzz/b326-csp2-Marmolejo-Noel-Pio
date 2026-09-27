package com.joysis.tvi.recordingapp;

import com.joysis.tvi.recordingapp.config.DbConnection;
import com.joysis.tvi.recordingapp.controller.*;
import com.joysis.tvi.recordingapp.model.User;
import com.joysis.tvi.recordingapp.repository.*;
import com.joysis.tvi.recordingapp.service.*;
import com.joysis.tvi.recordingapp.view.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        DbConnection dbConnection = new DbConnection();

        // =========================
        // ARTIST
        // =========================

        ArtistRepo artistRepo =
                new ArtistRepoImpl(dbConnection);

        ArtistService artistService =
                new ArtistServiceImpl(artistRepo);

        ArtistController artistController =
                new ArtistController(artistService);

        ArtistView artistView =
                new ArtistView(artistController, scanner);

        // =========================
        // ALBUM
        // =========================

        AlbumRepo albumRepo =
                new AlbumRepoImpl(dbConnection);

        AlbumService albumService =
                new AlbumServiceImpl(albumRepo);

        AlbumController albumController =
                new AlbumController(albumService);

        AlbumView albumView =
                new AlbumView(albumController, scanner);

        // =========================
        // SONG
        // =========================

        SongRepo songRepo =
                new SongRepoImpl(dbConnection);

        SongService songService =
                new SongServiceImpl(songRepo);

        SongController songController =
                new SongController(songService);

        SongView songView =
                new SongView(songController, scanner);

        // =========================
        // PLAYLIST
        // =========================

        PlaylistRepo playlistRepo =
                new PlaylistRepoImpl(dbConnection);

        PlaylistService playlistService =
                new PlaylistServiceImpl(playlistRepo);

        PlaylistController playlistController =
                new PlaylistController(playlistService);

        PlaylistView playlistView =
                new PlaylistView(playlistController, scanner);

        // =========================
        // USER
        // =========================

        UserRepo userRepo =
                new UserRepoImpl(dbConnection);

        UserService userService =
                new UserServiceImpl(userRepo);

        UserController userController =
                new UserController(userService);

        UserView userView =
                new UserView(userController, scanner);

        // =========================
        // LOGIN / REGISTER
        // =========================

        LoginView loginView =
                new LoginView(userController, scanner);

        RegisterView registerView =
                new RegisterView(userController, scanner);

        // =========================
        // ADMIN DASHBOARD
        // =========================

        AdminDashboardView adminDashboard =
                new AdminDashboardView(
                        artistView,
                        albumView,
                        songView,
                        playlistView,
                        userView,
                        scanner
                );

        // =========================
        // USER DASHBOARD
        // =========================

        UserDashboardView userDashboard =
                new UserDashboardView(
                        artistController,
                        albumController,
                        songController,
                        playlistController,
                        scanner
                );

        // =========================
        // MAIN MENU
        // =========================

        int choice = -1;

        do {

            System.out.println();
            System.out.println("=================================");
            System.out.println("      RECORDING STUDIO APP");
            System.out.println("=================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            try {

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:

                        User loggedInUser =
                                loginView.login();

                        if (loggedInUser != null) {

                            if (loggedInUser.getRole()
                                    .equalsIgnoreCase("ADMIN")) {

                                adminDashboard.run(loggedInUser);

                            } else {

                                userDashboard.run(loggedInUser);
                            }
                        }

                        break;

                    case 2:

                        registerView.register();

                        break;

                    case 0:

                        System.out.println();
                        System.out.println("=================================");
                        System.out.println("Thank you for using");
                        System.out.println("Recording Studio App!");
                        System.out.println("Goodbye!");
                        System.out.println("=================================");

                        break;

                    default:

                        System.out.println();
                        System.out.println("Invalid choice.");
                        System.out.println("Please enter 1, 2, or 0.");

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

        scanner.close();
    }
}