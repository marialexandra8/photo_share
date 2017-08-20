package com.maria.service.contest;

import com.maria.model.contest.ContestEntry;
import com.maria.repository.ContestEntryRepository;
import com.maria.service.api.ContestEntryService;
import com.maria.service.api.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 8/20/2017.
 */
@Service
public class ContestEntryServiceImpl implements ContestEntryService {
    private ContestEntryRepository contestEntryRepository;
    private FileService fileService;

    @Autowired
    public ContestEntryServiceImpl(ContestEntryRepository contestEntryRepository, FileService fileService) {
        this.contestEntryRepository = contestEntryRepository;
        this.fileService = fileService;
    }

    @Override
    public List<ContestEntry> findForContestId(int contestId) {
        return addUrl(contestEntryRepository.findForContestId(contestId));
    }

    private ContestEntry addUrl(ContestEntry contestEntry) {
        return contestEntry.setImagesPath(contestEntry.getImagesName().stream()
                .map(image -> fileService.getRelativePathForContestEntryImage(image))
                .collect(Collectors.toList()));
    }

    private List<ContestEntry> addUrl(List<ContestEntry> contestEntries) {
        return contestEntries.stream()
                .map(contestEntry -> new ContestEntry()
                        .setImagesPath(addUrl(contestEntry).getImagesPath())
                        .setImagesName(contestEntry.getImagesName())
                        .setUser(contestEntry.getUser()))
                .collect(Collectors.toList());
    }
}
