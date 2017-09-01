package com.maria.controller;

import com.maria.exception.InvalidArgumentException;
import com.maria.model.contest.*;
import com.maria.model.image.SaveContestEntryImageRequest;
import com.maria.model.image.SaveContestLogoRequest;
import com.maria.model.image.SupportedMimeType;
import com.maria.model.user.User;
import com.maria.security.PrincipalUser;
import com.maria.service.api.ContestEntryService;
import com.maria.service.api.ContestService;
import com.maria.service.api.ImageService;
import com.maria.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 8/20/2017.
 */
@RestController
@RequestMapping("/api")
public class ContestController {
    private ContestService contestService;
    private UserService userService;
    private ImageService imageService;
    private ContestEntryService contestEntryService;

    @Autowired
    public ContestController(ContestService contestService, UserService userService, ImageService imageService, ContestEntryService contestEntryService) {
        this.contestService = contestService;
        this.userService = userService;
        this.imageService = imageService;
        this.contestEntryService = contestEntryService;
    }

    @RequestMapping(value = "/contest", method = RequestMethod.POST)
    public ContestJsonResponse createContest(@RequestBody CreateContestJsonRequest createContestJsonRequest) {
        CreateContestRequest createContestRequest = new CreateContestRequest()
                .setDeadline(createContestJsonRequest.getDeadline())
                .setDescription(createContestJsonRequest.getDescription())
                .setName(createContestJsonRequest.getName());
        Contest contest = contestService.createContest(createContestRequest);

        return new ContestJsonResponse(contest);
    }

    @RequestMapping(value = "/contests", method = RequestMethod.GET)
    public List<ContestJsonResponse> findAll() {
        return toContestJsonListResponse(contestService.findAll());
    }

    @RequestMapping(value = "/contests/with-participation", method = RequestMethod.GET)
    public List<ContestWithParticipatingJsonResponse> findAllWithParticipating(@AuthenticationPrincipal PrincipalUser principalUser) {
        User user = userService.findByAccountId(principalUser.getAccount().getId());
        List<ContestWithParticipatingJsonResponse> contests = toContestWitParticipatingJsonResponse(contestService.findAll());
        Map<Integer, Boolean> participatingContestsForUserId = contestService.findParticipatingContestsForUserId(user.getId(), contests
                .stream()
                .map(ContestWithParticipatingJsonResponse::getId)
                .collect(Collectors.toList()));
        contests.forEach(contest -> contest.setUserIsParticipating(participatingContestsForUserId.get(contest.getId())));
        return contests;
    }

    @RequestMapping(value = "/contests/new", method = RequestMethod.GET)
    public List<ContestJsonResponse> findAllNewForUser(@AuthenticationPrincipal PrincipalUser principalUser) {
        User user = userService.findByAccountId(principalUser.getAccount().getId());
        return toContestJsonListResponse(contestService.findAllNewForUser(user.getId()));
    }

    @RequestMapping(value = "/contests/{contestId}", method = RequestMethod.GET)
    public ContestJsonResponse findContestById(@PathVariable Integer contestId) {
        return new ContestJsonResponse(contestService.findById(contestId));
    }

    @RequestMapping(value = "/contests/mine/active", method = RequestMethod.GET)
    public List<ContestJsonResponse> findAllOwnedActiveContest(@AuthenticationPrincipal PrincipalUser principalUser) {
        User user = userService.findByAccountId(principalUser.getAccount().getId());
        List<Contest> contests = contestService.findAllActiveContestsForUserId(user.getId());

        return toContestJsonListResponse(contests);
    }

    @RequestMapping(value = "/contests/entries/{contestId}", method = RequestMethod.GET)
    public List<ContestEntryJsonResponse> findAllContestEntryForContestId(@PathVariable Integer contestId) {
        return toContestEntryJsonListResponse(contestEntryService.findForContestId(contestId));
    }

    @RequestMapping(value = "/contest/logo/upload", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> uploadContestLogo(@RequestParam("logo") MultipartFile logoFile,
                                                        @RequestParam("contest_id") Integer contestId) {

        if (logoFile.isEmpty()) {
            throw new InvalidArgumentException("Logo content cannot be empty!");
        }
        try {
            InputStream logoInputStream = logoFile.getInputStream();
            String originalFileName = logoFile.getOriginalFilename();
            SupportedMimeType mimeType = SupportedMimeType.getByContentType(logoFile.getContentType());
            SaveContestLogoRequest saveContestLogoRequest = new SaveContestLogoRequest()
                    .setContestId(contestId)
                    .setImageName(originalFileName)
                    .setInputStream(logoInputStream)
                    .setMimeType(mimeType);

            imageService.saveContestLogo(saveContestLogoRequest);
        } catch (IOException e) {
            throw new InvalidArgumentException("Cannot read content! " + e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/contest/entries/upload", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> uploadContestEntryImage(
            @RequestParam("firstImage") MultipartFile firstImage,
            @RequestParam("secondImage") MultipartFile secondImage,
            @RequestParam("contest_id") Integer contestId,
            @AuthenticationPrincipal PrincipalUser principalUser) {

        List<MultipartFile> images = Arrays.asList(firstImage, secondImage);
        if (images.isEmpty()) {
            throw new InvalidArgumentException("The images content cannot be empty");
        }
        User user = userService.findByAccountId(principalUser.getAccount().getId());

        CreateContestEntryRequest createContestEntryRequest = new CreateContestEntryRequest()
                .setContestId(contestId)
                .setUserId(user.getId());
        ContestEntry contestEntry = contestEntryService.createContestEntry(createContestEntryRequest);
        images.forEach(imageRequest -> {
            try {
                InputStream inputStream = imageRequest.getInputStream();
                String originalFilename = imageRequest.getOriginalFilename();
                SupportedMimeType mimeType = SupportedMimeType.getByContentType(imageRequest.getContentType());
                SaveContestEntryImageRequest saveContestEntryImageRequest = new SaveContestEntryImageRequest()
                        .setInputStream(inputStream)
                        .setName(originalFilename)
                        .setMimeType(mimeType)
                        .setContestEntryId(contestEntry.getId());

                imageService.saveImageForContestEntry(saveContestEntryImageRequest);

            } catch (IOException e) {
                throw new InvalidArgumentException("Cannot read content" + e);
            }
        });

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<ContestEntryJsonResponse> toContestEntryJsonListResponse(List<ContestEntry> contestEntries) {
        return contestEntries.stream()
                .map(ContestEntryJsonResponse::new)
                .collect(Collectors.toList());
    }

    private List<ContestWithParticipatingJsonResponse> toContestWitParticipatingJsonResponse(List<Contest> contests) {
        return contests.stream()
                .map(ContestWithParticipatingJsonResponse::new)
                .collect(Collectors.toList());
    }

    private List<ContestJsonResponse> toContestJsonListResponse(List<Contest> contests) {
        return contests.stream()
                .map(ContestJsonResponse::new)
                .collect(Collectors.toList());
    }

}
