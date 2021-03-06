package com.ironhack.groupsservice.controller.impl;

import com.ironhack.groupsservice.controller.dtos.GroupDTO;
import com.ironhack.groupsservice.controller.interfaces.IGroupController;
import com.ironhack.groupsservice.service.interfaces.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController implements IGroupController {

    @Autowired
    private IGroupService groupService;

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDTO> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDTO getGroupById(@PathVariable("id") Long id) {
        return groupService.getGroupById(id);
    }
}
