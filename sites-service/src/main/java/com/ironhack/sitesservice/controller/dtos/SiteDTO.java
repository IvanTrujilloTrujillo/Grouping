package com.ironhack.sitesservice.controller.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SiteDTO {

    /**
     * Properties
     */
    private Long id;
    @NotEmpty
    private String name;
    @NotNull
    private String mapUrl;

    private String tocken;

    /**
     * Default Constructor
     */
    public SiteDTO() {
    }

    /**
     * Constructor: id, name mapUrl
     */
    public SiteDTO(Long id, String name, String mapUrl) {
        this.id = id;
        this.name = name;
        this.mapUrl = mapUrl;
    }

    /**
     * Constructor: name, mapUrl
     */
    public SiteDTO(String name, String mapUrl) {
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

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }
}
