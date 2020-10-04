package com.eventza.Eventza.controller;

import com.eventza.Eventza.Service.MailService;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.Service.VerificationMailService;
import com.eventza.Eventza.config.JwtUtil;
import com.eventza.Eventza.model.LoginRequest;
import com.eventza.Eventza.model.LoginResponse;
import com.eventza.Eventza.model.ResponseUser;
import com.eventza.Eventza.model.User;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private VerificationMailService mailService;

    @PostMapping("/login")
    public ResponseEntity<?> createLoginToken(@RequestBody LoginRequest request)throws Exception{
        System.out.println(request);
        try {
            User user = userService.getUserByUsername(request.getUsername());
            System.out.println(user.isVerified());
            if(!user.isVerified()){
                String k = RandomString.make(64);
                user.setVerificationToken(k);
                userService.updateUser(user);
                mailService.sendVerificationEmail(user);
                userService.updateUser(user);
                return new ResponseEntity<String>("User not verified. Please check EMAIL.",HttpStatus.NOT_ACCEPTABLE);
            }
        }
        catch (UsernameNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<String>("Some error occured!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("Username or password is wrong...", HttpStatus.NOT_FOUND);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getUsername());

        if(userDetails.isEnabled()){
            User user = userService.getUserByUsername(request.getUsername());
            ResponseUser r_user = new ResponseUser(user.getId(),user.getUsername(),user.getName(),user.getEmail(),user.getRoles(),user.isNewsletter_service(),user.getRegisteredEvents(),user.getHostedEvents(),user.getWishlist());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            return new ResponseEntity<LoginResponse>(new LoginResponse(jwt,r_user),HttpStatus.OK);
//            return ResponseEntity.ok(new LoginResponse(jwt,user));
        }
        else{
            return new ResponseEntity<String>("User is disabled by ADMIN.",HttpStatus.BAD_REQUEST);
//            return ResponseEntity.ok("Not Enabled from Admin!");
        }
    }
}
