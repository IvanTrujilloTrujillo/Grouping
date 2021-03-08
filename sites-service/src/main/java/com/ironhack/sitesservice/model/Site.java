package com.ironhack.sitesservice.model;

import org.hibernate.annotations.Type;

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
    @Type(type = "text")
    private String mapUrl;

    @OneToMany(mappedBy = "site")
    private List<Review> reviewList;

    /**
     * Default constructor
     */
    public Site() {
    }

    /**
     * Constructor: id, name, mapUrl
     */
    public Site(Long id, String name, String mapUrl) {
        this.id = id;
        this.name = name;
        this.mapUrl = mapUrl;
    }

    /**
     * Constructor: name, mapUrl
     */
    public Site(String name, String mapUrl) {
        this.name = name;
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
