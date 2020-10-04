package com.eventza.Eventza.model;

public class LoginResponse {

    private final String jwt;
    private ResponseUser user;

    public LoginResponse(String jwt,ResponseUser user){
        this.user = user;
        this.jwt = jwt;
    }

    public String getJwt(){
        return jwt;
    }

    public ResponseUser getUser(){
        return user;
    }
}
