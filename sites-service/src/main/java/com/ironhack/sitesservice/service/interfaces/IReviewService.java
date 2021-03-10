package com.ironhack.sitesservice.service.interfaces;

import com.ironhack.sitesservice.controller.dtos.ReviewDTO;
import com.ironhack.sitesservice.controller.dtos.SiteDTO;

import java.util.List;

public interface IReviewService {

    void saveNewReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getReviews(Long groupId, SiteDTO siteDTO);
}
