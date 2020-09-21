package com.eventza.Eventza.controller;

import com.eventza.Eventza.DTO.UserDTO;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Registration {

    @Autowired
    UserService userService;

    @GetMapping("/user/registration")
    public User register(@RequestBody User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles());
        return this.registerUserAccount(userDTO);
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
