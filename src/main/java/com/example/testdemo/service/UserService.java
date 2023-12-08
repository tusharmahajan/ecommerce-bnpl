package com.example.testdemo.service;

import com.example.testdemo.dtos.UserDetailsDto;
import com.example.testdemo.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    public String registerUser(UserDetailsDto userDetailsDto) {

        String name = userDetailsDto.getName();
        Integer bnplLimit = userDetailsDto.getBnplLimit();

        return UserRepository.storeUserDetails(name, bnplLimit);
    }
}
