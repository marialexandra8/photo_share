package com.maria.service.file;

import com.maria.exception.InvalidArgumentException;
import com.maria.exception.NotFoundException;
import com.maria.service.api.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Objects;

/**
 * Created on 8/16/2017.
 */
@Service
public class FileServiceImpl implements FileService {
    @Value("${images.rootPath}")
    private String imagesRoot;
    @Value("${images.users.logo.rootPath}")
    private String usersLogoRootPath;
    @Value("${images.users.contest.rootPath}")
    private String usersContestImagesRootPath;
    @Value("${images.users.logo.url}")
    private String usersLogoUrl;
    @Value("${images.users.contest.url}")
    private String usersContestImagesUrl;
    @Value("${images.contests.logo.rootPath}")
    private String contestLogoRootPath;
    @Value("${images.contests.logo.url}")
    private String contestsLogoUrl;

    @Override
    public void saveFileOnDisk(InputStream inputStream, String filePath) {
        byte[] buffer;
        OutputStream outStream = null;
        try {
            buffer = IOUtils.toByteArray(inputStream);
            File file = new File(filePath);
            outStream = new FileOutputStream(file);
            outStream.write(buffer);
        } catch (IOException e) {
            throw new InvalidArgumentException("Cannot write image" + e);
        } finally {
            if (Objects.nonNull(outStream)) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    throw new RuntimeException("Cannot close the stream", e);
                }
            }
        }
    }

    @Override
    public void saveContestLogoFile(InputStream photoInputStream, String fileName) {
        String fullPath = String.format("%s%s/%s", imagesRoot, contestLogoRootPath, fileName);
        saveFileOnDisk(photoInputStream, fullPath);
    }

    @Override
    public void saveContestEntryFile(InputStream photoInputStream, String fileName) {
        String fullPath = String.format("%s%s/%s", imagesRoot, usersContestImagesRootPath, fileName);
        saveFileOnDisk(photoInputStream, fullPath);
    }

    @Override
    public void saveUserLogoFile(InputStream photoInputStream, String fileName) {
        String fullPath = String.format("%s%s/%s", imagesRoot, usersLogoRootPath, fileName);
        saveFileOnDisk(photoInputStream, fullPath);
    }

    @Override
    public String getRelativePathForContestEntryImage(String imageName) {
        return imageName == null ? "" : String.format("%s/%s", usersContestImagesUrl, imageName);
    }

    @Override
    public String getRelativePathForContestLogo(String imageName) {
        return imageName == null ? "" : String.format("%s/%s", contestsLogoUrl, imageName);
    }

    @Override
    public String getRelativePathForUserLogo(String imageName) {
        return imageName == null ? "" : String.format("%s/%s", usersLogoUrl, imageName);
    }


}
