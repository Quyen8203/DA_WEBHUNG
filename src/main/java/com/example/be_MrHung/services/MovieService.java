package com.example.be_MrHung.services;

import com.example.be_MrHung.eNum.ResponseData;
import com.example.be_MrHung.models.Movie;
import com.example.be_MrHung.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public ResponseData<Movie> getAllMovie(){
        return new ResponseData(HttpStatus.OK, "success", movieRepository.findAll());
    }

    public ResponseData<Movie> getMoviesNow(){
        List<Movie> rs = movieRepository.getMoviesNow();
        if(CollectionUtils.isEmpty(rs)){
            return new ResponseData(HttpStatus.NOT_FOUND, "failed", null);
        }else{
            return new ResponseData(HttpStatus.OK, "success",rs);
        }
    }

    public ResponseData<Movie> getMoviesFuture(){
        List<Movie> rs = movieRepository.getMoviesFuture();
        if(CollectionUtils.isEmpty(rs)){
            return new ResponseData(HttpStatus.NOT_FOUND, "failed", null);
        }else{
            return new ResponseData(HttpStatus.OK, "success", rs);
        }
    }

    public ResponseData<Movie> createMovies(Movie movie) {
        try {
            // Validate dữ liệu đầu vào
            if (movie.getMovieName() == null || movie.getMovieName().isEmpty()) {
                return new ResponseData<>(HttpStatus.BAD_REQUEST, "Tên phim không được để trống", null);
            }

            // Lưu phim vào database
            Movie savedMovie = movieRepository.save(movie);

            return new ResponseData<>(HttpStatus.CREATED, "Thêm phim thành công", savedMovie);
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi thêm phim: " + e.getMessage(), null);
        }
    }

    public ResponseData<Movie> getMovieDetail(int movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);

        if (movie.isPresent()) {
            return new ResponseData<>(HttpStatus.OK, "Thành công", movie.get());
        } else {
            return new ResponseData<>(HttpStatus.NOT_FOUND, "Không tìm thấy phim với ID: " + movieId, null);
        }
    }

}
