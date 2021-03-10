package com.ironhack.groupsservice.controller.impl;

import com.ironhack.groupsservice.controller.dtos.GroupDTO;
import com.ironhack.groupsservice.controller.interfaces.IGroupController;
import com.ironhack.groupsservice.service.interfaces.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController implements IGroupController {

    @Autowired
    private IGroupService groupService;

    @GetMapping("/groups-by-user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDTO> getGroupsByUser(@PathVariable("id") Long id) {
        return groupService.getGroupsByUser(id);
    }

    @GetMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDTO getGroupById(@PathVariable("id") Long id) {
        return groupService.getGroupById(id);
    }

    @PostMapping("/new-group")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupDTO saveNewGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.saveNewGroup(groupDTO);
    }

}
