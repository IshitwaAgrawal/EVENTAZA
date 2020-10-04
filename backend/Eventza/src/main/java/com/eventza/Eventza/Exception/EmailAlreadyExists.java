package com.eventza.Eventza.Exception;

public class EmailAlreadyExists extends Exception{
    public EmailAlreadyExists(String email){
        super(email+" already exixts.");
    }
    public EmailAlreadyExists(){
        super("email is already registered.");
    }
}
