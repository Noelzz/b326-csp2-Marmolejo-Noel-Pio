package com.joysis.tvi.recordingapp.view;

import com.joysis.tvi.recordingapp.controller.AlbumController;
import com.joysis.tvi.recordingapp.controller.ArtistController;
import com.joysis.tvi.recordingapp.controller.PlaylistController;
import com.joysis.tvi.recordingapp.controller.SongController;
import com.joysis.tvi.recordingapp.model.Album;
import com.joysis.tvi.recordingapp.model.Artist;
import com.joysis.tvi.recordingapp.model.Playlist;
import com.joysis.tvi.recordingapp.model.Song;
import com.joysis.tvi.recordingapp.model.User;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserDashboardView {

    private final ArtistController artistController;
    private final AlbumController albumController;
    private final SongController songController;
    private final PlaylistController playlistController;
    private final Scanner scanner;

    public UserDashboardView(
            ArtistController artistController,
            AlbumController albumController,
            SongController songController,
            PlaylistController playlistController,
            Scanner scanner) {

        this.artistController = artistController;
        this.albumController = albumController;
        this.songController = songController;
        this.playlistController = playlistController;
        this.scanner = scanner;
    }

    public void run(User user) {

        int choice = -1;

        do {

            System.out.println();
            System.out.println("=================================");
            System.out.println("          USER DASHBOARD");
            System.out.println("=================================");
            System.out.println("Welcome, " + user.getUsername());
            System.out.println();
            System.out.println("1. View Artists");
            System.out.println("2. Search Artists");
            System.out.println("3. View Albums");
            System.out.println("4. Search Albums");
            System.out.println("5. View Songs");
            System.out.println("6. Search Songs");
            System.out.println("7. My Playlists");
            System.out.println("8. Create Playlist");
            System.out.println("9. Update My Playlist");
            System.out.println("10. Archive My Playlist");
            System.out.println("0. Logout");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            try {

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:

                        viewArtists();

                        break;

                    case 2:

                        searchArtists();

                        break;

                    case 3:

                        viewAlbums();

                        break;

                    case 4:

                        searchAlbums();

                        break;

                    case 5:

                        viewSongs();

                        break;

                    case 6:

                        searchSongs();

                        break;

                    case 7:

                        viewMyPlaylists(user.getId());

                        break;

                    case 8:

                        createPlaylist(user.getId());

                        break;

                    case 9:

                        updatePlaylist(user.getId());

                        break;

                    case 10:

                        archivePlaylist(user.getId());

                        break;

                    case 0:

                        System.out.println();
                        System.out.println("Logging out...");

                        break;

                    default:

                        System.out.println();
                        System.out.println("Invalid choice.");
                        System.out.println(
                                "Please enter a number from 0 to 10."
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

    private void viewArtists() {

        System.out.println();
        System.out.println("========== ARTISTS ==========");

        List<Artist> artists =
                artistController.handleViewAllArtists();

        if (artists.isEmpty()) {

            System.out.println("No artists found.");

            return;
        }

        for (Artist artist : artists) {

            System.out.println(
                    artist.getId()
                            + " - "
                            + artist.getName()
            );
        }
    }

    private void searchArtists() {

        System.out.print("Enter artist name: ");

        String keyword = scanner.nextLine();

        List<Artist> artists =
                artistController.searchArtist(keyword);

        if (artists.isEmpty()) {

            System.out.println("No artists found.");

            return;
        }

        for (Artist artist : artists) {

            System.out.println(
                    artist.getId()
                            + " - "
                            + artist.getName()
            );
        }
    }

    private void viewAlbums() {

        System.out.println();
        System.out.println("========== ALBUMS ==========");

        List<Album> albums =
                albumController.handleViewAllAlbums();

        if (albums.isEmpty()) {

            System.out.println("No albums found.");

            return;
        }

        for (Album album : albums) {

            System.out.println(
                    album.getId()
                            + " - "
                            + album.getName()
                            + " - "
                            + album.getYear()
            );
        }
    }

    private void searchAlbums() {

        System.out.print("Enter album name: ");

        String keyword = scanner.nextLine();

        List<Album> albums =
                albumController.searchAlbum(keyword);

        if (albums.isEmpty()) {

            System.out.println("No albums found.");

            return;
        }

        for (Album album : albums) {

            System.out.println(
                    album.getId()
                            + " - "
                            + album.getName()
                            + " - "
                            + album.getYear()
            );
        }
    }

    private void viewSongs() {

        System.out.println();
        System.out.println("========== SONGS ==========");

        List<Song> songs =
                songController.handleViewAllSongs();

        if (songs.isEmpty()) {

            System.out.println("No songs found.");

            return;
        }

        for (Song song : songs) {

            System.out.println(
                    song.getId()
                            + " - "
                            + song.getTitle()
                            + " - "
                            + song.getGenre()
                            + " - "
                            + song.getLength()
                            + " seconds"
            );
        }
    }

    private void searchSongs() {

        System.out.print("Enter song title: ");

        String keyword = scanner.nextLine();

        List<Song> songs =
                songController.searchSong(keyword);

        if (songs.isEmpty()) {

            System.out.println("No songs found.");

            return;
        }

        for (Song song : songs) {

            System.out.println(
                    song.getId()
                            + " - "
                            + song.getTitle()
                            + " - "
                            + song.getGenre()
            );
        }
    }

    private void viewMyPlaylists(int userId) {

        System.out.println();
        System.out.println("========== MY PLAYLISTS ==========");

        List<Playlist> playlists =
                playlistController.handleViewAllPlaylists();

        boolean found = false;

        for (Playlist playlist : playlists) {

            if (playlist.getUserId() == userId) {

                System.out.println(
                        "ID: "
                                + playlist.getId()
                                + " | Date Created: "
                                + playlist.getDateCreated()
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "You don't have any playlists."
            );
        }
    }

    private void createPlaylist(int userId) {

        System.out.println();
        System.out.println("========== CREATE PLAYLIST ==========");

        System.out.print("Enter date created: ");

        String dateCreated = scanner.nextLine();

        Playlist playlist =
                new Playlist(
                        0,
                        dateCreated,
                        userId
                );

        boolean success =
                playlistController.handleCreatePlaylist(
                        playlist
                );

        if (success) {

            System.out.println(
                    "Playlist created successfully."
            );

        } else {

            System.out.println(
                    "Failed to create playlist."
            );
        }
    }

    private void updatePlaylist(int userId) {

        System.out.println();
        System.out.println("========== UPDATE PLAYLIST ==========");

        System.out.print("Enter playlist ID: ");

        int id;

        try {

            id = scanner.nextInt();
            scanner.nextLine();

        } catch (InputMismatchException e) {

            System.out.println(
                    "Invalid playlist ID."
            );

            scanner.nextLine();

            return;
        }

        Playlist playlist =
                playlistController.handleGetPlaylistById(id);

        if (playlist == null) {

            System.out.println(
                    "Playlist not found."
            );

            return;
        }

        if (playlist.getUserId() != userId) {

            System.out.println(
                    "You can only update your own playlist."
            );

            return;
        }

        System.out.print(
                "Enter new date created: "
        );

        String dateCreated =
                scanner.nextLine();

        Playlist updatedPlaylist =
                new Playlist(
                        id,
                        dateCreated,
                        userId
                );

        boolean success =
                playlistController.handleUpdatePlaylist(
                        updatedPlaylist
                );

        if (success) {

            System.out.println(
                    "Playlist updated successfully."
            );

        } else {

            System.out.println(
                    "Failed to update playlist."
            );
        }
    }

    private void archivePlaylist(int userId) {

        System.out.println();
        System.out.println(
                "========== ARCHIVE PLAYLIST =========="
        );

        System.out.print("Enter playlist ID: ");

        int id;

        try {

            id = scanner.nextInt();
            scanner.nextLine();

        } catch (InputMismatchException e) {

            System.out.println(
                    "Invalid playlist ID."
            );

            scanner.nextLine();

            return;
        }

        Playlist playlist =
                playlistController.handleGetPlaylistById(id);

        if (playlist == null) {

            System.out.println(
                    "Playlist not found."
            );

            return;
        }

        if (playlist.getUserId() != userId) {

            System.out.println(
                    "You can only archive your own playlist."
            );

            return;
        }

        boolean success =
                playlistController.handleArchivePlaylist(id);

        if (success) {

            System.out.println(
                    "Playlist archived successfully."
            );

        } else {

            System.out.println(
                    "Failed to archive playlist."
            );
        }
    }
}