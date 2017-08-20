package com.maria.service.api;

import com.maria.model.contest.Contest;
import com.maria.model.contest.CreateContestRequest;

import java.util.List;

/**
 * Created on 8/20/2017.
 */
public interface ContestService {
    Contest createContest(CreateContestRequest createContestRequest);

    List<Contest> findAll();
}
