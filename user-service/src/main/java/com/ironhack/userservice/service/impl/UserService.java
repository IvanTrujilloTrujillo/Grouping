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

    //Create a new User
    public UserDTO register(UserDTO userDTO) {
        //Checks if the name, username and password are valid
        if(userDTO.getName().equals("") || userDTO.getUsername().equals("") || userDTO.getPassword().equals("")) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The user is not valid");
        }
        //Checks if the username already exists
        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The username already exists");
        }

        //Convert UserDTO to a User and save it
        userDTO.setId(userRepository.save(new User(userDTO.getName(), userDTO.getUsername(), userDTO.getPassword())).getId());

        return userDTO;
    }

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

    //Get the name of the user by his/her id
    public String findNameByUserId(Long userId) {
        User user;

        //Get the user from the database
        if(userRepository.existsById(userId)) {
            user = userRepository.findById(userId).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        //Return the name
        return user.getName();
    }
}
