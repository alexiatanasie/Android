package com.example.ticket1_songs_xml;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SongsDAO {
    @Insert
    void insertSong(Song song);

    @Query("SELECT * FROM songs")
    List<Song>getSongs();

    @Query("SELECT * FROM songs WHERE noOfViews>100000")
    List<Song>getSongsByViews();
}