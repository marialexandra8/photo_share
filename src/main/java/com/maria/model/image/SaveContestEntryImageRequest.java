package com.maria.model.image;

import java.io.InputStream;

/**
 * `
 * Created on 8/19/2017.
 */
public class SaveContestEntryImageRequest {
    private InputStream inputStream;
    private String name;
    private int contestEntryId;
    private SupportedMimeType mimeType;

    public int getContestEntryId() {
        return contestEntryId;
    }

    public SaveContestEntryImageRequest setContestEntryId(int contestEntryId) {
        this.contestEntryId = contestEntryId;
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
