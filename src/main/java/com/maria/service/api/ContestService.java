package com.maria.service.api;

import com.maria.model.contest.Contest;
import com.maria.model.contest.CreateContestRequest;

import java.util.List;
import java.util.Map;

/**
 * Created on 8/20/2017.
 */
public interface ContestService {
    Contest createContest(CreateContestRequest createContestRequest);

    List<Contest> findAll();

    List<Contest> findAllActiveContestsForUserId(int userId);

    Contest findById(int id);

    List<Contest> findAllNewForUser(int userId);

    Map<Integer, Boolean> findParticipatingContestsForUserId(int userId, List<Integer> contestIds);
}
