package com.maria.model.review;

/**
 * Created on 8/21/2017.
 */
public class ReviewJsonResponse {
    private int id;
    private int rate;

    public ReviewJsonResponse(Review review) {
        this
                .setId(review.getId())
                .setRate(review.getRate());
    }

    public ReviewJsonResponse() {
    }

    public int getId() {
        return id;
    }

    public ReviewJsonResponse setId(int id) {
        this.id = id;
        return this;
    }

    public int getRate() {
        return rate;
    }

    public ReviewJsonResponse setRate(int rate) {
        this.rate = rate;
        return this;
    }
}
