package com.ironhack.edgeservice.controller.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SiteWithReviewsDTO extends SiteDTO{

    private Double mean;

    public SiteWithReviewsDTO() {
    }

    public SiteWithReviewsDTO(Long id, String name, String mapUrl, Double mean) {
        super(id, name, mapUrl);
        this.mean = mean;
    }

    public SiteWithReviewsDTO(String name, String mapUrl, Double mean) {
        super(name, mapUrl);
        this.mean = mean;
    }

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    @Override
    public String toString() {
        return "SiteWithReviewsDTO{" +
                "mean=" + mean +
                '}';
    }
}
