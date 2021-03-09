package com.ironhack.groupsservice.service.interfaces;

import java.util.List;

public interface IGroupMemberService {

    void saveUserAsMemberByGroupId(Long groupId, Long userId);

    List<Long> getMembersByGroupId(Long id);
}
