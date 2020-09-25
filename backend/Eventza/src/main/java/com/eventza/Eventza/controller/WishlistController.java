package com.eventza.Eventza.controller;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.Service.WishlistService;
import com.eventza.Eventza.model.User;
import com.eventza.Eventza.model.Wishlist;
import com.eventza.Eventza.model.WishlistRequest;
import com.eventza.Eventza.model.WishlistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishlistController {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    WishlistService wishlistService;

    @PostMapping("/addToWishlist")
    public String add(@RequestBody WishlistRequest request){
        String username = request.getUsername();
        String eventName = request.getEventName();
        if(username!=null && eventName!=null){
            try{
                User user = userService.getUserByUsername(username);
                EventModel event = eventService.getRequestedEvent(eventName);
                Wishlist w = new Wishlist(user.getId(),event.getId());
                wishlistService.addNew(w);
            }
            catch(Exception e){
                System.out.println("Error!! in getting user or event!");
            }
        }
        return null;
    }
}
