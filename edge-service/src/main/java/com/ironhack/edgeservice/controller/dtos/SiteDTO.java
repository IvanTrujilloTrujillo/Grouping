package com.ironhack.edgeservice.controller.dtos;

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
    private Long groupId;
    @NotNull
    private String mapUrl;

    private String tocken;

    /**
     * Default Constructor
     */
    public SiteDTO() {
    }

    /**
     * Constructor: id, name, groupId, mapUrl
     */
    public SiteDTO(Long id, @NotEmpty String name, @NotNull Long groupId, @NotNull String mapUrl) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.mapUrl = mapUrl;
    }

    /**
     * Constructor: name, groupId, mapUrl
     */
    public SiteDTO(String name, Long groupId, String mapUrl) {
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

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }
}
