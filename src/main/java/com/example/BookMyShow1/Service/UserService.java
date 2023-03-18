package com.example.BookMyShow1.Service;

import com.example.BookMyShow1.Dtos.UserRequestDto;
import com.example.BookMyShow1.Models.UserEntity;
import com.example.BookMyShow1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public String createUser(UserRequestDto userRequestDto){

        //converted the userRequestDto to userEntity by builder annotation
        UserEntity userEntity = UserEntity.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();

        try{
            userRepository.save(userEntity);
        }catch (Exception e){
            return "user couldn't be added";
        }
        return "User Successfully added";
    }
}
