package com.maria.service.review;

import com.maria.model.review.CreateReviewRequest;
import com.maria.model.review.Review;
import com.maria.repository.ReviewRepository;
import com.maria.service.api.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 8/21/2017.
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(CreateReviewRequest createReviewRequest) {
        int contestEntryId = createReviewRequest.getContestEntryId();
        int rate = createReviewRequest.getRate();
        int userId = createReviewRequest.getUserId();
        return reviewRepository.createReview(userId, contestEntryId, rate);
    }
}
