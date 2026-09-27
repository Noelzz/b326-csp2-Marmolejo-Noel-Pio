package com.joysis.tvi.recordingapp;

import com.joysis.tvi.recordingapp.config.DbConnection;
import com.joysis.tvi.recordingapp.controller.AlbumController;
import com.joysis.tvi.recordingapp.controller.ArtistController;
import com.joysis.tvi.recordingapp.repository.AlbumRepo;
import com.joysis.tvi.recordingapp.repository.AlbumRepoImpl;
import com.joysis.tvi.recordingapp.repository.ArtistRepo;
import com.joysis.tvi.recordingapp.repository.ArtistRepoImpl;
import com.joysis.tvi.recordingapp.service.AlbumService;
import com.joysis.tvi.recordingapp.service.AlbumServiceImpl;
import com.joysis.tvi.recordingapp.service.ArtistService;
import com.joysis.tvi.recordingapp.service.ArtistServiceImpl;
import com.joysis.tvi.recordingapp.view.AlbumView;
import com.joysis.tvi.recordingapp.view.ArtistView;
import com.joysis.tvi.recordingapp.controller.SongController;
import com.joysis.tvi.recordingapp.repository.SongRepo;
import com.joysis.tvi.recordingapp.repository.SongRepoImpl;
import com.joysis.tvi.recordingapp.service.SongService;
import com.joysis.tvi.recordingapp.service.SongServiceImpl;
import com.joysis.tvi.recordingapp.view.SongView;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        DbConnection dbConnection = new DbConnection();

        // ================= ARTIST =================

        ArtistRepo artistRepository =
                new ArtistRepoImpl(dbConnection);

        ArtistService artistService =
                new ArtistServiceImpl(artistRepository);

        ArtistController artistController =
                new ArtistController(artistService);

        ArtistView artistView =
                new ArtistView(artistController, scanner);


        // ================= ALBUM =================

        AlbumRepo albumRepository =
                new AlbumRepoImpl(dbConnection);

        AlbumService albumService =
                new AlbumServiceImpl(albumRepository);

        AlbumController albumController =
                new AlbumController(albumService);

        AlbumView albumView =
                new AlbumView(albumController, scanner);

        // ================= SONG =================

        SongRepo songRepository =
                new SongRepoImpl(dbConnection);

        SongService songService =
                new SongServiceImpl(songRepository);

        SongController songController =
                new SongController(songService);

        SongView songView =
                new SongView(songController, scanner);


        // ================= TEST =================

        //artistView.run();
       // albumView.run();
       songView.run();

        scanner.close();
    }
}