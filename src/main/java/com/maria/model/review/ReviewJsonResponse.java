package com.maria.model.review;

/**
 * Created on 8/21/2017.
 */
public class ReviewJsonResponse {
    private int id;
    private int entryId;
    private int totalReviewsCount;

    public ReviewJsonResponse(Review review) {
        this
                .setId(review.getId())
                .setEntryId(review.getContestEntryId());
    }

    public ReviewJsonResponse() {
    }

    public int getTotalReviewsCount() {
        return totalReviewsCount;
    }

    public ReviewJsonResponse setTotalReviewsCount(int totalReviewsCount) {
        this.totalReviewsCount = totalReviewsCount;
        return this;
    }

    public int getEntryId() {
        return entryId;
    }

    public ReviewJsonResponse setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    public int getId() {
        return id;
    }

    public ReviewJsonResponse setId(int id) {
        this.id = id;
        return this;
    }

}
