package com.maria.model.review;

/**
 * Created on 8/21/2017.
 */
public class ReviewJsonResponse {
    private int id;

    public ReviewJsonResponse(Review review) {
        this
                .setId(review.getId());
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

}
