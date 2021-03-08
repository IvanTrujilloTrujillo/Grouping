package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.controller.dtos.UserDTO;
import com.ironhack.userservice.controller.interfaces.IUserController;
import com.ironhack.userservice.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController implements IUserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }
}
