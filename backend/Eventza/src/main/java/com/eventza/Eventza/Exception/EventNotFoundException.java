package com.eventza.Eventza.Exception;

public class EventNotFoundException extends Exception{

    public EventNotFoundException(){
        super("The event queried is not found in the database");
    }

}
