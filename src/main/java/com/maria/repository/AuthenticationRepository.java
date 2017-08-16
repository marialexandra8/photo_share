package com.maria.repository;

import com.maria.authentication.TokenBasedAuthentication;
import com.maria.exception.NotFoundException;
import com.maria.model.token.AuthenticationToken;
import com.maria.repository.rowmapper.AuthenticationTokenRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created   on 8/9/2017.
 */
@Repository
public class AuthenticationRepository extends BaseRepository {
    private static AuthenticationTokenRowMapper authenticationTokenRowMapper = new AuthenticationTokenRowMapper();

    public AuthenticationToken findByToken(String token) {
        String sql = "SELECT * FROM authentication_tokens WHERE token=?";
        List<AuthenticationToken> authenticationTokens = jdbcTemplate.query(sql, new Object[]{token}, authenticationTokenRowMapper);
        if (authenticationTokens.isEmpty()) {
            throw new NotFoundException("No such token + " + token);
        }
        return authenticationTokens.get(0);
    }

    public boolean tokenExists(String token) {
        String sql = "SELECT count(*) FROM authentication_tokens WHERE token=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{token}, Integer.class) > 0;
    }

    public void deleteToken(String token) {
        String sql = "DELETE FROM authentication_tokens WHERE token = ?";
        int rowsDeleted = jdbcTemplate.update(sql, token);
        if (rowsDeleted == 0) {
            throw new NotFoundException("Token to delete: " + token);
        }
    }

    public AuthenticationToken saveToken(int accountID, String token) {
        String sql = "INSERT INTO authentication_tokens (account_id, token) VALUES (:accountID, :token)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("accountID", accountID)
                .addValue("token", token);
        namedJdbcTemplate.update(sql, params);
        return this.findByToken(token);
    }
}
