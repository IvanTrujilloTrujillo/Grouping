package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.controller.dtos.GroupDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("group-service")
public interface GroupClient {

    @GetMapping("/groups")
    List<GroupDTO> getAllGroups();

    @GetMapping("/groups/{id}")
    GroupDTO getGroupById(@PathVariable("id") Long id);
}
