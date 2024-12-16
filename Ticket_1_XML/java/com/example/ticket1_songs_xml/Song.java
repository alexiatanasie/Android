package com.example.ticket1_songs_xml;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "songs")
public class Song {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String songTitle;
    private String artist;
    private int noOfViews;
    private Date songReleaseDate;

    public Song(String songTitle, String artist, int noOfViews, Date songReleaseDate) {
        this.songTitle = songTitle;
        this.artist = artist;
        this.noOfViews = noOfViews;
        this.songReleaseDate = songReleaseDate;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songTitle='" + songTitle + '\'' +
                ", artist='" + artist + '\'' +
                ", noOfViews=" + noOfViews +
                ", songReleaseDate=" + songReleaseDate +
                '}';
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        if(songTitle==null)
            throw new IllegalArgumentException("invalid title");
        this.songTitle = songTitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getNoOfViews() {
        return noOfViews;
    }

    public void setNoOfViews(int noOfViews) {
        if(noOfViews<0)
            throw new IllegalArgumentException("no of views should be >0" );
        this.noOfViews = noOfViews;
    }

    public Date getSongReleaseDate() {
        return songReleaseDate;
    }

    public void setSongReleaseDate(Date songReleaseDate) {
        this.songReleaseDate = songReleaseDate;
    }
}