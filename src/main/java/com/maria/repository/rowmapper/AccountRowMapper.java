package com.maria.repository.rowmapper;

import com.maria.model.account.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created   on 8/9/2017.
 */
public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        int id = resultSet.getInt("accounts.id");
        return new Account()
                .setEmail(email)
                .setPassword(password)
                .setId(id);
    }
}
