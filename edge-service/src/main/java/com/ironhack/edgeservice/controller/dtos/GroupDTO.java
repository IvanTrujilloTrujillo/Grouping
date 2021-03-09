package com.ironhack.edgeservice.controller.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GroupDTO {

    /**
     * Properties
     */
    private Long id;
    @NotEmpty
    private String name;
    @NotNull
    private Long groupAdmin;

    private String tocken;

    /**
     * Default constructor
     */
    public GroupDTO() {
    }

    /**
     * Constructor: id, name, groupAdmin
     */
    public GroupDTO(Long id, String name, Long groupAdmin) {
        this.id = id;
        this.name = name;
        this.groupAdmin = groupAdmin;
    }

    /**
     * Constructor: name, groupAdmin
     */
    public GroupDTO(String name, Long groupAdmin) {
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

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }
}
