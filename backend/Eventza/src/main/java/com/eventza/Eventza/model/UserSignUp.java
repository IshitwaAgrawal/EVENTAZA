package com.eventza.Eventza.model;

public class UserSignUp {
    private String name;
    private String username;
    private String email;
    private String password;
    private String roles;

    public UserSignUp(String name,String username,String password,String email,String roles){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles){this.roles = roles;}

}
