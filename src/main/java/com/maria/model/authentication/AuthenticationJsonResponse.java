package com.maria.model.authentication;

import com.maria.model.account.AccountJsonResponse;

/**
 * Created on 8/16/2017.
 */
public class AuthenticationJsonResponse {
    private AccountJsonResponse accountJsonResponse;
    private String token;


    public AuthenticationJsonResponse() {
    }

    public AuthenticationJsonResponse(AuthenticationResponse authenticationResponse) {
        this
                .setAccountJsonResponse(new AccountJsonResponse(authenticationResponse.getAccount()))
                .setToken(authenticationResponse.getToken());
    }

    public AccountJsonResponse getAccountJsonResponse() {
        return accountJsonResponse;
    }

    public AuthenticationJsonResponse setAccountJsonResponse(AccountJsonResponse accountJsonResponse) {
        this.accountJsonResponse = accountJsonResponse;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AuthenticationJsonResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
