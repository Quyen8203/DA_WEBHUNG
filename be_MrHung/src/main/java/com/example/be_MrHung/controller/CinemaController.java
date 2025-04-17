package com.example.be_MrHung.controller;


import com.example.be_MrHung.eNum.ResponseData;
import com.example.be_MrHung.models.Cinema;
import com.example.be_MrHung.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
public class CinemaController {

    @Autowired
    CinemaService cinemaService;

    @GetMapping("list")
    public ResponseEntity<?> getAllCinema(){
        return ResponseEntity.ok(cinemaService.getAllCinema());
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseData<List<Cinema>>> searchCinemasByAddress(
            @RequestParam("city") String address) {

        ResponseData<List<Cinema>> response = cinemaService.searchCinemasByAddress(address);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    //Lấy thông tin rạp theo suất chiếu của phim
    @GetMapping("/{schedule_id}")
    public ResponseEntity<?> getCinemaByScheduleId(@PathVariable Integer schedule_id){
        return ResponseEntity.ok(cinemaService.getCinemaByScheduleId(schedule_id));
    }

    //Lấy rạp phim theo phim và ngày chiếu cụ thể
    @GetMapping("/movie-date")
    public ResponseEntity<ResponseData<List<Cinema>>> getCinemasByMovieAndDate(
            @RequestParam ("movie_id") Integer movie_id,
            @RequestParam("schedule_date") String schedule_date) {
        return ResponseEntity.ok(cinemaService.getCinemasByMovieAndDate(movie_id, schedule_date));
    }

    @GetMapping("/date")
    public ResponseEntity<ResponseData<List<Cinema>>> getCinemasByDate(
            @RequestParam ("movie_id") Integer movie_id,
            @RequestParam("schedule_date") String schedule_date) {
        return ResponseEntity.ok(cinemaService.getCinemasByMovieAndDate(movie_id, schedule_date));
    }
}
