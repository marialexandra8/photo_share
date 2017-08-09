package com.maria.repository;

import com.maria.exception.NotFoundException;
import com.maria.model.account.Account;
import com.maria.repository.rowmapper.AccountRowMapper;
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
}
