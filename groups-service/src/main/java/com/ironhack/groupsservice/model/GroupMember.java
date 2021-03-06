package com.ironhack.groupsservice.model;

import javax.persistence.*;

@Entity
@Table(name = "groups_members")
public class GroupMember {

    /**
    * Properties
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long UserId;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    /**
     * Default constructor
     */
    public GroupMember() {
    }

    /**
     * Constructor: userId, group
     */
    public GroupMember(Long userId, Group group) {
        UserId = userId;
        this.group = group;
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

    public Group getGroups() {
        return group;
    }

    public void setGroups(Group group) {
        this.group = group;
    }
}
