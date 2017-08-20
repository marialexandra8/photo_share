package com.maria.repository.rowmapper;

import com.maria.model.contest.Contest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created on 8/20/2017.
 */
public class ContestRowMapper implements RowMapper<Contest> {
    @Override
    public Contest mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("c.id");
        String name = resultSet.getString("c.name");
        String description = resultSet.getString("c.description");
        LocalDate deadline = resultSet.getDate("c.deadline").toLocalDate();
        String logoName = resultSet.getString("c.logo");
        return new Contest()
                .setDeadline(deadline)
                .setDescription(description)
                .setName(name)
                .setId(id)
                .setLogoName(logoName);
    }
}
