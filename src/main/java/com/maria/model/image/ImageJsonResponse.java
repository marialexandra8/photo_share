package com.maria.model.image;

/**
 * Created on 8/19/2017.
 */
public class ImageJsonResponse {
    private String name;
    private String path;

    public ImageJsonResponse() {
    }

    public ImageJsonResponse(Image image) {
        this
                .setName(image.getName())
                .setPath(image.getPath());
    }

    public String getName() {
        return name;
    }

    public ImageJsonResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ImageJsonResponse setPath(String path) {
        this.path = path;
        return this;
    }
}
