package com.ironhack.sitesservice.controller.interfaces;

import com.ironhack.sitesservice.controller.dtos.ReviewDTO;

import java.util.List;

public interface IReviewController {

    void saveNewReview(ReviewDTO reviewDTO);
}
