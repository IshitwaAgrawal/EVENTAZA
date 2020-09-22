package com.eventza.Eventza.controller;

import com.eventza.Eventza.DTO.UserDTO;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.LoginResponse;
import com.eventza.Eventza.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Registration {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/user/registration")
    public ResponseEntity<User> register(@RequestBody User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles());
        return ResponseEntity.ok(this.registerUserAccount(userDTO));
    }

    public User registerUserAccount(UserDTO userDTO){
        try{
            User registered = userService.registerNewUserAccount(userDTO);
            return registered;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
