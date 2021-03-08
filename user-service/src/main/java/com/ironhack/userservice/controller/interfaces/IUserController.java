package com.ironhack.userservice.controller.interfaces;

import com.ironhack.userservice.controller.dtos.UserDTO;

public interface IUserController {

    UserDTO register(UserDTO userDTO);

    UserDTO findByUsername(String username);
}
