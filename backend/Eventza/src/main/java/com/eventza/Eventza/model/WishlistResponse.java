package com.eventza.Eventza.model;

import com.eventza.Eventza.Events.EventModel;

import java.util.List;

public class WishlistResponse {
    private List<EventModel> wishlist;

    public List<EventModel> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<EventModel> wishlist) {
        this.wishlist = wishlist;
    }
}
