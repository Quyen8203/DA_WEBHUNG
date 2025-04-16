package com.example.be_MrHung.services;


import com.example.be_MrHung.eNum.ResponseData;
import com.example.be_MrHung.models.Cinema;
import com.example.be_MrHung.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {
    @Autowired
    CinemaRepository cinemaRepository;

    public ResponseData<Cinema> getAllCinema(){
        return new ResponseData(HttpStatus.OK, "success", cinemaRepository.findAll());
    }

    // Tìm rạp theo id suất chiếu
    public ResponseData<Cinema> getCinemaByScheduleId(Integer schedule_id){
        return new ResponseData(HttpStatus.OK, "success", cinemaRepository.getCinemasByIdSchedule(schedule_id));
    }

    //Tìm rạp theo phim và ngày chiếu
    public ResponseData<List<Cinema>> getCinemasByMovieAndDate(Integer movie_id, String schedule_date) {
        List<Cinema> cinemas = cinemaRepository.getScheduleCinema(movie_id, schedule_date);
        if (!cinemas.isEmpty()) {
            return new ResponseData<>(HttpStatus.OK, "success", cinemas);
        } else {
            return new ResponseData<>(HttpStatus.NOT_FOUND, "Không tìm thấy rạp nào chiếu phim này vào ngày được chọn", null);
        }
    }

    public ResponseData<List<Cinema>> searchCinemasByAddress(String address) {
        try {
            List<Cinema> cinemas = cinemaRepository.findCinemasByAddress(address);

            if (cinemas.isEmpty()) {
                return new ResponseData<>(HttpStatus.NOT_FOUND,
                        "Không tìm thấy rạp nào với địa chỉ: " + address, null);
            }

            return new ResponseData<>(HttpStatus.OK, "Thành công", cinemas);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Lỗi khi tìm kiếm rạp: " + e.getMessage(), null);
        }
    }


}
