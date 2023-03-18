package com.example.BookMyShow1.Dtos;

import lombok.Data;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Data
public class BookTicketRequestDto {

    private List<String> requestSeats;
    private int showId;
    private int userId;

}
