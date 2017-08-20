package com.maria.repository;

import com.maria.exception.NotFoundException;
import com.maria.model.account.Gender;
import com.maria.model.user.User;
import com.maria.repository.rowmapper.UserRowMapper;
import org.omg.CosNaming.NamingContextPackage.NotFound;
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

    public User createUser(String name, String email, LocalDate birthday, Gender gender, int accountId) {
        String sql = "INSERT INTO users(name, email, birthday, gender, account_id) VALUES (:name, :email, :birthday, :gender, :account_id)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("email", email)
                .addValue("birthday", birthday)
                .addValue("gender", gender.name())
                .addValue("account_id", accountId);
        
        namedJdbcTemplate.update(sql, params, keyHolder);
        int id = keyHolder.getKey().intValue();
        return findById(id);
    }

    public User findByAccountId(int accountId) {
        String sql = "SELECT * FROM users u " +
                " JOIN accounts a ON (u.account_id = a.id)" +
                " WHERE a.id = ?";

        List<User> users = jdbcTemplate.query(sql, new Object[]{accountId}, userRowMapper);
        if (users.isEmpty()) {
            throw new NotFoundException("Cannot find user for account id: " + accountId);
        }
        return users.get(0);
    }

    public User findById(int id) {
        String sql = "SELECT * FROM users u WHERE id=?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{id}, userRowMapper);
        if (users.isEmpty()) {
            throw new NotFoundException("Cannot find user for id: " + id);
        }
        return users.get(0);
    }
}
