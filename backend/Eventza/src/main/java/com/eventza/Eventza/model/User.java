package com.eventza.Eventza.model;

import com.eventza.Eventza.Events.EventModel;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    private UUID id;
    private String name;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private String roles;
    private boolean verified;
    private String verificationToken;
    private int created_events;
    private int register_in_events;
    private boolean newsletter_service;

    @OneToMany(cascade = CascadeType.ALL)
    private List<EventModel> hostedEvents;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<EventModel> registeredEvents;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<EventModel> wishlist;

    public User(){
        this.registeredEvents = new ArrayList<>();
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public int getCreated_events() {
        return created_events;
    }

    public void setCreated_events(int created_events) {
        this.created_events = created_events;
    }

    public int getRegister_in_events() {
        return register_in_events;
    }

    public void setRegister_in_events(int register_in_events) {
        this.register_in_events = register_in_events;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<EventModel> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<EventModel> wishlist) {
        this.wishlist = wishlist;
    }

    public List<EventModel> getHostedEvents() {
        return hostedEvents;
    }

    public void setHostedEvents(List<EventModel> hostedEvents) {
        this.hostedEvents = hostedEvents;
    }

    public List<EventModel> getRegisteredEvents() {
        return registeredEvents;
    }

    public void setRegisteredEvents(List<EventModel> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }

    public void addWish(EventModel event){
        this.wishlist.add(event);
    }

    public boolean deleteWish(EventModel eventModel){
        return this.wishlist.remove(eventModel);
    }

    public boolean isNewsletter_service() {
        return newsletter_service;
    }

    public void setNewsletter_service(boolean newsletter_service) {
        this.newsletter_service = newsletter_service;
    }

    public void addHostedEvents(EventModel eventModel){
        this.created_events++;
        this.hostedEvents.add(eventModel);
    }

    public void registerEvent(EventModel eventModel){
        this.register_in_events++;
        this.registeredEvents.add(eventModel);
    }

    public boolean deleteHostedEvent(EventModel eventModel){
        this.created_events--;
        return this.hostedEvents.remove(eventModel);
    }

    public boolean unregisterEvent(EventModel eventModel){
        this.register_in_events--;
        return this.registeredEvents.remove(eventModel);
    }
}
