package com.maria.controller;

import com.maria.exception.InvalidArgumentException;
import com.maria.model.image.SaveContestEntryImageRequest;
import com.maria.model.image.SaveUserLogoRequest;
import com.maria.model.image.SupportedMimeType;
import com.maria.model.user.User;
import com.maria.model.user.UserJsonResponse;
import com.maria.security.PrincipalUser;
import com.maria.service.api.ImageService;
import com.maria.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created on 8/20/2017.
 */
@RequestMapping("/api")
@RestController
public class UserController {
    private UserService userService;
    private ImageService imageService;

    @Autowired
    public UserController(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public UserJsonResponse getOwnedUser(@AuthenticationPrincipal PrincipalUser principalUser) {
        User user = userService.findByAccountId(principalUser.getAccount().getId());
        return new UserJsonResponse(user);
    }

    @RequestMapping(value = "/user/logo/upload", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> uploadUserLogo(
            @RequestParam(value = "logo") MultipartFile logoFile,
            @AuthenticationPrincipal PrincipalUser principalUser) {
        if (logoFile.isEmpty()) {
            throw new InvalidArgumentException("The images content cannot be empty");
        }
        User user = userService.findByAccountId(principalUser.getAccount().getId());
        try {
            InputStream inputStream = logoFile.getInputStream();
            String originalFilename = logoFile.getOriginalFilename();
            SupportedMimeType mimeType = SupportedMimeType.getByContentType(logoFile.getContentType());
            SaveUserLogoRequest saveUserLogoRequest = new SaveUserLogoRequest()
                    .setImageStream(inputStream)
                    .setImageName(originalFilename)
                    .setSupportedMimeType(mimeType)
                    .setUserId(user.getId());

            imageService.saveUserLogo(saveUserLogoRequest);
        } catch (IOException e) {
            throw new InvalidArgumentException("Cannot read content" + e);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
