package com.maria.security;

import com.maria.model.account.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created   on 8/9/2017.
 */
public class PrincipalUser extends User {
    private Account account;

    // username will be it`s email
    public PrincipalUser(Account account, Collection<? extends GrantedAuthority> authorities) {
        super(account.getEmail(), account.getPassword(), authorities);
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
