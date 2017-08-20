package com.maria.model.image;

import com.maria.exception.UnsupportedTypeException;

import java.util.stream.Stream;

/**
 * Created on 8/16/2017.
 */
public enum SupportedMimeType {
    JPG("image/jpg", "jpg"), JPEG("image/jpeg", "jpeg"), GIF("image/gif", "gif"), PNG("image/png", "png");
    private Object[] values;


    SupportedMimeType(Object... values) {
        this.values = values;
    }

    public String getContentType() {
        return (String) values[0];
    }

    public String getExtension() {
        return (String) values[1];
    }

    public static SupportedMimeType getByContentType(String contentType) {
        return Stream.of(SupportedMimeType.values())
                .filter(supportedMimeType -> supportedMimeType.getContentType().equalsIgnoreCase(contentType))
                .findFirst()
                .orElseThrow(() -> new UnsupportedTypeException("Invalid content type: " + contentType));
    }

    public static SupportedMimeType getByExtension(String extension) {
        return Stream.of(SupportedMimeType.values())
                .filter(supportedMimeType -> supportedMimeType.getExtension().equalsIgnoreCase(extension))
                .findFirst()
                .orElseThrow(() -> new UnsupportedTypeException("Invalid extension: " + extension));
    }
}
