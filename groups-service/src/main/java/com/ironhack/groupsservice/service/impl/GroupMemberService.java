package com.ironhack.groupsservice.service.impl;

import com.ironhack.groupsservice.model.Group;
import com.ironhack.groupsservice.model.GroupMember;
import com.ironhack.groupsservice.repository.GroupMemberRepository;
import com.ironhack.groupsservice.repository.GroupRepository;
import com.ironhack.groupsservice.service.interfaces.IGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupMemberService implements IGroupMemberService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    //Link a user with a group
    public void saveUserAsMemberByGroupId(Long groupId, Long userId) {

        //Get the group by id
        Group group;
        if(groupRepository.existsById(groupId)) {
            group = groupRepository.findById(groupId).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }

        //System.out.println(userId);
        //System.out.println(group);

        groupMemberRepository.save(new GroupMember(userId, group));
    }

    //Get all groupMembers by a group id
    public List<Long> getMembersByGroupId(Long id) {

        //Get the group by id
        Group group;
        if(groupRepository.existsById(id)) {
            group = groupRepository.findById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }

        //Search the GroupMember for this group
        List<GroupMember> groupMemberList = groupMemberRepository.findByGroup(group);

        //Get only the list of the user id and return it
        List<Long> userIdList = new ArrayList<>();
        for (GroupMember groupMember : groupMemberList) {
            userIdList.add(groupMember.getUserId());
        }
        return userIdList;
    }
}
