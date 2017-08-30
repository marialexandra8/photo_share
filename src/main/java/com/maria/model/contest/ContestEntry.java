package com.maria.model.contest;

import com.maria.model.review.Review;
import com.maria.model.user.User;

import java.util.List;

/**
 * Created on 8/20/2017.
 */
public class ContestEntry {
    private int id;
    private int contestId;
    private User user;
    private List<String> imagesName;
    private List<String> imagesPath;
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public ContestEntry setReviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public int getId() {
        return id;
    }

    public ContestEntry setId(int id) {
        this.id = id;
        return this;
    }

    public int getContestId() {
        return contestId;
    }

    public ContestEntry setContestId(int contestId) {
        this.contestId = contestId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ContestEntry setUser(User user) {
        this.user = user;
        return this;
    }

    public List<String> getImagesName() {
        return imagesName;
    }

    public ContestEntry setImagesName(List<String> imagesName) {
        this.imagesName = imagesName;
        return this;
    }

    public List<String> getImagesPath() {
        return imagesPath;
    }

    public ContestEntry setImagesPath(List<String> imagesPath) {
        this.imagesPath = imagesPath;
        return this;
    }
}
