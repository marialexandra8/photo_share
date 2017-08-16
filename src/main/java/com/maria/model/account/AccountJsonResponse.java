package com.maria.model.account;

/**
 * Created on 8/10/2017.
 */
public class AccountJsonResponse {
    private Integer id;
    private String email;

    public AccountJsonResponse() {
    }

    public AccountJsonResponse(Account account) {
        this
                .setId(account.getId())
                .setEmail(account.getEmail());
    }

    public Integer getId() {
        return id;
    }

    public AccountJsonResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AccountJsonResponse setEmail(String email) {
        this.email = email;
        return this;
    }
}
