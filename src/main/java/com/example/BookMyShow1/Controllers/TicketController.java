package com.example.BookMyShow1.Controllers;

import com.example.BookMyShow1.Dtos.BookTicketRequestDto;
import com.example.BookMyShow1.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("book")
    public String bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto){
        try{
            return ticketService.bookTicket(bookTicketRequestDto);
        }catch(Exception e){
            return "Requested seats are not available";
        }
    }
}
