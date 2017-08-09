package com.maria.model.account;

/**
 * Created   on 8/9/2017.
 */
public class Account {
    private int id;
    private String email;
    private String password;
    //TODO add authorities


    public int getId() {
        return id;
    }

    public Account setId(int id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }
}
