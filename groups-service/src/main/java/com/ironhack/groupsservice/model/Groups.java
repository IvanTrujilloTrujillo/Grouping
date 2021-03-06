package com.ironhack.groupsservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Groups {

    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long groupAdmin;

    @OneToMany(mappedBy = "groups")
    private List<GroupsMembers> groupsMembersList;

    /**
     * Default constructor
     */
    public Groups() {
    }

    /**
     * Constructor: groupAdmin
     */
    public Groups(Long groupAdmin) {
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

    public Long getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(Long groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public List<GroupsMembers> getGroupsMembersList() {
        return groupsMembersList;
    }

    public void setGroupsMembersList(List<GroupsMembers> groupsMembersList) {
        this.groupsMembersList = groupsMembersList;
    }
}
