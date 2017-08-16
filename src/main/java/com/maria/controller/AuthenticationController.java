package com.maria.controller;

import com.maria.converter.AccountConverter;
import com.maria.model.account.Account;
import com.maria.model.account.AccountJsonResponse;
import com.maria.model.authentication.AuthenticationJsonRequest;
import com.maria.model.authentication.AuthenticationJsonResponse;
import com.maria.model.authentication.AuthenticationRequest;
import com.maria.model.authentication.AuthenticationResponse;
import com.maria.model.token.AuthenticationToken;
import com.maria.service.api.AccountService;
import com.maria.service.api.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created   on 8/8/2017.
 */
@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {
    private AccountService accountService;
    private AuthenticationService authenticationService;
    private AccountConverter accountConverter = new AccountConverter();

    @Autowired
    public AuthenticationController(AccountService accountService, AuthenticationService authenticationService) {
        this.accountService = accountService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public AuthenticationJsonResponse authenticateAccount(@RequestBody AuthenticationJsonRequest authenticationJsonRequest) {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest()
                .setEmail(authenticationJsonRequest.getEmail())
                .setPassword(authenticationJsonRequest.getPassword());
        AuthenticationResponse authenticationResponse = authenticationService.authenticateAccount(authenticationRequest);
        return new AuthenticationJsonResponse(authenticationResponse);
    }

}
