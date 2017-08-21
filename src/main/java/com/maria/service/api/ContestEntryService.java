package com.maria.service.api;

import com.maria.model.contest.ContestEntry;
import com.maria.model.contest.CreateContestEntryRequest;

import java.util.List;

/**
 * Created on 8/20/2017.
 */
public interface ContestEntryService {

    ContestEntry createContestEntry(CreateContestEntryRequest createContestEntryRequest);

    List<ContestEntry> findForContestId(int contestId);
}
