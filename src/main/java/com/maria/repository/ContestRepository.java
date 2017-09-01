package com.maria.repository;

import com.maria.exception.NotFoundException;
import com.maria.model.contest.Contest;
import com.maria.repository.rowmapper.ContestRowMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.JDBCType;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 8/20/2017.
 */
@Repository
public class ContestRepository extends BaseRepository {
    private static final ContestRowMapper CONTEST_ROW_MAPPER = new ContestRowMapper();

    public Contest createContest(String name, String description, LocalDate deadline) {
        String sql = "INSERT INTO contests(name, description, deadline) VALUES (:name, :description, :deadline)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("description", description)
                .addValue("deadline", deadline);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(sql, parameterSource, keyHolder);
        int id = keyHolder.getKey().intValue();
        return findById(id);
    }

    public List<Contest> findAll() {
        String sql = "SELECT * FROM contests c";
        return jdbcTemplate.query(sql, CONTEST_ROW_MAPPER);
    }

    public Map<Integer, Boolean> findParticipatingContestsForUserId(int userId, List<Integer> contestsId) {
        Map<Integer, Boolean> contestsWithParticipates = new HashMap<>();
        contestsId.forEach(contestId -> contestsWithParticipates.put(contestId, false));
        String sql = "SELECT c.*, ce.* FROM contests c " +
                "LEFT JOIN contest_entries ce ON c.id=ce.contest_id AND ce.user_id = :user_id " +
                " WHERE c.id IN (:contest_ids)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("contest_ids", contestsId);
        return namedJdbcTemplate.query(sql, parameterSource, (rs) -> {
            System.out.println("is false");

            while (rs.next()) {
                int contestId = rs.getInt("c.id");
                int participatingUserId = rs.getInt("ce.user_id");
                if (participatingUserId != 0) {
                    contestsWithParticipates.put(contestId, true);
                    System.out.println(contestId + "is true");
                }
                System.out.println(contestId + "is false");

            }
            return contestsWithParticipates;
        });

    }

    public List<Contest> findAllActiveContestsForUserId(int userId) {
        String sql = "SELECT * FROM contests c JOIN contest_entries ce " +
                " ON c.id = ce.contest_id AND ce.user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, CONTEST_ROW_MAPPER);
    }

    public List<Contest> findAllNewContestForUser(int userId) {
        String sql = "SELECT * FROM contests c " +
                "LEFT JOIN contest_entries ce ON c.id=ce.contest_id" +
                " WHERE ce.user_id != ? OR ce.user_id IS NULL";
        return jdbcTemplate.query(sql, new Object[]{userId}, CONTEST_ROW_MAPPER);
    }

    public Contest findById(int id) {
        String sql = "SELECT * FROM contests c WHERE id=?";
        List<Contest> contests = jdbcTemplate.query(sql, new Object[]{id}, CONTEST_ROW_MAPPER);

        if (contests.isEmpty()) {
            throw new NotFoundException("Cannot find contest with id " + id);
        }
        return contests.get(0);
    }
}
