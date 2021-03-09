package com.ironhack.groupsservice.controller.impl;

import com.ironhack.groupsservice.controller.interfaces.IGroupMemberController;
import com.ironhack.groupsservice.service.interfaces.IGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupMemberController implements IGroupMemberController {

    @Autowired
    private IGroupMemberService groupMemberService;

    @PostMapping("/new-member/{groupId}/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUserAsMemberByGroupId(@PathVariable("groupId") Long groupId, @PathVariable("groupId") Long userId) {
        groupMemberService.saveUserAsMemberByGroupId(groupId, userId);
    }


    @GetMapping("/members-group/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Long> getMembersByGroupId(@PathVariable("id") Long id) {
        return groupMemberService.getMembersByGroupId(id);
    }
}
