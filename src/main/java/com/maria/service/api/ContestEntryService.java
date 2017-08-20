package com.maria.service.api;

import com.maria.model.contest.ContestEntry;

import java.util.List;

/**
 * Created on 8/20/2017.
 */
public interface ContestEntryService {

    List<ContestEntry> findForContestId(int contestId);
}
