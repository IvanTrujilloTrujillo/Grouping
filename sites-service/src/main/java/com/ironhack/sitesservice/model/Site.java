package com.ironhack.sitesservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Site {

    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long groupId;
    private String mapUrl;

    @OneToMany(mappedBy = "site")
    private List<Review> reviewList;

    /**
     * Default constructor
     */
    public Site() {
    }

    /**
     * Constructor: name, groupId, mapUrl
     */
    public Site(String name, Long groupId, String mapUrl) {
        this.name = name;
        this.groupId = groupId;
        this.mapUrl = mapUrl;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
