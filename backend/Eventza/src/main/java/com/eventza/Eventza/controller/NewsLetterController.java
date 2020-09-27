package com.eventza.Eventza.controller;

import com.eventza.Eventza.Service.NewsLetterService;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NewsLetterController {

    @Autowired
    UserService userService;

    @Autowired
    NewsLetterService letterService;

    @PostMapping
    public ResponseEntity<String> addNewsLetterService(@RequestBody Map<String,String> user){
        try {
            User u = userService.getUserByUsername(user.get("username"));
            u.setNewsletter_service(true);
            letterService.sendNewsLetter(u,true);
            return new ResponseEntity<String>("Successfully added newsletter service to user "+u.getUsername(),HttpStatus.OK);
        }
        catch (UsernameNotFoundException u){
            return new ResponseEntity<String>(u.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> removeNewsLetterService(@RequestBody Map<String,String> user){
        try {
            User u = userService.getUserByUsername(user.get("username"));
            u.setNewsletter_service(false);
            letterService.sendNewsLetter(u,false);
            return new ResponseEntity<String>("Successfully removed newsletter service from user "+u.getUsername(),HttpStatus.OK);
        }
        catch (UsernameNotFoundException u){
            return new ResponseEntity<String>(u.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
