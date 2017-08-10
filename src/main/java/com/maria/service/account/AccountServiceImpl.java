package com.maria.service.account;

import com.maria.model.account.Account;
import com.maria.repository.AccountRepository;
import com.maria.service.account.model.CreateAccountRequest;
import com.maria.service.api.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created on 8/10/2017.
 */
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public Account createAccount(CreateAccountRequest createAccountRequest) {
        String email = createAccountRequest.getEmail();
        String password = createAccountRequest.getPassword();

        return accountRepository.createAccount(email, password);
    }
}
