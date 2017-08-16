package com.maria.repository;

import com.maria.exception.NotFoundException;
import com.maria.model.account.Account;
import com.maria.repository.rowmapper.AccountRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created   on 8/9/2017.
 */
@Repository
public class AccountRepository extends BaseRepository {
    private static AccountRowMapper accountRowMapper = new AccountRowMapper();


    public Account findById(int id) {
        String sql = "SELECT * FROM accounts WHERE id=?";
        List<Account> accounts = jdbcTemplate.query(sql, new Object[]{id}, accountRowMapper);
        if (accounts.isEmpty()) {
            throw new NotFoundException("No account for id " + id);
        }
        return accounts.get(0);
    }

    public Account findByEmail(String email) {
        String sql = "SELECT * FROM accounts WHERE email=?";
        List<Account> accounts = jdbcTemplate.query(sql, new Object[]{email}, accountRowMapper);
        if (accounts.isEmpty()) {
            throw new NotFoundException("No account for email: " + email);
        }
        return accounts.get(0);
    }

    public Account createAccount(String email, String password) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO accounts(email, password) VALUES (:email, :password)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", email)
                .addValue("password", password);
        namedJdbcTemplate.update(sql, params, keyHolder);
        int id = keyHolder.getKey().intValue();
        return findById(id);
    }
}
