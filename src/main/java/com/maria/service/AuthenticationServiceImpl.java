package com.maria.service;

import com.maria.exception.AuthenticationException;
import com.maria.exception.NotFoundException;
import com.maria.model.account.Account;
import com.maria.model.token.AuthenticationToken;
import com.maria.repository.AccountRepository;
import com.maria.repository.AuthenticationRepository;
import com.maria.security.PrincipalUser;
import com.maria.service.api.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created   on 8/9/2017.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationRepository authenticationRepository;
    private AccountRepository accountRepository;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository, AccountRepository accountRepository) {
        this.authenticationRepository = authenticationRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public PrincipalUser authenticateByToken(String token) {
        AuthenticationToken authenticationToken = null;

        try {
            authenticationToken = authenticationRepository.findByToken(token);
        } catch (NotFoundException e) {
            throw new AuthenticationException("Invalid token provided " + token);
        }
        Account account = accountRepository.findById(authenticationToken.getAccountId());

        return mapToPrincipal(account);
    }

    private PrincipalUser mapToPrincipal(Account account) {
        return new PrincipalUser(account.getEmail(), account.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(account.getEmail())));
    }
}
