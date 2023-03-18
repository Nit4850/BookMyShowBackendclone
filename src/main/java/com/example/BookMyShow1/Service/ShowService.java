package com.example.BookMyShow1.Service;

import com.example.BookMyShow1.Dtos.ShowRequestDto;
import com.example.BookMyShow1.Models.*;
import com.example.BookMyShow1.Repository.MovieRepository;
import com.example.BookMyShow1.Repository.ShowRepository;
import com.example.BookMyShow1.Repository.ShowSeatRepository;
import com.example.BookMyShow1.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowRequestDto showRequestDto){

        //creating the showEntity using the builder annotation
        ShowEntity showEntity = ShowEntity.builder().showTime(showRequestDto.getShowTime()).showDate(showRequestDto.getShowDate()).multiplier(showRequestDto.getMultiplier()).build();


        //You need to get the movie repository
        MovieEntity movieEntity = movieRepository.findByMovieName(showRequestDto.getMovieName());

        // you need to get the theater repository
        TheaterEntity theaterEntity = theaterRepository.findById(showRequestDto.getTheaterId()).get();

        showEntity.setTheater(theaterEntity);
        showEntity.setMovie(movieEntity);

        //optional things
        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);

        List<ShowSeatEntity> seatEntityList = createShowSeats(theaterEntity.getTheaterSeatEntityList());

        showEntity.setListOfSeats((seatEntityList));

        // For each show seats: we need to mark that to which show belongs (foreign key will be filled)
        for(ShowSeatEntity showSeat: seatEntityList){
            showSeat.setShow(showEntity);
        }
        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);
//        showRepository.save(showEntity);

        return "Show added Successfully";

    }

    public List<ShowSeatEntity> createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList){
        List<ShowSeatEntity> seats = new ArrayList<>();

        for(TheaterSeatEntity theaterSeat: theaterSeatEntityList){

            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNo(theaterSeat.getSeatNo()).seatType(theaterSeat.getSeatType()).build();
            seats.add(showSeat);

        }
        showSeatRepository.saveAll(seats);
        return seats;
    }
}
