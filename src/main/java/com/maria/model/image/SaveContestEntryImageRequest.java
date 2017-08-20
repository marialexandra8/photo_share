package com.maria.model.image;

import java.io.InputStream;

/**
 * `
 * Created on 8/19/2017.
 */
public class SaveContestEntryImageRequest {
    private InputStream inputStream;
    private String name;
    private int userId;
    private int contestId;
    private SupportedMimeType mimeType;

    public int getContestId() {
        return contestId;
    }

    public SaveContestEntryImageRequest setContestId(int contestId) {
        this.contestId = contestId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public SaveContestEntryImageRequest setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public SaveContestEntryImageRequest setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public String getName() {
        return name;
    }

    public SaveContestEntryImageRequest setName(String name) {
        this.name = name;
        return this;
    }

    public SupportedMimeType getMimeType() {
        return mimeType;
    }

    public SaveContestEntryImageRequest setMimeType(SupportedMimeType mimeType) {
        this.mimeType = mimeType;
        return this;
    }
}
