package com.maria.repository;

import com.maria.exception.NotFoundException;
import com.maria.model.contest.Contest;
import com.maria.repository.rowmapper.ContestRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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

    public Contest findById(int id) {
        String sql = "SELECT * FROM contests c WHERE id=?";
        List<Contest> contests = jdbcTemplate.query(sql, new Object[]{id}, CONTEST_ROW_MAPPER);

        if (contests.isEmpty()) {
            throw new NotFoundException("Cannot find contest with id " + id);
        }
        return contests.get(0);
    }
}
