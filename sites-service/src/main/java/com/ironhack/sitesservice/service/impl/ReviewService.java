package com.ironhack.sitesservice.service.impl;

import com.ironhack.sitesservice.controller.dtos.ReviewDTO;
import com.ironhack.sitesservice.controller.dtos.SiteDTO;
import com.ironhack.sitesservice.model.Review;
import com.ironhack.sitesservice.model.Site;
import com.ironhack.sitesservice.repository.ReviewRepository;
import com.ironhack.sitesservice.service.interfaces.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    //Save a new review
    public void saveNewReview(ReviewDTO reviewDTO) {
        //Convert the SiteDTO to a Site
        Site site = new Site(reviewDTO.getSite().getId(), reviewDTO.getSite().getName(), reviewDTO.getSite().getMapUrl());

        //System.out.println(reviewDTO);
        //System.out.println(site);

        //Check if the user has a review from the same site in the same group
        List<Review> reviewList = reviewRepository.findByGroupIdAndUserId(reviewDTO.getGroupId(), reviewDTO.getUserId());
        for (Review review : reviewList) {
            if(review.getSite().getName().equals(site.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This user already has a review to this site and group");
            }
        }


        //Convert ReviewDTO to a Review and save it
        reviewRepository.save(new Review(reviewDTO.getGroupId(), site, reviewDTO.getUserId(), reviewDTO.getRating(), reviewDTO.getComment()));
    }

    //Get all the reviews from a Site and a Group
    public List<ReviewDTO> getReviews(Long groupId, SiteDTO siteDTO) {
        //Convert siteDTO to a Site
        Site site = new Site(siteDTO.getId(), siteDTO.getName(), siteDTO.getMapUrl());

        //Search all the reviews from the database
        List<Review> reviewList = reviewRepository.findByGroupIdAndSite(groupId, site);

        //Convert the list to a list of DTOs and return it
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for (Review review : reviewList) {
            reviewDTOList.add(new ReviewDTO(review.getId(), review.getGroupId(),
                    new SiteDTO(review.getSite().getId(), review.getSite().getName(), review.getSite().getMapUrl()),
                    review.getUserId(), review.getRating(), review.getComment()));
        }
        return reviewDTOList;
    }
}
