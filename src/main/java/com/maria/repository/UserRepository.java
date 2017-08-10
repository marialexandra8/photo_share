package com.maria.repository;

import com.maria.exception.NotFoundException;
import com.maria.model.account.Gender;
import com.maria.model.user.User;
import com.maria.repository.rowmapper.UserRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created on 8/10/2017.
 */
@Repository
public class UserRepository extends BaseRepository {
    private UserRowMapper userRowMapper = new UserRowMapper();

    public User createUser(String name, String email, LocalDate birthday, Gender gender) {
        String sql = "INSERT INTO users(name, email, birthday, gender) VALUES (:name, :email, :birthday, :gender)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("email", email)
                .addValue("birthday", birthday)
                .addValue("gender", gender.name());
        namedJdbcTemplate.update(sql, params, keyHolder);
        int id = keyHolder.getKey().intValue();
        return findById(id);
    }

    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id=?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{id}, userRowMapper);
        if (users.isEmpty()) {
            throw new NotFoundException("Cannot find user for id: " + id);
        }
        return users.get(0);
    }
}
