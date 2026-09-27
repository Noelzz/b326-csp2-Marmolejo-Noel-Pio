package com.joysis.tvi.recordingapp.view;

import com.joysis.tvi.recordingapp.controller.SongController;
import com.joysis.tvi.recordingapp.model.Song;

import java.util.List;
import java.util.Scanner;

public class SongView {

    private final SongController songController;
    private final Scanner scanner;

    public SongView(SongController songController, Scanner scanner) {
        this.songController = songController;
        this.scanner = scanner;
    }

    public void run() {

        int choice;

        do {

            System.out.println();
            System.out.println("===== SONG MENU =====");
            System.out.println("1. View All Songs");
            System.out.println("2. Search Song");
            System.out.println("3. Add Song");
            System.out.println("4. Update Song");
            System.out.println("5. Archive Song");
            System.out.println("6. Restore Song");
            System.out.println("7. Delete Song");
            System.out.println("8. View All Archived Songs");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewAllSongs();
                    break;

                case 2:
                    searchSong();
                    break;

                case 3:
                    addSong();
                    break;

                case 4:
                    updateSong();
                    break;

                case 5:
                    archiveSong();
                    break;

                case 6:
                    restoreSong();
                    break;

                case 7:
                    deleteSong();
                    break;

                case 8:
                    viewAllArchivedSongs();
                    break;

                case 0:
                    System.out.println("Returning...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void viewAllSongs() {

        List<Song> songs = songController.handleViewAllSongs();

        printSongs(songs);
    }

    private void searchSong() {

        System.out.print("Enter song title to search: ");
        String keyword = scanner.nextLine();

        List<Song> songs = songController.searchSong(keyword);

        printSongs(songs);
    }

    private void addSong() {

        System.out.print("Enter song title: ");
        String title = scanner.nextLine();

        System.out.print("Enter song length: ");
        int length = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter song genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter album ID: ");
        int albumId = scanner.nextInt();
        scanner.nextLine();

        Song song = new Song(
                0,
                title,
                length,
                genre,
                albumId
        );

        boolean success = songController.handleCreateSong(song);

        if (success) {
            System.out.println("Song added successfully.");
        } else {
            System.out.println("Failed to add song.");
        }
    }

    private void updateSong() {

        System.out.print("Enter song ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new song title: ");
        String title = scanner.nextLine();

        System.out.print("Enter new song length: ");
        int length = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new song genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter new album ID: ");
        int albumId = scanner.nextInt();
        scanner.nextLine();

        Song song = new Song(
                id,
                title,
                length,
                genre,
                albumId
        );

        boolean success = songController.handleUpdateSong(song);

        if (success) {
            System.out.println("Song updated successfully.");
        } else {
            System.out.println("Failed to update song.");
        }
    }

    private void archiveSong() {

        System.out.print("Enter song ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success = songController.handleArchiveSong(id);

        if (success) {
            System.out.println("Song archived successfully.");
        } else {
            System.out.println("Failed to archive song.");
        }
    }

    private void restoreSong() {

        System.out.print("Enter song ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success = songController.handleRestoreSong(id);

        if (success) {
            System.out.println("Song restored successfully.");
        } else {
            System.out.println("Failed to restore song.");
        }
    }

    private void deleteSong() {

        System.out.print("Enter song ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success = songController.handleDeleteSong(id);

        if (success) {
            System.out.println("Song deleted successfully.");
        } else {
            System.out.println("Failed to delete song.");
        }
    }

    private void viewAllArchivedSongs() {

        List<Song> songs =
                songController.handleViewAllArchivedSongs();

        printSongs(songs);
    }

    private void printSongs(List<Song> songs) {

        if (songs.isEmpty()) {
            System.out.println("No songs found.");
            return;
        }

        System.out.println();
        System.out.println("===== SONGS =====");

        for (Song song : songs) {

            System.out.println(
                    "ID: " + song.getId() +
                            " | Title: " + song.getTitle() +
                            " | Length: " + song.getLength() +
                            " | Genre: " + song.getGenre() +
                            " | Album ID: " + song.getAlbumId()
            );
        }
    }
}