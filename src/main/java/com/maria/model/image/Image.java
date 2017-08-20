package com.maria.model.image;

/**
 * Created on 8/19/2017.
 */
public class Image {
    private int id;
    private String name;
    private SupportedMimeType mimeType;
    private String path;

    public int getId() {
        return id;
    }

    public Image setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Image setName(String name) {
        this.name = name;
        return this;
    }

    public SupportedMimeType getMimeType() {
        return mimeType;
    }

    public Image setMimeType(SupportedMimeType mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Image setPath(String path) {
        this.path = path;
        return this;
    }
}
