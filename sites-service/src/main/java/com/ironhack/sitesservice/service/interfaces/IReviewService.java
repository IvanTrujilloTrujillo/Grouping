package com.ironhack.sitesservice.service.interfaces;

import com.ironhack.sitesservice.controller.dtos.ReviewDTO;

public interface IReviewService {

    void saveNewReview(ReviewDTO reviewDTO);
}
