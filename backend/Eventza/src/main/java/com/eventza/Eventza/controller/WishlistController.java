package com.eventza.Eventza.controller;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Events.EventRepository;
import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.Repository.UserRepository;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import com.eventza.Eventza.model.WishlistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class WishlistController {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;


    @PostMapping("/addToWishlist")
    public String add(@RequestBody WishlistRequest request){
        String username = request.getUsername();
        String eventName = request.getEventName();
        if(username!=null && eventName!=null){
            try{
                User user = userService.getUserByUsername(username);
                EventModel event = eventService.getRequestedEvent(eventName);
                user.addWish(event);
                userRepository.save(user);
                eventRepository.save(event);

            }
            catch(Exception e){
                System.out.println("Error!! in getting user or event!");
            }
        }
        return null;
    }

    @GetMapping("/getWishlist")
    public ResponseEntity<?> getWishes(@RequestBody Map<String,String> username){
        try{
            System.out.println(username);
            User user = userService.getUserByUsername(username.get("username"));
            return ResponseEntity.ok(user.getWishlist());
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
