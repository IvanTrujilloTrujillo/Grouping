package com.ironhack.sitesservice.model;

import javax.persistence.*;

@Entity
public class Review {

    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long groupId;
    @ManyToOne
    private Site site;
    private Long userId;
    private Integer rating;
    private String comment;

    /**
     * Default constructor
     */
    public Review() {
    }

    /**
     * Constructor: groupId, site, userId, rating, comment
     */
    public Review(Long groupId, Site site, Long userId, Integer rating, String comment) {
        this.groupId = groupId;
        this.site = site;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", site=" + site +
                ", userId=" + userId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}