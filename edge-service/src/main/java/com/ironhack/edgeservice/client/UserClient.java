package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.controller.dtos.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("users-service")
public interface UserClient {

    @GetMapping("/users/{username}")
    UserDTO findByUsername(@PathVariable("username") String username);
}
