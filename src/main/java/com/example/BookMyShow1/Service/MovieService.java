package com.example.BookMyShow1.Service;

import com.example.BookMyShow1.Dtos.MovieRequestDto;
import com.example.BookMyShow1.Models.MovieEntity;
import com.example.BookMyShow1.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto){

        //convert dto to entity to save it into database
        MovieEntity movie = MovieEntity.builder().movieName(movieRequestDto.getMovieName()).duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();

        movieRepository.save(movie);

        return "Movie Added Successfully";
    }
}
