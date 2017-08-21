package com.maria.model.review;

/**
 * Created on 8/21/2017.
 */
public class Review {
    private int id;
    private int rate;
    private int userId;
    private int contestEntryId;

    public int getId() {
        return id;
    }

    public Review setId(int id) {
        this.id = id;
        return this;
    }

    public int getRate() {
        return rate;
    }

    public Review setRate(int rate) {
        this.rate = rate;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Review setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getContestEntryId() {
        return contestEntryId;
    }

    public Review setContestEntryId(int contestEntryId) {
        this.contestEntryId = contestEntryId;
        return this;
    }
}
