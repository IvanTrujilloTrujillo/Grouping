package com.ironhack.groupsservice.repository;

import com.ironhack.groupsservice.model.Group;
import com.ironhack.groupsservice.model.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    List<GroupMember> findByGroup(Group group);
    List<GroupMember> findByUserId(Long id);
}
