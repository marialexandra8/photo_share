package com.maria.model.authentication;

import com.maria.model.account.Account;
import com.maria.model.token.Token;

/**
 * Created on 8/16/2017.
 */
public class AuthenticationResponse {
    private Account account;
    private String token;

    public Account getAccount() {
        return account;
    }

    public AuthenticationResponse setAccount(Account account) {
        this.account = account;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AuthenticationResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
