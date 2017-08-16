package com.maria.service.file;

import com.maria.model.file.SupportedMimeType;
import com.maria.service.api.FileService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * Created on 8/16/2017.
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public void saveFile(InputStream inputStream, String fileName, SupportedMimeType supportedMimeType) {
    }
}
