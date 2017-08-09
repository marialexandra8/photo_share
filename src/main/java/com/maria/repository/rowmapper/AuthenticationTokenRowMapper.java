package com.maria.repository.rowmapper;

import com.maria.model.token.AuthenticationToken;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created   on 8/9/2017.
 */
public class AuthenticationTokenRowMapper implements RowMapper<AuthenticationToken> {

    @Override
    public AuthenticationToken mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("account_id");
        String token = resultSet.getString("token");
        return new AuthenticationToken(id, accountId, token);

    }
}
