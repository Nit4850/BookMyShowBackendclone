package com.example.BookMyShow1.Repository;

import com.example.BookMyShow1.Models.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity,Integer> {

}
