package com.maria.service.api;

import java.io.InputStream;

/**
 * Created on 8/16/2017.
 */
public interface FileService {
    void saveFileOnDisk(InputStream inputStream, String filePath);

    void saveContestLogoFile(InputStream photoInpuStream, String fileName);

    void saveContestEntryFile(InputStream photoInputStream, String fileName);

    void saveUserLogoFile(InputStream photoInputStream, String fileName);

    String getRelativePathForContestEntryImage(String imageName);

    String getRelativePathForContestLogo(String imageName);

    String getRelativePathForUserLogo(String imageName);
}
