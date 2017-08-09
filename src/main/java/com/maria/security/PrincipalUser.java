package com.maria.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created   on 8/9/2017.
 */
public class PrincipalUser extends User {
    private String email;

    // username will be it`s email
    public PrincipalUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
    }

    public String getEmail() {
        return email;
    }
}
