package com.eventza.Eventza.controller;

import com.eventza.Eventza.DTO.UserDTO;
import com.eventza.Eventza.Exception.EmailAlreadyExists;
import com.eventza.Eventza.Exception.PasswordException;
import com.eventza.Eventza.Exception.UserAlreadyExists;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.LoginResponse;
import com.eventza.Eventza.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.regex.PatternSyntaxException;

@RestController
public class Registration {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/user/registration")
    public ResponseEntity<?> register(@RequestBody User user)throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles());
        return ResponseEntity.ok(this.registerUserAccount(userDTO));
    }

    public ResponseEntity<?> registerUserAccount(UserDTO userDTO){
        try{
            User registered = userService.registerNewUserAccount(userDTO);
            return ResponseEntity.ok(registered);
        }
        catch (PasswordException e){
            return ResponseEntity.ok("Password must be of length greater than or equals to 10.");
        }
        catch(PatternSyntaxException p){
            return ResponseEntity.ok("The mail is invalid.Please check again!");
        }
        catch(UserAlreadyExists u){
            return ResponseEntity.ok("User already exists");
        }
        catch(EmailAlreadyExists e){
            return ResponseEntity.ok("Email is already registered with us.Please use another email.");
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
