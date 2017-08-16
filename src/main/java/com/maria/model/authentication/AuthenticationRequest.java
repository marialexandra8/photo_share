package com.maria.model.authentication;

/**
 * Created on 8/16/2017.
 */
public class AuthenticationRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public AuthenticationRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthenticationRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
