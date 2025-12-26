package com.fop.model;

public class User {
    private final String email;
    private String displayName;
    private String password;

    public User(String email, String displayName, String password) {
        this.email = email;
        this.displayName = displayName;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getDisplayName() { return displayName; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return "User{email='" + email + "', displayName='" + displayName + "'}";
    }
}
