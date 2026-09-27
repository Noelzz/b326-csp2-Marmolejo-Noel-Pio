package com.joysis.tvi.recordingapp.view;

import com.joysis.tvi.recordingapp.controller.PlaylistController;
import com.joysis.tvi.recordingapp.model.Playlist;

import java.util.List;
import java.util.Scanner;

public class PlaylistView {

    private final PlaylistController playlistController;
    private final Scanner scanner;

    public PlaylistView(PlaylistController playlistController, Scanner scanner) {
        this.playlistController = playlistController;
        this.scanner = scanner;
    }

    public void run() {

        int choice;

        do {

            System.out.println();
            System.out.println("===== PLAYLIST MENU =====");
            System.out.println("1. View All Playlists");
            System.out.println("2. Search Playlist");
            System.out.println("3. Add Playlist");
            System.out.println("4. Update Playlist");
            System.out.println("5. Archive Playlist");
            System.out.println("6. Restore Playlist");
            System.out.println("7. Delete Playlist");
            System.out.println("8. View All Archived Playlists");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewAllPlaylists();
                    break;

                case 2:
                    searchPlaylist();
                    break;

                case 3:
                    addPlaylist();
                    break;

                case 4:
                    updatePlaylist();
                    break;

                case 5:
                    archivePlaylist();
                    break;

                case 6:
                    restorePlaylist();
                    break;

                case 7:
                    deletePlaylist();
                    break;

                case 8:
                    viewAllArchivedPlaylists();
                    break;

                case 0:
                    System.out.println("Returning...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void viewAllPlaylists() {

        List<Playlist> playlists =
                playlistController.handleViewAllPlaylists();

        printPlaylists(playlists);
    }

    private void searchPlaylist() {

        System.out.print("Enter date to search: ");
        String keyword = scanner.nextLine();

        List<Playlist> playlists =
                playlistController.searchPlaylist(keyword);

        printPlaylists(playlists);
    }

    private void addPlaylist() {

        System.out.print("Enter date created: ");
        String dateCreated = scanner.nextLine();

        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        Playlist playlist = new Playlist(
                0,
                dateCreated,
                userId
        );

        boolean success =
                playlistController.handleCreatePlaylist(playlist);

        if (success) {
            System.out.println("Playlist added successfully.");
        } else {
            System.out.println("Failed to add playlist.");
        }
    }

    private void updatePlaylist() {

        System.out.print("Enter playlist ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new date created: ");
        String dateCreated = scanner.nextLine();

        System.out.print("Enter new user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        Playlist playlist = new Playlist(
                id,
                dateCreated,
                userId
        );

        boolean success =
                playlistController.handleUpdatePlaylist(playlist);

        if (success) {
            System.out.println("Playlist updated successfully.");
        } else {
            System.out.println("Failed to update playlist.");
        }
    }

    private void archivePlaylist() {

        System.out.print("Enter playlist ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success =
                playlistController.handleArchivePlaylist(id);

        if (success) {
            System.out.println("Playlist archived successfully.");
        } else {
            System.out.println("Failed to archive playlist.");
        }
    }

    private void restorePlaylist() {

        System.out.print("Enter playlist ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success =
                playlistController.handleRestorePlaylist(id);

        if (success) {
            System.out.println("Playlist restored successfully.");
        } else {
            System.out.println("Failed to restore playlist.");
        }
    }

    private void deletePlaylist() {

        System.out.print("Enter playlist ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success =
                playlistController.handleDeletePlaylist(id);

        if (success) {
            System.out.println("Playlist deleted successfully.");
        } else {
            System.out.println("Failed to delete playlist.");
        }
    }

    private void viewAllArchivedPlaylists() {

        List<Playlist> playlists =
                playlistController.handleViewAllArchivedPlaylists();

        printPlaylists(playlists);
    }

    private void printPlaylists(List<Playlist> playlists) {

        if (playlists.isEmpty()) {
            System.out.println("No playlists found.");
            return;
        }

        System.out.println();
        System.out.println("===== PLAYLISTS =====");

        for (Playlist playlist : playlists) {

            System.out.println(
                    "ID: " + playlist.getId() +
                            " | Date Created: " + playlist.getDateCreated() +
                            " | User ID: " + playlist.getUserId()
            );
        }
    }
}