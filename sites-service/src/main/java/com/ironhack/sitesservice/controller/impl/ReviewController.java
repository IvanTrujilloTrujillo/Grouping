package com.ironhack.sitesservice.controller.impl;

import com.ironhack.sitesservice.controller.dtos.ReviewDTO;
import com.ironhack.sitesservice.controller.dtos.SiteDTO;
import com.ironhack.sitesservice.controller.interfaces.IReviewController;
import com.ironhack.sitesservice.service.interfaces.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReviewController implements IReviewController {

    @Autowired
    private IReviewService reviewService;

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewReview(@RequestBody @Valid ReviewDTO reviewDTO) {
        reviewService.saveNewReview(reviewDTO);
    }

    @PostMapping("/reviews-by-group/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> getReviews(@PathVariable("groupId") Long groupId, @RequestBody SiteDTO siteDTO) {
        return reviewService.getReviews(groupId, siteDTO);
    }
}
