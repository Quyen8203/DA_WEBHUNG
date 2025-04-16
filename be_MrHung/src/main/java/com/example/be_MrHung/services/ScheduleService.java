package com.example.be_MrHung.services;

import com.example.be_MrHung.eNum.ResponseData;
import com.example.be_MrHung.models.Schedule;
import com.example.be_MrHung.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> getSchedulesByMovieAndCinema(Integer movieId, Integer cinemaId) {
        return scheduleRepository.findScheduleByMovieId(movieId, cinemaId);
    }
}
