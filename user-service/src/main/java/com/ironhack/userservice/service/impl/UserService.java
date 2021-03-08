package com.ironhack.userservice.service.impl;

import com.ironhack.userservice.controller.dtos.UserDTO;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.repository.UserRepository;
import com.ironhack.userservice.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    //Get a User by username
    public UserDTO findByUsername(String username) {
        //Get the User from the database
        Optional<User> user = userRepository.findByUsername(username);

        //Checks if the user exists
        if(user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
        }

        //Convert User to UserDTO and return it
        return new UserDTO(user.get().getId(), user.get().getName(), user.get().getUsername(), user.get().getPassword());
    }
}
