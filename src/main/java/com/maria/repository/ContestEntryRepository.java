package com.maria.repository;

import com.maria.model.contest.ContestEntry;
import com.maria.repository.rowmapper.ContestEntryResulSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 8/20/2017.
 */
@Repository
public class ContestEntryRepository extends BaseRepository {
    private static final ContestEntryResulSetExtractor CONTEST_ENTRY_RESUL_SET_EXTRACTOR = new ContestEntryResulSetExtractor();

    public List<ContestEntry> findForContestId(int contestId) {
        String sql = "SELECT * FROM users u JOIN contest_entries ce ON (u.id = ce.user_id) " +
                " WHERE ce.contest_id=?";
        return jdbcTemplate.query(sql, new Object[]{contestId}, CONTEST_ENTRY_RESUL_SET_EXTRACTOR);
    }

}
