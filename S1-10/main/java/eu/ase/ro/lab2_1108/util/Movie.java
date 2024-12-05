package eu.ase.ro.lab2_1108.util;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {
    private String title;
    private Date releaseDate;
    private int profit;
    private String movieGenre;
    private String platform;

    public Movie(){

    }
    public Movie(String title, Date releaseDate, int profit, String movieGenre, String platform) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.profit = profit;
        this.movieGenre = movieGenre;
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getProfit() {
        return profit;
    }

    public String getPlatform() {
        return platform;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", profit=" + profit +
                ", movieGenre='" + movieGenre + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}