package com.ironhack.edgeservice.controller.dtos;

public class ReviewWithUserNameDTO extends ReviewDTO{

    /**
     * Properties
     */
    private String userName;

    /**
     * Default constructor
     */
    public ReviewWithUserNameDTO() {
    }

    /**
     * Constructor: id, groupId, site, userId, rating, comment, userName
     */
    public ReviewWithUserNameDTO(Long id, Long groupId, SiteDTO site, Long userId, Integer rating, String comment, String userName) {
        super(id, groupId, site, userId, rating, comment);
        this.userName = userName;
    }

    /**
     * Getters & Setters
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
