package com.eventza.Eventza;

import com.eventza.Eventza.Repository.UserRepository;
import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class home {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @PostMapping("/getUser")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/403")
    public String denied(){
        return "<h1>ACCESS DENIED!</h1>";
    }
}