package com.example.BookMyShow1.Repository;

import com.example.BookMyShow1.Models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {

    MovieEntity findByMovieName(String movieName);
}
