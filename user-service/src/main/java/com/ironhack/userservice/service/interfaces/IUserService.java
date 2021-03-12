package com.ironhack.userservice.service.interfaces;

import com.ironhack.userservice.controller.dtos.UserDTO;

public interface IUserService {

    UserDTO register(UserDTO userDTO);

    UserDTO findByUsername(String username);

    String findNameByUserId(Long userId);
}
