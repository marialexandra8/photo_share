package com.maria.model.contest;

import com.maria.model.user.User;

import java.util.List;

/**
 * Created on 8/20/2017.
 */
public class ContestEntry {
    private User user;
    private List<String> imagesName;
    private List<String> imagesPath;

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
