package com.maria.service.api;

import com.maria.model.review.CreateReviewRequest;
import com.maria.model.review.Review;

/**
 * Created on 8/21/2017.
 */
public interface ReviewService {
    Review createReview(CreateReviewRequest createReviewRequest);
}
