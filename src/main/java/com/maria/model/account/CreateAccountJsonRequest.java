package com.maria.model.account;

import java.time.LocalDate;

/**
 * Created on 8/10/2017.
 */
public class CreateAccountJsonRequest {
    private String email;
    private String password;
    private String name;
    private LocalDate birthday;
    private String gender;

    public String getName() {
        return name;
    }

    public CreateAccountJsonRequest setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public CreateAccountJsonRequest setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public CreateAccountJsonRequest setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateAccountJsonRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateAccountJsonRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
