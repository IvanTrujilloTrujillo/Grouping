package com.ironhack.sitesservice.controller.interfaces;

import com.ironhack.sitesservice.controller.dtos.ReviewDTO;
import com.ironhack.sitesservice.controller.dtos.SiteDTO;

import java.util.List;

public interface IReviewController {

    void saveNewReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getReviews(Long groupId, SiteDTO siteDTO);
}
