package com.maria.service.account;

import com.maria.exception.AlreadyExistsException;
import com.maria.model.account.Account;
import com.maria.model.user.CreateUserRequest;
import com.maria.repository.AccountRepository;
import com.maria.repository.AuthenticationRepository;
import com.maria.repository.UserRepository;
import com.maria.model.account.CreateAccountRequest;
import com.maria.service.api.AccountService;
import com.maria.service.api.UserService;
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
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AuthenticationRepository authenticationRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.authenticationRepository = authenticationRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @Override
    public Account createAccount(CreateAccountRequest createAccountRequest) {
        String encryptedPassword = passwordEncoder.encode(createAccountRequest.getPassword());
        if (accountRepository.accountEmailExists(createAccountRequest.getEmail())) {
            throw new AlreadyExistsException("An account with this email is already registered");
        }
        Account account = accountRepository.createAccount(createAccountRequest.getEmail(), encryptedPassword);
        //TODO extract this if confirm account feature is added
        CreateUserRequest createUserRequest = new CreateUserRequest()
                .setAccountId(account.getId())
                .setBirthday(createAccountRequest.getBirthday())
                .setEmail(createAccountRequest.getEmail())
                .setGender(createAccountRequest.getGender())
                .setName(createAccountRequest.getName());
        userService.createUser(createUserRequest);

        return account;
    }

}
