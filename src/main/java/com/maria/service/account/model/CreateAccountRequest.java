package com.maria.service.account.model;

import com.maria.model.account.Gender;

import java.time.LocalDate;

/**
 * Created on 8/10/2017.
 */
public class CreateAccountRequest {
    private String email;
    private String password;
    private String name;
    private LocalDate birthday;
    private Gender gender;

    public String getName() {
        return name;
    }

    public CreateAccountRequest setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public CreateAccountRequest setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public CreateAccountRequest setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateAccountRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateAccountRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
