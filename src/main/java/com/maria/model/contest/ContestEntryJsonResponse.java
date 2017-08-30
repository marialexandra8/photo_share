package com.maria.model.contest;

import com.maria.model.user.UserJsonResponse;

import java.util.List;

/**
 * Created on 8/20/2017.
 */
public class ContestEntryJsonResponse {
    private Integer id;
    private UserJsonResponse userJsonResponse;
    private List<String> imagesUrl;
    private Integer likes;


    public ContestEntryJsonResponse(ContestEntry contestEntry) {
        this
                .setId(contestEntry.getId())
                .setImagesUrl(contestEntry.getImagesPath())
                .setLikes(contestEntry.getReviews().size())
                .setUserJsonResponse(new UserJsonResponse(contestEntry.getUser()));
    }

    public ContestEntryJsonResponse() {
    }

    public Integer getLikes() {
        return likes;
    }

    public ContestEntryJsonResponse setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ContestEntryJsonResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public UserJsonResponse getUserJsonResponse() {
        return userJsonResponse;
    }

    public ContestEntryJsonResponse setUserJsonResponse(UserJsonResponse userJsonResponse) {
        this.userJsonResponse = userJsonResponse;
        return this;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public ContestEntryJsonResponse setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
        return this;
    }
}
