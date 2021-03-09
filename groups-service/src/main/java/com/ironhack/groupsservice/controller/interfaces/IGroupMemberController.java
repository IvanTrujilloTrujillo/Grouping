package com.ironhack.groupsservice.controller.interfaces;

import java.util.List;

public interface IGroupMemberController {

    void saveUserAsMemberByGroupId(Long groupId, Long userId);

    List<Long> getMembersByGroupId(Long id);
}
