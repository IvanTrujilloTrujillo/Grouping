package com.ironhack.groupsservice.controller.interfaces;

import com.ironhack.groupsservice.controller.dtos.GroupDTO;

import java.util.List;

public interface IGroupController {

    List<GroupDTO> getAllGroups();

    GroupDTO getGroupById(Long id);
}
