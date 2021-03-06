package com.ironhack.groupsservice.model;

import javax.persistence.*;

@Entity
@Table(name = "groups_members")
public class GroupsMembers {

    /**
    * Properties
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long UserId;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups groups;

    /**
     * Default constructor
     */
    public GroupsMembers() {
    }

    /**
     * Constructor: userId, groups
     */
    public GroupsMembers(Long userId, Groups groups) {
        UserId = userId;
        this.groups = groups;
    }

    /**
     * Getters & Setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}
