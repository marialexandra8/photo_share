package com.maria.converter;

import com.maria.model.account.Account;
import com.maria.model.account.AccountJsonResponse;

/**
 * Created on 8/10/2017.
 */
public class AccountConverter {
    public AccountJsonResponse toAccountJsonResponse(Account account) {
        return new AccountJsonResponse()
                .setId(account.getId())
                .setEmail(account.getEmail());
    }
}
