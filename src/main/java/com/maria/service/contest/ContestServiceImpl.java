package com.maria.service.contest;

import com.maria.model.contest.Contest;
import com.maria.model.contest.CreateContestRequest;
import com.maria.repository.ContestRepository;
import com.maria.service.api.ContestService;
import com.maria.service.api.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created on 8/20/2017.
 */
@Service
public class ContestServiceImpl implements ContestService {
    private ContestRepository contestRepository;
    private FileService fileService;

    @Autowired
    public ContestServiceImpl(ContestRepository contestRepository, FileService fileService) {
        this.contestRepository = contestRepository;
        this.fileService = fileService;
    }

    @Override
    public Contest createContest(CreateContestRequest createContestRequest) {
        String name = createContestRequest.getName();
        String description = createContestRequest.getDescription();
        LocalDate deadline = createContestRequest.getDeadline();
        return contestRepository.createContest(name, description, deadline);
    }

    @Override
    public List<Contest> findAll() {
        List<Contest> contests = contestRepository.findAll();
        contests.forEach(contest -> contest.setLogoPath(fileService.getRelativePathForContestLogo(contest.getLogoName())));
        return contests;
    }

    @Override
    public List<Contest> findAllActiveContestsForUserId(int userId) {
        List<Contest> contests = contestRepository.findAllActiveContestsForUserId(userId);
        contests.forEach(contest -> contest.setLogoPath(fileService.getRelativePathForContestLogo(contest.getLogoName())));
        return contests;
    }

    @Override
    public Contest findById(int id) {
        Contest contest = contestRepository.findById(id);
        return contest.setLogoPath(fileService.getRelativePathForContestLogo(contest.getLogoName()));
    }

    @Override
    public List<Contest> findAllNewForUser(int userId) {
        List<Contest> contests = contestRepository.findAllNewContestForUser(userId);
        contests.forEach(contest -> contest.setLogoPath(fileService.getRelativePathForContestLogo(contest.getLogoName())));
        return contests;
    }
}
