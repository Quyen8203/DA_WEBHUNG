package com.example.be_MrHung.controller;


import com.example.be_MrHung.eNum.ResponseData;
import com.example.be_MrHung.models.Movie;
import com.example.be_MrHung.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieSevice;


    @GetMapping("/list")
    public ResponseEntity<?> getAllMovies(){
        return ResponseEntity.ok(movieSevice.getAllMovie());
    }


    @GetMapping("/now-showing")
    public ResponseEntity<?> getMoviesNow(){
        return ResponseEntity.ok(movieSevice.getMoviesNow());
    }


    @GetMapping("/comming-soon")
    public ResponseEntity<?> getMoviesFuture(){
        return ResponseEntity.ok(movieSevice.getMoviesFuture());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMovie(@RequestBody Movie movie){
        ResponseData<Movie> response = movieSevice.createMovies(movie);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Movie>> getMovieDetail(@PathVariable int id) {
        ResponseData<Movie> response = movieSevice.getMovieDetail(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
