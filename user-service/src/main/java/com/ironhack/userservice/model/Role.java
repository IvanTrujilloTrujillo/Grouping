package com.ironhack.userservice.model;

import javax.persistence.*;

@Entity
public class Role {

    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private User user;

    /**
     * Default constructor
     */
    public Role() {
    }

    /**
     * Constructor: name, user
     */
    public Role(String name, User user) {
        this.name = name;
        this.user = user;
    }

    /**
     * Getters & setters
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
