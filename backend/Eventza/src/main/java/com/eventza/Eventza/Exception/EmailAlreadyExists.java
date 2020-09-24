package com.eventza.Eventza.Exception;

public class EmailAlreadyExists extends Exception{
    public EmailAlreadyExists(String email){
        super("A user with the email is already registered : "+email);
    }
    public EmailAlreadyExists(){
        super("A user with the email is already registered.");
    }
}
