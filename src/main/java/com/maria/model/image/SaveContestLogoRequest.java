package com.maria.model.image;

import java.io.InputStream;

/**
 * Created on 8/20/2017.
 */
public class SaveContestLogoRequest {
    private InputStream inputStream;
    private String imageName;
    private SupportedMimeType mimeType;
    private int contestId;

    public String getImageName() {
        return imageName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public SaveContestLogoRequest setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public SaveContestLogoRequest setImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public SupportedMimeType getMimeType() {
        return mimeType;
    }

    public SaveContestLogoRequest setMimeType(SupportedMimeType mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public int getContestId() {
        return contestId;
    }

    public SaveContestLogoRequest setContestId(int contestId) {
        this.contestId = contestId;
        return this;
    }
}
