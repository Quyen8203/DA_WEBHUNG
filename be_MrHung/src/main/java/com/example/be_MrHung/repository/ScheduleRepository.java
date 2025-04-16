package com.example.be_MrHung.repository;


import com.example.be_MrHung.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query("SELECT s FROM Schedule  s JOIN Movie m ON s.movieId = m.movieId JOIN Room r ON s.roomId = r.roomId WHERE m.movieId = ?1 AND r.cinemaId = ?2")
    List<Schedule> findScheduleByMovieId(Integer movieId, Integer cinemaId);

}
