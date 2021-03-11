package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.controller.dtos.GroupDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("group-service")
public interface GroupClient {

    @GetMapping("/groups-by-user/{id}")
    List<GroupDTO> getGroupsByUser(@PathVariable("id") Long id);

    @GetMapping("/groups/{id}")
    GroupDTO getGroupById(@PathVariable("id") Long id);

    @PostMapping("/new-member/{groupId}/{userId}")
    void saveUserAsMemberByGroupId(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId);

    @GetMapping("/members-group/{id}")
    List<Long> getMembersByGroupId(@PathVariable("id") Long id);

    @PostMapping("/new-group")
    GroupDTO saveNewGroup(@RequestBody GroupDTO groupDTO);
}
