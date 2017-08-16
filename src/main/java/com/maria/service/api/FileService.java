package com.maria.service.api;

import com.maria.model.file.SupportedMimeType;

import java.io.InputStream;

/**
 * Created on 8/16/2017.
 */
public interface FileService {
    void saveFile(InputStream inputStream, String fileName, SupportedMimeType supportedMimeType);
}
