package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.controller.dtos.ReviewDTO;
import com.ironhack.edgeservice.controller.dtos.SiteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("sites-service")
public interface SiteClient {

    @GetMapping("/sites/group/{id}")
    List<SiteDTO> getSiteByGroupId(@PathVariable("id") Long id);

    @PostMapping("/sites")
    SiteDTO saveNewSite(@RequestBody SiteDTO siteDTO);

    @PostMapping("/reviews")
    void saveNewReview(@RequestBody ReviewDTO reviewDTO);

    @PostMapping("/reviews-by-group/{groupId}")
    List<ReviewDTO> getReviews(@PathVariable("groupId") Long groupId, @RequestBody SiteDTO siteDTO);
}
