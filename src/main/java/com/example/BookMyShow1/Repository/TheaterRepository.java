package com.example.BookMyShow1.Repository;

import com.example.BookMyShow1.Models.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity,Integer> {

    TheaterEntity findByNameAndCity(String name,String city);
}
