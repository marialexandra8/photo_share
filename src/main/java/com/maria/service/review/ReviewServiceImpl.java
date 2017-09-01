package com.maria.service.review;

import com.maria.exception.AlreadyExistsException;
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
        int userId = createReviewRequest.getUserId();
        if (reviewRepository.userAlreadyVotedEntry(userId, contestEntryId)) {
            throw new AlreadyExistsException("You have already voted this entry!");
        }
        return reviewRepository.createReview(userId, contestEntryId);
    }

    @Override
    public Integer findLikesCountForEntry(int entryId) {
        return reviewRepository.findLikesCount(entryId);
    }

}
