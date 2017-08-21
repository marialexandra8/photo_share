package com.maria.model.contest;

/**
 * Created on 8/21/2017.
 */
public class CreateContestEntryRequest {
    private int userId;
    private int contestId;

    public int getUserId() {
        return userId;
    }

    public CreateContestEntryRequest setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getContestId() {
        return contestId;
    }

    public CreateContestEntryRequest setContestId(int contestId) {
        this.contestId = contestId;
        return this;
    }
}
