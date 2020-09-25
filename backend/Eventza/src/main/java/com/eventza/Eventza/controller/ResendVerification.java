package com.eventza.Eventza.controller;

import com.eventza.Eventza.Service.MailService;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.LoginRequest;
import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResendVerification {

    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;

    @PostMapping("/resendVerification")
    public String resend(@RequestBody LoginRequest loginRequest){
        try {
            String username = loginRequest.getUsername();
            if(username == null){
                return "USERNAME OR PASSWORD NOT VALID!!!";
            }
            User user = userService.getUserByUsername(username);
            mailService.sendVerificationEmail(user);
            return "Verification mail sent successfully!!";
        }
        catch (UsernameNotFoundException e){
            return "Username not found!";
        }
    }
}
