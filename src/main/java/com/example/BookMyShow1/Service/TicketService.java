package com.example.BookMyShow1.Service;

import com.example.BookMyShow1.Controllers.TicketController;
import com.example.BookMyShow1.Dtos.BookTicketRequestDto;
import com.example.BookMyShow1.Models.ShowEntity;
import com.example.BookMyShow1.Models.ShowSeatEntity;
import com.example.BookMyShow1.Models.TicketEntity;
import com.example.BookMyShow1.Models.UserEntity;
import com.example.BookMyShow1.Repository.ShowRepository;
import com.example.BookMyShow1.Repository.TicketRepository;
import com.example.BookMyShow1.Repository.UserRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    TicketRepository ticketRepository;

    public String bookTicket(BookTicketRequestDto bookTicketRequestDto) throws Exception{

        //Get the requested seat
        List<String> requestedSeats = bookTicketRequestDto.getRequestSeats();

        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();

        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

        //get the show seats from the show entity
        List<ShowSeatEntity> showSeats = showEntity.getListOfSeats();

        //Validate I can allocate these seats to the requested by user

        List<ShowSeatEntity> bookedSeats = new ArrayList<>();

        for(ShowSeatEntity showSeat:showSeats){

            String seatNo = showSeat.getSeatNo();
            if(showSeat.isBooked()==false && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }
        }

        // if some seats are not available which is requested by the users
        //Failure Case
        if(bookedSeats.size() != requestedSeats.size()){
            throw new Exception("Requested seats aren't available");
        }

        //SUCCESS
        //This means bookedSeats actually contains the booked seats
        //So we can create a tickets
        TicketEntity ticketEntity = new TicketEntity();

        double totalAmount = 0;
        double multiplier = showEntity.getMultiplier();

        String allotedSeats = "";

        int rate = 0;

        //So we can create the amount,setting booked status
        for(ShowSeatEntity bookedSeat : bookedSeats){
            bookedSeat.setBooked(true);    // show seat is booked
            bookedSeat.setBookedAt(new Date());    // ticket is booked at that time
            bookedSeat.setTicket(ticketEntity);    // for which ticket it was booked
            bookedSeat.setShow(showEntity);    // which show is booked

            String seatNo = bookedSeat.getSeatNo();
            allotedSeats = allotedSeats + seatNo + ",";
            if(seatNo.charAt(0)=='1'){
                rate = 100;
            }else{
                rate = 200;
            }
            totalAmount = totalAmount + multiplier*rate;
        }

        // Now we can generate a tickets
        ticketEntity.setBooked_at(new Date());
        ticketEntity.setAmount((int)totalAmount);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAlloted_seats(allotedSeats);

        //Bidirectional mapping part is pending

        ticketRepository.save(ticketEntity);
        return "Successfully created a ticket";
    }
}
