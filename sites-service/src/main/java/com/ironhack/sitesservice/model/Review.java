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
     * Constructor: site, userId, rating, comment
     */
    public Review(Site site, Long userId, Integer rating, String comment) {
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
}