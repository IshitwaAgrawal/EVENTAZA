package com.eventza.Eventza.controller;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Events.EventRepository;
import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.Exception.EventNotFoundException;
import com.eventza.Eventza.Repository.UserRepository;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import com.eventza.Eventza.model.WishlistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
                System.out.println("Event added to wishlist"
                    + "");
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

    @DeleteMapping("/deleteFromWishlist")
    public ResponseEntity<?> deleteWish(@RequestBody Map<String,String> event){
        String eventname = event.get("eventName");
        String username = event.get("username");
        try{
            EventModel e = eventService.getRequestedEvent(eventname);
            User u = userService.getUserByUsername(username);
            u.deleteWish(e);
            return new ResponseEntity<String>("Event deleted",HttpStatus.FOUND);
        }
        catch (EventNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch(UsernameNotFoundException u){
            return new ResponseEntity<String>(u.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<String>("Some error happened.Please try again!",HttpStatus.CONFLICT);
        }
    }
}
