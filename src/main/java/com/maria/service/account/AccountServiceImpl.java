package com.maria.service.account;

import com.maria.exception.PermissionDeniedException;
import com.maria.model.account.Account;
import com.maria.model.authentication.AuthenticationRequest;
import com.maria.repository.AccountRepository;
import com.maria.repository.AuthenticationRepository;
import com.maria.repository.UserRepository;
import com.maria.service.account.model.CreateAccountRequest;
import com.maria.service.api.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 8/10/2017.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private AuthenticationRepository authenticationRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AuthenticationRepository authenticationRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.authenticationRepository = authenticationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Account createAccount(CreateAccountRequest createAccountRequest) {
        String encryptedPassword = passwordEncoder.encode(createAccountRequest.getPassword());
        Account account = accountRepository.createAccount(createAccountRequest.getEmail(), encryptedPassword);
        //TODO extract this if confirm account feature is added
        userRepository.createUser(createAccountRequest.getName(), createAccountRequest.getEmail(), createAccountRequest.getBirthday(),
                createAccountRequest.getGender());

        return account;
    }

}
