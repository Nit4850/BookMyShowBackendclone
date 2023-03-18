package com.example.BookMyShow1.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "theater")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private String address;
    private String city;

    //List of shows
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    List<ShowEntity> listOfShows;


    //List f theater seats
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    List<TheaterSeatEntity> theaterSeatEntityList;

}
