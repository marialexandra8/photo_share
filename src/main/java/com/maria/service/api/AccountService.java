package com.maria.service.api;

import com.maria.model.account.Account;
import com.maria.model.authentication.AuthenticationRequest;
import com.maria.model.authentication.AuthenticationResponse;
import com.maria.service.account.model.CreateAccountRequest;

/**
 * Created on 8/10/2017.
 */
public interface AccountService {
    Account createAccount(CreateAccountRequest createAccountRequest);

}
