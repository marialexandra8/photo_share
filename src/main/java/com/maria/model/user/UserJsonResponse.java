package com.maria.model.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.maria.model.account.Gender;
import com.maria.serializer.FullDateSerializer;

import java.time.LocalDate;

/**
 * Created on 8/20/2017.
 */
public class UserJsonResponse {
    private int id;
    private String email;
    private String name;
    private LocalDate birthday;
    private Gender gender;
    private String logoUrl;

    public UserJsonResponse(User user) {
        this
                .setBirthday(user.getBirthday())
                .setEmail(user.getEmail())
                .setGender(user.getGender())
                .setId(user.getId())
                .setLogoUrl(user.getLogoPath())
                .setName(user.getName());
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public UserJsonResponse setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public int getId() {
        return id;
    }

    public UserJsonResponse setId(int id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserJsonResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserJsonResponse setName(String name) {
        this.name = name;
        return this;
    }

    @JsonSerialize(using = FullDateSerializer.class)
    public LocalDate getBirthday() {
        return birthday;
    }

    public UserJsonResponse setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public UserJsonResponse setGender(Gender gender) {
        this.gender = gender;
        return this;
    }
}
