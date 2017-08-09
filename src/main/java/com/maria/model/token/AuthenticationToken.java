package com.maria.model.token;

/**
 * Created   on 8/9/2017.
 */
public class AuthenticationToken extends Token {
    private int id;
    private int accountId;

    public AuthenticationToken(int id, int accountId, String token) {
        this.id = id;
        this.accountId = accountId;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public int getAccountId() {
        return accountId;
    }
}
