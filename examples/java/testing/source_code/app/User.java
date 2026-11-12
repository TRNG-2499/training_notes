package com.revature.junit.app;

// A simple model representing a user record
public class User {

    private final String username;
    private final String password;
    private final boolean accountLocked;

    public User(String username, String password, boolean accountLocked) {
        this.username = username;
        this.password = password;
        this.accountLocked = accountLocked;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isAccountLocked() { return accountLocked; }
}