package com.ironhack.groupsservice.service.interfaces;

import com.ironhack.groupsservice.controller.dtos.GroupDTO;

import java.util.List;

public interface IGroupService {

    List<GroupDTO> getGroupsByUser(Long id);

    GroupDTO getGroupById(Long id);


    GroupDTO saveNewGroup(GroupDTO groupDTO);
}
