package com.eventza.Eventza.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Wishlist {
    @Id
    private UUID wishId;
    private UUID userId;
    private UUID eventId;

    public Wishlist(){}

    public Wishlist(UUID userId,UUID eventId){
        this.wishId = UUID.randomUUID();
        this.userId = userId;
        this.eventId = eventId;
    }

    public UUID getWishId() {
        return wishId;
    }

    public void setWishId(UUID wishId) {
        this.wishId = wishId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }
}
