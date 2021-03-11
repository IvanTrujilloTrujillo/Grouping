package com.ironhack.edgeservice.controller.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReviewDTO {

    /**
     * Properties
     */
    private Long id;
    @NotNull
    private Long groupId;
    @NotNull
    private SiteDTO site;
    @NotNull
    private Long userId;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    @NotNull
    private String comment;

    private String tocken;

    /**
     * Default constructor
     */
    public ReviewDTO() {
    }

    /**
     * Constructor: id, groupId, site, userId, rating, comment
     */
    public ReviewDTO(Long id, Long groupId, SiteDTO site, Long userId, Integer rating, String comment) {
        this.id = id;
        this.groupId = groupId;
        this.site = site;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * Constructor: groupId, site, userId, rating, comment
     */
    public ReviewDTO(Long groupId, SiteDTO site, Long userId, Integer rating, String comment) {
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

    public SiteDTO getSite() {
        return site;
    }

    public void setSite(SiteDTO site) {
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

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", site=" + site +
                ", userId=" + userId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", tocken='" + tocken + '\'' +
                '}';
    }
}
