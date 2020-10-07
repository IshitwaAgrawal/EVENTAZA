package com.eventza.Eventza.controller;

import com.eventza.Eventza.DTO.UserDTO;
import com.eventza.Eventza.Exception.EmailAlreadyExists;
import com.eventza.Eventza.Exception.PasswordException;
import com.eventza.Eventza.Exception.UserAlreadyExists;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.LoginResponse;
import com.eventza.Eventza.model.ResponseUser;
import com.eventza.Eventza.model.User;
import com.eventza.Eventza.model.UserSignUp;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

@RestController
public class UserRegistration {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/user/registration")
    public ResponseEntity<?> register(@RequestBody Map<String,String> user){
        UserSignUp userSignUp = new UserSignUp(user.get("name"),user.get("username"),user.get("password"),user.get("email"),user.get("roles"));
        return this.registerUserAccount(userSignUp);
    }

    public ResponseEntity<?> registerUserAccount(UserSignUp user){
        List<String> err = new ArrayList<>();
        if(user.getName()==null){
            err.add("Please enter the name.");
//            return new ResponseEntity<String>("Please enter the name.",HttpStatus.NOT_FOUND);
        }
        if(user.getUsername()==null){
            err.add("Please enter the username.");
//            return new ResponseEntity<String>("Please enter the username.",HttpStatus.NOT_FOUND);
        }
        if(user.getEmail()==null){
            err.add("Please enter the email.");
//            return new ResponseEntity<String>("Please enter the email.",HttpStatus.NOT_FOUND);
        }
        if(user.getPassword()==null){
            err.add("Please enter the password.");
//            return new ResponseEntity<String>("Please enter the password.",HttpStatus.NOT_FOUND);
        }
        if(!err.isEmpty()){
            return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
        }
        if(user.getRoles()==null){
            user.setRoles("USER");
        }
        try{
            User registered = userService.registerNewUserAccount(user);
            ResponseUser r_user = new ResponseUser(registered.getId(),registered.getUsername(),registered.getName(),registered.getEmail(),registered.getRoles(),registered.isNewsletter_service(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
            return new ResponseEntity<>(r_user,HttpStatus.OK);
        }
        catch (PasswordException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FOUND);
        }
        catch(PatternSyntaxException p){
            return new ResponseEntity<>("The mail is invalid.Please write correct email address.",HttpStatus.NOT_FOUND);
        }
        catch(UserAlreadyExists u){
            return new ResponseEntity<>(u.getMessage(),HttpStatus.FOUND);
        }
        catch(EmailAlreadyExists e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FOUND);
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }
}
