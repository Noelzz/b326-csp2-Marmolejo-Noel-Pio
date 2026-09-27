package com.joysis.tvi.recordingapp.view;

import com.joysis.tvi.recordingapp.controller.AlbumController;
import com.joysis.tvi.recordingapp.model.Album;

import java.util.List;
import java.util.Scanner;

public class AlbumView {

    private final AlbumController albumController;
    private final Scanner scanner;

    public AlbumView(AlbumController albumController, Scanner scanner) {
        this.albumController = albumController;
        this.scanner = scanner;
    }

    public void run() {

        int choice;

        do {

            System.out.println();
            System.out.println("===== ALBUM MENU =====");
            System.out.println("1. View All Albums");
            System.out.println("2. Search Album");
            System.out.println("3. Add Album");
            System.out.println("4. Update Album");
            System.out.println("5. Archive Album");
            System.out.println("6. Restore Album");
            System.out.println("7. Delete Album");
            System.out.println("8. View All Archived Albums");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewAllAlbums();
                    break;

                case 2:
                    searchAlbum();
                    break;

                case 3:
                    addAlbum();
                    break;

                case 4:
                    updateAlbum();
                    break;

                case 5:
                    archiveAlbum();
                    break;

                case 6:
                    restoreAlbum();
                    break;

                case 7:
                    deleteAlbum();
                    break;

                case 8:
                    viewAllArchivedAlbums();
                    break;

                case 0:
                    System.out.println("Returning...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void viewAllAlbums() {

        List<Album> albums = albumController.handleViewAllAlbums();

        printAlbums(albums);
    }

    private void searchAlbum() {

        System.out.print("Enter album name to search: ");
        String keyword = scanner.nextLine();

        List<Album> albums = albumController.searchAlbum(keyword);

        printAlbums(albums);
    }

    private void addAlbum() {

        System.out.print("Enter album name: ");
        String name = scanner.nextLine();

        System.out.print("Enter album year: ");
        int year = scanner.nextInt();

        System.out.print("Enter artist ID: ");
        int artistId = scanner.nextInt();
        scanner.nextLine();

        Album album = new Album(
                0,
                name,
                year,
                artistId
        );

        boolean success = albumController.handleCreateAlbum(album);

        if (success) {
            System.out.println("Album added successfully.");
        } else {
            System.out.println("Failed to add album.");
        }
    }

    private void updateAlbum() {

        System.out.print("Enter album ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new album name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new album year: ");
        int year = scanner.nextInt();

        System.out.print("Enter new artist ID: ");
        int artistId = scanner.nextInt();
        scanner.nextLine();

        Album album = new Album(
                id,
                name,
                year,
                artistId
        );

        boolean success = albumController.handleUpdateAlbum(album);

        if (success) {
            System.out.println("Album updated successfully.");
        } else {
            System.out.println("Failed to update album.");
        }
    }

    private void archiveAlbum() {

        System.out.print("Enter album ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success = albumController.handleArchiveAlbum(id);

        if (success) {
            System.out.println("Album archived successfully.");
        } else {
            System.out.println("Failed to archive album.");
        }
    }

    private void restoreAlbum() {

        System.out.print("Enter album ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success = albumController.handleRestoreAlbum(id);

        if (success) {
            System.out.println("Album restored successfully.");
        } else {
            System.out.println("Failed to restore album.");
        }
    }

    private void deleteAlbum() {

        System.out.print("Enter album ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success = albumController.handleDeleteAlbum(id);

        if (success) {
            System.out.println("Album deleted successfully.");
        } else {
            System.out.println("Failed to delete album.");
        }
    }

    private void viewAllArchivedAlbums() {

        List<Album> albums = albumController.handleViewAllArchivedAlbums();

        printAlbums(albums);
    }

    private void printAlbums(List<Album> albums) {

        if (albums.isEmpty()) {
            System.out.println("No albums found.");
            return;
        }

        System.out.println();
        System.out.println("===== ALBUMS =====");

        for (Album album : albums) {

            System.out.println(
                    "ID: " + album.getId() +
                            " | Name: " + album.getName() +
                            " | Year: " + album.getYear() +
                            " | Artist ID: " + album.getArtistId()
            );
        }
    }
}