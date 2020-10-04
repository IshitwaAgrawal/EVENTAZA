package com.eventza.Eventza.model;

import com.eventza.Eventza.Events.EventModel;

import java.util.List;
import java.util.UUID;

public class ResponseUser {
    private UUID id;
    private String username;
    private String name;
    private String email;
    private String roles;
    private boolean newsletter_service;
    private List<EventModel> registeredEvents;
    private List<EventModel> hostedEvents;
    private List<EventModel> wishlist;

    public ResponseUser(UUID id,String username,String name,String email,String roles,boolean newsletter_service,List<EventModel> registeredEvents,List<EventModel> hostedEvents,List<EventModel> wishlist){
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.newsletter_service = newsletter_service;
        this.registeredEvents = registeredEvents;
        this.hostedEvents = hostedEvents;
        this.wishlist = wishlist;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isNewsletter_service() {
        return newsletter_service;
    }

    public void setNewsletter_service(boolean newsletter_service) {
        this.newsletter_service = newsletter_service;
    }

    public List<EventModel> getRegisteredEvents() {
        return registeredEvents;
    }

    public void setRegisteredEvents(List<EventModel> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }

    public List<EventModel> getHostedEvents() {
        return hostedEvents;
    }

    public void setHostedEvents(List<EventModel> hostedEvents) {
        this.hostedEvents = hostedEvents;
    }

    public List<EventModel> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<EventModel> wishlist) {
        this.wishlist = wishlist;
    }
}
