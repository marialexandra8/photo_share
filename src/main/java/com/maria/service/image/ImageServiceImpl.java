package com.maria.service.image;

import com.maria.model.image.SaveContestLogoRequest;
import com.maria.model.image.SaveUserLogoRequest;
import com.maria.model.image.SupportedMimeType;
import com.maria.repository.ImageRepository;
import com.maria.service.api.FileService;
import com.maria.service.api.ImageService;
import com.maria.model.image.SaveContestEntryImageRequest;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

/**
 * Created on 8/19/2017.
 */
@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;
    private FileService fileService;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, FileService fileService) {
        this.imageRepository = imageRepository;
        this.fileService = fileService;
    }

    @Override
    public void saveImageForContestEntry(SaveContestEntryImageRequest saveContestEntryImageRequest) {
        //TODO validate n pics/user
        String fileName = saveContestEntryImageRequest.getName();
        SupportedMimeType mimeType = saveContestEntryImageRequest.getMimeType();
        InputStream inputStream = saveContestEntryImageRequest.getInputStream();
        int userId = saveContestEntryImageRequest.getUserId();
        int contestId = saveContestEntryImageRequest.getContestId();

        while (imageRepository.imageNameExistsInContestEntry(fileName)) {
            fileName = addRandomIntToFileName(fileName, mimeType);
        }
        fileService.saveContestEntryFile(inputStream, fileName);
        imageRepository.saveImageForContestEntry(fileName, mimeType, userId, contestId);
    }

    @Override
    public void saveUserLogo(SaveUserLogoRequest saveUserLogoRequest) {
        String fileName = saveUserLogoRequest.getImageName();
        SupportedMimeType mimeType = saveUserLogoRequest.getSupportedMimeType();
        InputStream inputStream = saveUserLogoRequest.getImageStream();
        int userId = saveUserLogoRequest.getUserId();

        while (imageRepository.imageNameExistsInUserLogo(fileName)) {
            fileName = addRandomIntToFileName(fileName, mimeType);
        }
        fileService.saveUserLogoFile(inputStream, fileName);
        imageRepository.saveLogoForUser(fileName, userId);
    }

    @Override
    public void saveContestLogo(SaveContestLogoRequest saveContestLogoRequest) {
        String fileName = saveContestLogoRequest.getImageName();
        SupportedMimeType mimeType = saveContestLogoRequest.getMimeType();
        InputStream inputStream = saveContestLogoRequest.getInputStream();
        int contestId = saveContestLogoRequest.getContestId();

        while (imageRepository.imageNameExistsInContestLogo(fileName)) {
            fileName = addRandomIntToFileName(fileName, mimeType);
        }
        fileService.saveContestLogoFile(inputStream, fileName);
        imageRepository.saveLogoForContest(fileName, contestId);
    }


    private String addRandomIntToFileName(String fileName, SupportedMimeType mimeType) {
        int extensionIndexStart = fileName.lastIndexOf('.');
        return fileName.substring(0, extensionIndexStart) + RandomUtils.nextInt(0, 9) + "." + mimeType.getExtension();
    }
}
