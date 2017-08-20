package com.maria.service;

import com.maria.exception.AuthenticationException;
import com.maria.exception.NotFoundException;
import com.maria.exception.PermissionDeniedException;
import com.maria.model.account.Account;
import com.maria.model.authentication.AuthenticationRequest;
import com.maria.model.authentication.AuthenticationResponse;
import com.maria.model.token.AuthenticationToken;
import com.maria.repository.AccountRepository;
import com.maria.repository.AuthenticationRepository;
import com.maria.security.PrincipalUser;
import com.maria.service.api.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

/**
 * Created   on 8/9/2017.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationRepository authenticationRepository;
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.authenticationRepository = authenticationRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public AuthenticationResponse authenticateAccount(AuthenticationRequest authenticationRequest) {
        Account account = accountRepository.findByEmail(authenticationRequest.getEmail());
        if (!passwordEncoder.matches(authenticationRequest.getPassword(), account.getPassword())) {
            throw new PermissionDeniedException("Incorrect email/password");
        }
        AuthenticationToken authenticationToken = authenticationRepository.saveToken(account.getId(), generateToken());
        //TODO if account confirmation is added , check
        return new AuthenticationResponse()
                .setAccount(account)
                .setToken(authenticationToken.getToken());
    }

    private String generateToken() {
        String token = UUID.randomUUID().toString();
        while (authenticationRepository.tokenExists(token)) {
            token = UUID.randomUUID().toString();
        }
        return token;
    }

    private PrincipalUser mapToPrincipal(Account account) {
        //TODO change account type to match
        return new PrincipalUser(account, Collections.singletonList(new SimpleGrantedAuthority(account.getEmail())));
    }
}
