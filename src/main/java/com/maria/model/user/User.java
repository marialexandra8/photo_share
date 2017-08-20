package com.maria.model.user;

import com.maria.model.account.Gender;

import java.time.LocalDate;

/**
 * Created on 8/10/2017.
 */
public class User {
    private int id;
    private String email;
    private String name;
    private LocalDate birthday;
    private Gender gender;
    private String logoName;
    private String logoPath;

    public Gender getGender() {
        return gender;
    }

    public User setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getLogoName() {
        return logoName;
    }

    public User setLogoName(String logoName) {
        this.logoName = logoName;
        return this;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public User setLogoPath(String logoPath) {
        this.logoPath = logoPath;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public User setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }
}
