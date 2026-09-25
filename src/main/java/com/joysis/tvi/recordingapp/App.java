package com.joysis.tvi.recordingapp;

import com.joysis.tvi.recordingapp.config.DbConnection;
import com.joysis.tvi.recordingapp.controller.ArtistController;
import com.joysis.tvi.recordingapp.repository.ArtistRepo;
import com.joysis.tvi.recordingapp.repository.ArtistRepoImpl;
import com.joysis.tvi.recordingapp.service.ArtistService;
import com.joysis.tvi.recordingapp.service.ArtistServiceImpl;
import com.joysis.tvi.recordingapp.view.ArtistView;

import java.util.Scanner;

// Manual testing entry point — focused on Artist only for now.
// Song / Album / Playlist / User wiring removed temporarily until we're
// done verifying Artist works end-to-end.
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DbConnection dbConnection = new DbConnection();

        // ----- Artist feature wiring -----
        ArtistRepo artistRepository = new ArtistRepoImpl(dbConnection);
        ArtistService artistService = new ArtistServiceImpl(artistRepository);
        ArtistController artistController = new ArtistController(artistService);
        ArtistView artistView = new ArtistView(artistController, scanner);

        // Straight into Artist Management — no main menu needed yet
        artistView.run();

        scanner.close();
    }
}