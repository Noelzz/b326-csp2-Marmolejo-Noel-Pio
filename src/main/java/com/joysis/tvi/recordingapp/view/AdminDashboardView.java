package com.joysis.tvi.recordingapp.view;

import com.joysis.tvi.recordingapp.model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminDashboardView {

    private final ArtistView artistView;
    private final AlbumView albumView;
    private final SongView songView;
    private final PlaylistView playlistView;
    private final UserView userView;
    private final Scanner scanner;

    public AdminDashboardView(
            ArtistView artistView,
            AlbumView albumView,
            SongView songView,
            PlaylistView playlistView,
            UserView userView,
            Scanner scanner) {

        this.artistView = artistView;
        this.albumView = albumView;
        this.songView = songView;
        this.playlistView = playlistView;
        this.userView = userView;
        this.scanner = scanner;
    }

    public void run(User user) {

        int choice = -1;

        do {

            System.out.println();
            System.out.println("=================================");
            System.out.println("         ADMIN DASHBOARD");
            System.out.println("=================================");
            System.out.println("Welcome, " + user.getUsername());
            System.out.println();
            System.out.println("1. Manage Artists");
            System.out.println("2. Manage Albums");
            System.out.println("3. Manage Songs");
            System.out.println("4. Manage Playlists");
            System.out.println("5. Manage Users");
            System.out.println("0. Logout");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            try {

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:

                        artistView.run();

                        break;

                    case 2:

                        albumView.run();

                        break;

                    case 3:

                        songView.run();

                        break;

                    case 4:

                        playlistView.run();

                        break;

                    case 5:

                        userView.run();

                        break;

                    case 0:

                        System.out.println();
                        System.out.println("Logging out...");

                        break;

                    default:

                        System.out.println();
                        System.out.println("Invalid choice.");
                        System.out.println(
                                "Please enter a number from 0 to 5."
                        );

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
}