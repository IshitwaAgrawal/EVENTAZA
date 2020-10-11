package com.eventza.Eventza.controller;

import com.eventza.Eventza.Repository.UserRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class NewsLetterController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NewsLetterService letterService;

    @PostMapping("/addNewsService")
    public ResponseEntity<String> addNewsLetterService(@RequestBody Map<String,String> user)throws Exception{
        try {
            User u = userService.getUserByUsername(user.get("username"));
            if(!u.isVerified()) {
                return new ResponseEntity<String>("Please verify first to subscribe the Newsletter.",HttpStatus.NOT_ACCEPTABLE);
            }
            if (u.isNewsletter_service()) {
                return new ResponseEntity<String>("You have already subscribed for newsletter service.", HttpStatus.OK);
            }
            userRepository.updateNewsletterService(u.getId(), true);
            letterService.sendNewsLetter(u,true);
            return new ResponseEntity<String>("Successfully added newsletter service to user "+u.getUsername(),HttpStatus.OK);
        }
        catch (UsernameNotFoundException u){
            return new ResponseEntity<String>(u.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeNewsService")
    public ResponseEntity<String> removeNewsLetterService(@RequestBody Map<String,String> user){
        try {
            User u = userService.getUserByUsername(user.get("username"));
            userRepository.updateNewsletterService(u.getId(), false);
            letterService.sendNewsLetter(u,false);
            return new ResponseEntity<String>("Successfully removed newsletter service from user "+u.getUsername(),HttpStatus.OK);
        }
        catch (UsernameNotFoundException u){
            return new ResponseEntity<String>(u.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
