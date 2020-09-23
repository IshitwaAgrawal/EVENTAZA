package com.eventza.Eventza.Exception;

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(String username){
        super("User with USERNAME -> "+username+"already Exists.");
    }
    public UserAlreadyExists(){
        super("User already exists.");
    }
}
