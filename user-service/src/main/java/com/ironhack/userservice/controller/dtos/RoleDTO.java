package com.ironhack.userservice.controller.dtos;

public class RoleDTO {

    /**
     * Properties
     */
    private Long id;
    private String name;
    private UserDTO user;

    /**
     * Default constructor
     */
    public RoleDTO() {
    }

    /**
     * Constructor: id, name, user
     */
    public RoleDTO(Long id, String name, UserDTO user) {
        this.id = id;
        this.name = name;
        this.user = user;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
