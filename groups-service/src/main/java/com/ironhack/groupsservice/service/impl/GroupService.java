package com.ironhack.groupsservice.service.impl;

import com.ironhack.groupsservice.controller.dtos.GroupDTO;
import com.ironhack.groupsservice.model.Group;
import com.ironhack.groupsservice.model.GroupMember;
import com.ironhack.groupsservice.repository.GroupMemberRepository;
import com.ironhack.groupsservice.repository.GroupRepository;
import com.ironhack.groupsservice.service.interfaces.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService implements IGroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    //Return all the groups from the database
    public List<GroupDTO> getGroupsByUser(Long id) {

        //Retrieve the GroupMembers where appear the userId
        List<GroupMember> groupMemberList = groupMemberRepository.findByUserId(id);

        //Create a list with the groups in the groupMemberList
        List<Group> groupList = new ArrayList<>();
        for (GroupMember groupMember : groupMemberList) {
            groupList.add(groupMember.getGroup());
        }

        //Convert to a dto list
        List<GroupDTO> groupDTOList = new ArrayList<>();
        for (Group group : groupList) {
            GroupDTO groupDTO = new GroupDTO(group.getId(), group.getName(), group.getGroupAdmin());
            groupDTOList.add(groupDTO);
        }

        return groupDTOList;
    }

    //Find a group by id and return it
    public GroupDTO getGroupById(Long id) {
        if(groupRepository.existsById(id)) {

            Group group = groupRepository.findById(id).get();

            //Return a group DTO
            return new GroupDTO(group.getId(), group.getName(), group.getGroupAdmin());
        } else {
            //If it doesn't exist, return a not found exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }
    }

    public GroupDTO saveNewGroup(GroupDTO groupDTO) {
        //Save the group in the database
        Group group = groupRepository.save(new Group(groupDTO.getName(), groupDTO.getGroupAdmin()));

        //Save the admin as a group member
        groupMemberRepository.save(new GroupMember(groupDTO.getGroupAdmin(), group));

        //Set the group Id to the DTO
        groupDTO.setId(group.getId());

        return groupDTO;
    }


}
