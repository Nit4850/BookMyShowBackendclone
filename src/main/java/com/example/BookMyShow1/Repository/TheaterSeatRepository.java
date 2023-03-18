package com.example.BookMyShow1.Repository;

import com.example.BookMyShow1.Models.TheaterSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeatEntity,Integer> {
}
