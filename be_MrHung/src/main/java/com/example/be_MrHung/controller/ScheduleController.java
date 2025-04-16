package com.example.be_MrHung.controller;

import com.example.be_MrHung.models.Schedule;
import com.example.be_MrHung.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/movie/{movieId}/cinema/{cinemaId}")
    public List<Schedule> getSchedulesByMovieAndCinema(
            @PathVariable Integer movieId,
            @PathVariable Integer cinemaId) {

        return scheduleService.getSchedulesByMovieAndCinema(movieId, cinemaId);
    }

}
