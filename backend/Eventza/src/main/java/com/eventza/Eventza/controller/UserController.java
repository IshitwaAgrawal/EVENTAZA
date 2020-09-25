package com.eventza.Eventza.controller;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.Service.WishlistService;
import com.eventza.Eventza.model.User;
import com.eventza.Eventza.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    WishlistService wishlistService;

    @GetMapping("/getWishlist")
    public ResponseEntity<?> getAll(@RequestBody Map<String,String> username){
        User user = userService.getUserByUsername(username.get("username"));
        System.out.println(username);
        if(user!=null){
            List<Wishlist> wishesAbs = wishlistService.getWishesByUsername(user.getId());
            List<EventModel> wishes = new ArrayList<>();
            for(Wishlist w:wishesAbs){
                EventModel e = eventService.getEventById(w.getEventId());
                wishes.add(e);
            }
            return ResponseEntity.ok(wishes);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
