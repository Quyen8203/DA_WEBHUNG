package com.example.be_MrHung.controller;


import com.example.be_MrHung.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/schedule")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/movie/{movieId}/cinema/{cinemaId}")
//    public List<Schedule> getSchedulesByMovieAndCinema(
//            @PathVariable Integer movieId,
//            @PathVariable Integer cinemaId) {
//
//        return scheduleService.getSchedulesByMovieAndCinema(movieId, cinemaId);
//    }
    public ResponseEntity<?> getSchedulesByMovieAndCinema(@PathVariable Integer movieId,
           @PathVariable Integer cinemaId){
        return ResponseEntity.ok(scheduleService.getSchedulesByMovieAndCinema(movieId,cinemaId));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedule());
    }

    @GetMapping("/by-movie-cinema-date")
    public ResponseEntity<?> getSchedulesByMovieCinemaAndDate(
            @RequestParam Integer movieId,
            @RequestParam Integer cinemaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {

        return ResponseEntity.ok(scheduleService.getSchedulesByMovieAndCinemaAndDate(movieId,cinemaId,date));
    }

}
