package com.maria.controller;

import com.maria.converter.AccountConverter;
import com.maria.model.account.Account;
import com.maria.model.account.AccountJsonResponse;
import com.maria.model.account.CreateAccountJsonRequest;
import com.maria.service.account.model.CreateAccountRequest;
import com.maria.service.api.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 8/10/2017.
 */
@RestController
@RequestMapping(value = "/api")
public class RegisterController {
    private AccountService accountService;
    private AccountConverter accountConverter = new AccountConverter();

    @Autowired
    public RegisterController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public AccountJsonResponse register(@RequestBody CreateAccountJsonRequest createAccountJsonRequest) {
        String email = createAccountJsonRequest.getEmail();
        String password = createAccountJsonRequest.getPassword();

        CreateAccountRequest createAccountRequest = new CreateAccountRequest()
                .setEmail(email)
                .setPassword(password);
        Account createdAccount = accountService.createAccount(createAccountRequest);
        return accountConverter.toAccountJsonResponse(createdAccount);
    }

}
