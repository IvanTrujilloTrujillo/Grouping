package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.controller.dtos.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("users-service")
public interface UserClient {

    @PostMapping("/users")
    UserDTO register(@RequestBody UserDTO userDTO);

    @GetMapping("/users/{username}")
    UserDTO findByUsername(@PathVariable("username") String username);

    @GetMapping("/users/name/{userId}")
    String findNameByUserId(@PathVariable("userId") Long userId);
}
