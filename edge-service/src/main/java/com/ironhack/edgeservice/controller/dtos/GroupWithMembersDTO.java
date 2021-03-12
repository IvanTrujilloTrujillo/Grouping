package com.ironhack.edgeservice.controller.dtos;

public class GroupWithMembersDTO extends GroupDTO{

    /**
     * Properties
     */
    private Integer groupMembers;

    /**
     * Default constructor
     */
    public GroupWithMembersDTO() {
    }

    /**
     * Constructor: id, name, groupAdmin, groupMembers
     */
    public GroupWithMembersDTO(Long id, String name, Long groupAdmin, Integer groupMembers) {
        super(id, name, groupAdmin);
        this.groupMembers = groupMembers;
    }

    /**
     * Getters & Setters
     */
    public Integer getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(Integer groupMembers) {
        this.groupMembers = groupMembers;
    }

    @Override
    public String toString() {
        return "GroupWithMembersDTO{" +
                "groupMembers=" + groupMembers +
                '}';
    }
}
