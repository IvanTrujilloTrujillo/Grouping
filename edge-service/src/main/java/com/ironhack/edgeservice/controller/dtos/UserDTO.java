package com.ironhack.edgeservice.controller.dtos;

import java.util.Set;

public class UserDTO {

    /**
     * Properties
     */
    private Long id;
    private String name;
    private String username;
    private String password;

    private Set<RoleDTO> roles;

    /**
     * Default constructor
     */
    public UserDTO() {
    }

    /**
     * Constructor: id, name, username, password, roles
     */
    public UserDTO(Long id, String name, String username, String password, Set<RoleDTO> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    /**
     * Constructor: id, name, username, password
     */
    public UserDTO(Long id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }
}
