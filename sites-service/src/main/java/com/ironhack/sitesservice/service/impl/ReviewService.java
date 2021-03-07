package com.ironhack.sitesservice.service.impl;

import com.ironhack.sitesservice.controller.dtos.ReviewDTO;
import com.ironhack.sitesservice.model.Review;
import com.ironhack.sitesservice.model.Site;
import com.ironhack.sitesservice.repository.ReviewRepository;
import com.ironhack.sitesservice.service.interfaces.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    //Save a new review
    public void saveNewReview(ReviewDTO reviewDTO) {
        //Convert the SiteDTO to a Site
        Site site = new Site(reviewDTO.getSite().getId(), reviewDTO.getSite().getName(), reviewDTO.getSite().getMapUrl());

        //Check if the user has a review from the same site in the same group
        if(reviewRepository.findByGroupIdAndSiteAndUserId(reviewDTO.getGroupId(), site, reviewDTO.getUserId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This user already has a review to this site and group");
        }

        //Convert ReviewDTO to a Review and save it
        reviewRepository.save(new Review(reviewDTO.getGroupId(), site, reviewDTO.getUserId(), reviewDTO.getRating(), reviewDTO.getComment()));
    }
}
