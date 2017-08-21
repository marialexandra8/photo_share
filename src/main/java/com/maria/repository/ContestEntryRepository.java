package com.maria.repository;

import com.maria.exception.NotFoundException;
import com.maria.model.contest.ContestEntry;
import com.maria.repository.rowmapper.ContestEntryResulSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 8/20/2017.
 */
@Repository
public class ContestEntryRepository extends BaseRepository {
    private static final ContestEntryResulSetExtractor CONTEST_ENTRY_RESUL_SET_EXTRACTOR = new ContestEntryResulSetExtractor();

    public ContestEntry createContestEntry(int userId, int contestId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO contest_entries(user_id, contest_id) VALUES(:user_id, :contest_id)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("contest_id", contestId);

        namedJdbcTemplate.update(sql, parameterSource, keyHolder);
        int id = keyHolder.getKey().intValue();
        return findById(id);
    }

    public ContestEntry findById(int id) {
        String sql = "SELECT * FROM users u " +
                " JOIN contest_entries ce ON (u.id = ce.user_id) " +
                " LEFT JOIN contest_entry_images cei ON (ce.id = cei.contest_entry_id) " +
                " WHERE ce.id=?";
        List<ContestEntry> contestEntries = jdbcTemplate.query(sql, new Object[]{id}, CONTEST_ENTRY_RESUL_SET_EXTRACTOR);
        if (contestEntries.isEmpty()) {
            throw new NotFoundException("Cannot find contest entry for id: " + id);
        }
        return contestEntries.get(0);
    }

    public List<ContestEntry> findByContestId(int contestId) {
        String sql = "SELECT * FROM users u " +
                " JOIN contest_entries ce ON (u.id = ce.user_id) " +
                " JOIN contest_entry_images cei ON (ce.id = cei.contest_entry_id) " +
                " JOIN reviews r ON (ce.id = r.contest_entry_id) " +
                " WHERE ce.contest_id=?";
        return jdbcTemplate.query(sql, new Object[]{contestId}, CONTEST_ENTRY_RESUL_SET_EXTRACTOR);
    }

}
