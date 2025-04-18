package com.example.be_MrHung.models;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "movie_description")
    private String movieDescription;

    @Column(name = "movie_trailer")
    private String movieTrailer;

    @Column(name = "movie_cens")
    private String movieCens;

    @Column(name = "movie_genres")
    private String movieGenres;

    @Column(name = "movie_release")
    private String movieRelease;

    @Column(name = "movie_lenght")
    private String movieLength;

    @Column(name = "movie_format")
    private String movieFormat;

    @Column(name = "movie_poster")
    private String moviePoster;

    @Column(name = "is_now_showing")
    private boolean isNowShowing = false;

    public Movie() {
    }

    public Movie(String movieName, String movieDescription, String movieTrailer, String movieCens, String movieGenres, String movieRelease, String movieLength, String movieFormat, String moviePoster, boolean isNowShowing) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieTrailer = movieTrailer;
        this.movieCens = movieCens;
        this.movieGenres = movieGenres;
        this.movieRelease = movieRelease;
        this.movieLength = movieLength;
        this.movieFormat = movieFormat;
        this.moviePoster = moviePoster;
        this.isNowShowing = isNowShowing;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieTrailer() {
        return movieTrailer;
    }

    public void setMovieTrailer(String movieTrailer) {
        this.movieTrailer = movieTrailer;
    }

    public String getMovieCens() {
        return movieCens;
    }

    public void setMovieCens(String movieCens) {
        this.movieCens = movieCens;
    }

    public String getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(String movieGenres) {
        this.movieGenres = movieGenres;
    }

    public String getMovieRelease() {
        return movieRelease;
    }

    public void setMovieRelease(String movieRelease) {
        this.movieRelease = movieRelease;
    }

    public String getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(String movieLength) {
        this.movieLength = movieLength;
    }

    public String getMovieFormat() {
        return movieFormat;
    }

    public void setMovieFormat(String movieFormat) {
        this.movieFormat = movieFormat;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public boolean getisNowShowing() {
        return isNowShowing;
    }

    public void setNowShowing(boolean nowShowing) {
        isNowShowing = nowShowing;
    }
}
