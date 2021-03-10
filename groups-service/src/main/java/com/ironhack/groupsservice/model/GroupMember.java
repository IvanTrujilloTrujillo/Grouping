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
    private Long userId;
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
        this.userId = userId;
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
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
