package com.maria.model.review;

/**
 * Created on 8/21/2017.
 */
public class CreateReviewJsonRequest {
    private Integer contestEntryId;

    public Integer getContestEntryId() {
        return contestEntryId;
    }

    public CreateReviewJsonRequest setContestEntryId(Integer contestEntryId) {
        this.contestEntryId = contestEntryId;
        return this;
    }

}
