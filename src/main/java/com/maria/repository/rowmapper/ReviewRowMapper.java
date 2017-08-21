package com.maria.repository.rowmapper;


import com.maria.model.review.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created on 8/21/2017.
 */
public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("r.id");
        int userId = resultSet.getInt("r.user_id");
        int contestEntryId = resultSet.getInt("r.contest_entry_id");
        int rate = resultSet.getInt("r.rate");

        return new Review()
                .setContestEntryId(contestEntryId)
                .setId(id)
                .setRate(rate)
                .setUserId(userId);
    }
}
