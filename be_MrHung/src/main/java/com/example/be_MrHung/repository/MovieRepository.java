package com.example.be_MrHung.repository;


import com.example.be_MrHung.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("SELECT m FROM Movie m WHERE m.isNowShowing = true")
    List<Movie> getMoviesNow();
    @Query("SELECT m FROM Movie m WHERE m.isNowShowing = false")
    List<Movie> getMoviesFuture();

    Movie findByMovieId(int movieId);
}
