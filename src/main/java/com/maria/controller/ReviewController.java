package com.maria.controller;

import com.maria.model.review.CreateReviewJsonRequest;
import com.maria.model.review.CreateReviewRequest;
import com.maria.model.review.Review;
import com.maria.model.review.ReviewJsonResponse;
import com.maria.model.user.User;
import com.maria.security.PrincipalUser;
import com.maria.service.api.ReviewService;
import com.maria.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 8/21/2017.
 */
@RestController
@RequestMapping("/api")
public class ReviewController {
    private ReviewService reviewService;
    private UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public ReviewJsonResponse createReview(
            @RequestBody CreateReviewJsonRequest createReviewJsonRequest,
            @AuthenticationPrincipal PrincipalUser principalUser) {
        User user = userService.findByAccountId(principalUser.getAccount().getId());
        CreateReviewRequest createReviewRequest = new CreateReviewRequest()
                .setContestEntryId(createReviewJsonRequest.getContestEntryId())
                .setUserId(user.getId());
        Review review = reviewService.createReview(createReviewRequest);
        ReviewJsonResponse reviewJsonResponse = new ReviewJsonResponse(review);
        reviewJsonResponse.setTotalReviewsCount(reviewService.findLikesCountForEntry(review.getContestEntryId()));
        return reviewJsonResponse;
    }
}
