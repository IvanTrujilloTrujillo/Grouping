package com.ironhack.groupsservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {

    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long groupAdmin;

    @OneToMany(mappedBy = "group")
    private List<GroupMember> groupMemberList;

    /**
     * Default constructor
     */
    public Group() {
    }

    /**
     * Constructor: name, groupAdmin
     */
    public Group(String name, Long groupAdmin) {
        this.name = name;
        this.groupAdmin = groupAdmin;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(Long groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public List<GroupMember> getGroupMemberList() {
        return groupMemberList;
    }

    public void setGroupMemberList(List<GroupMember> groupMemberList) {
        this.groupMemberList = groupMemberList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupAdmin=" + groupAdmin +
                ", groupMemberList=" + groupMemberList +
                '}';
    }
}
