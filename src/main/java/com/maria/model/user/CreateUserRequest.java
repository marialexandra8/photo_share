package com.maria.model.user;

import com.maria.model.account.Gender;

import java.time.LocalDate;

/**
 * Created on 8/10/2017.
 */
public class CreateUserRequest {
    private String email;
    private String name;
    private LocalDate birthday;
    private Gender gender;
    private int accountId;

    public int getAccountId() {
        return accountId;
    }

    public CreateUserRequest setAccountId(int accountId) {
        this.accountId = accountId;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public CreateUserRequest setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateUserRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateUserRequest setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public CreateUserRequest setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }
}
