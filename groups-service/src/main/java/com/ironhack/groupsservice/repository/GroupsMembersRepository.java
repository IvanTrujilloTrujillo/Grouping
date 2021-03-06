package com.ironhack.groupsservice.repository;

import com.ironhack.groupsservice.model.GroupsMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsMembersRepository extends JpaRepository<GroupsMembers, Long> {
}
