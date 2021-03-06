package com.ironhack.groupsservice.repository;

import com.ironhack.groupsservice.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {
}
