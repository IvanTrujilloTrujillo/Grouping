package com.ironhack.groupsservice.controller.interfaces;

import com.ironhack.groupsservice.controller.dtos.GroupDTO;

import java.util.List;

public interface IGroupController {

    List<GroupDTO> getGroupsByUser(Long id);

    GroupDTO getGroupById(Long id);

    GroupDTO saveNewGroup(GroupDTO groupDTO);

}
