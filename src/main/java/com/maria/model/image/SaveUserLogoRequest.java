package com.maria.model.image;

import java.io.InputStream;

/**
 * Created on 8/20/2017.
 */
public class SaveUserLogoRequest {
    private InputStream imageStream;
    private String imageName;
    private SupportedMimeType supportedMimeType;
    private int userId;


    public InputStream getImageStream() {
        return imageStream;
    }

    public SaveUserLogoRequest setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
        return this;
    }

    public SupportedMimeType getSupportedMimeType() {
        return supportedMimeType;
    }

    public SaveUserLogoRequest setSupportedMimeType(SupportedMimeType supportedMimeType) {
        this.supportedMimeType = supportedMimeType;
        return this;
    }

    public String getImageName() {
        return imageName;
    }

    public SaveUserLogoRequest setImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public SaveUserLogoRequest setUserId(int userId) {
        this.userId = userId;
        return this;
    }
}
