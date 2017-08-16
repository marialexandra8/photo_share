package com.maria.model.authentication;

/**
 * Created on 8/16/2017.
 */
public class AuthenticationJsonRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public AuthenticationJsonRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthenticationJsonRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
