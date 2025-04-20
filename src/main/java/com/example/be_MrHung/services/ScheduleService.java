package com.example.be_MrHung.services;

import com.example.be_MrHung.eNum.ResponseData;
import com.example.be_MrHung.models.Movie;
import com.example.be_MrHung.models.Schedule;
import com.example.be_MrHung.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;


    public ResponseData<Schedule> getSchedulesByMovieAndCinema(Integer movieId, Integer cinemaId) {
        return new ResponseData(HttpStatus.OK, "success",scheduleRepository.findScheduleByMovieId(movieId, cinemaId));
    }

    public ResponseData<Schedule> getAllSchedule(){
        return new ResponseData(HttpStatus.OK, "success", scheduleRepository.findAll());
    }

    public ResponseData<Schedule> getSchedulesByMovieAndCinemaAndDate(Integer movieId, Integer cinemaId, String date) {
        return new ResponseData(HttpStatus.OK, "success", scheduleRepository.findByMovieIdAndRoom_CinemaIdAndScheduleDateOrderByScheduleStart(
                movieId,
                cinemaId,
                date
        ));
    }


}
