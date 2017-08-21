package com.maria.model.review;

/**
 * Created on 8/21/2017.
 */
public class CreateReviewRequest {
    private int userId;
    private int contestEntryId;
    private int rate;

    public int getUserId() {
        return userId;
    }

    public CreateReviewRequest setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getContestEntryId() {
        return contestEntryId;
    }

    public CreateReviewRequest setContestEntryId(int contestEntryId) {
        this.contestEntryId = contestEntryId;
        return this;
    }

    public int getRate() {
        return rate;
    }

    public CreateReviewRequest setRate(int rate) {
        this.rate = rate;
        return this;
    }
}
