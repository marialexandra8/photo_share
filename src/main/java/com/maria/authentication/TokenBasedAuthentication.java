package com.maria.authentication;

import com.maria.security.PrincipalUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created   on 8/9/2017.
 */
public class TokenBasedAuthentication implements Authentication {
    private PrincipalUser principalUser;
    private boolean authenticated;
    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return principalUser.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principalUser;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return principalUser.getUsername();
    }

    public TokenBasedAuthentication setPrincipalUser(PrincipalUser principalUser) {
        this.principalUser = principalUser;
        return this;
    }

    public TokenBasedAuthentication setToken(String token) {
        this.token = token;
        return this;
    }

    public String getToken() {
        return token;
    }
}
