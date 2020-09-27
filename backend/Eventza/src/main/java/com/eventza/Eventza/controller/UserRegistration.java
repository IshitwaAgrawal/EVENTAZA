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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.regex.PatternSyntaxException;

@RestController
public class UserRegistration {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/user/registration")
    public ResponseEntity<?> register(@RequestBody User user)throws Exception{
        System.out.println(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles());
        return this.registerUserAccount(userDTO);
    }

    public ResponseEntity<?> registerUserAccount(UserDTO userDTO){
        try{
            User registered = userService.registerNewUserAccount(userDTO);
            return ResponseEntity.ok(registered);
        }
        catch (PasswordException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.FOUND);
//            return new ResponseEntity<PasswordException>(e,HttpStatus.FOUND);
//            return ResponseEntity.notFound().build();
//            return ResponseEntity.ok("Password must be of length greater than or equals to 10.");
        }
        catch(PatternSyntaxException p){
            return new ResponseEntity<String>("The mail is invalid.Please write correct email address.",HttpStatus.NOT_FOUND);
//            return ResponseEntity.ok("The mail is invalid.Please check again!");
        }
        catch(UserAlreadyExists u){
            return new ResponseEntity<String>(u.getMessage(),HttpStatus.FOUND);
//            return ResponseEntity.notFound().build();
//            return ResponseEntity.ok("User already exists");
        }
        catch(EmailAlreadyExists e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.FOUND);
//            return new ResponseEntity<EmailAlreadyExists>(e,HttpStatus.FOUND);
//            return ResponseEntity.notFound().build();
//            return ResponseEntity.ok("Email is already registered with us.Please use another email.");
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
//        return null;
    }
}
