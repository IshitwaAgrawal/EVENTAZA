package com.eventza.Eventza.controller;

import com.eventza.Eventza.Service.MailService;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.Service.VerificationMailService;
import com.eventza.Eventza.config.JwtUtil;
import com.eventza.Eventza.model.LoginRequest;
import com.eventza.Eventza.model.LoginResponse;
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
                mailService.sendVerificationEmail(user);
                return new ResponseEntity<String>("User not verified!!",HttpStatus.NOT_ACCEPTABLE);
            }
        }
        catch (Exception e){
            return new ResponseEntity<String>("Error",HttpStatus.NOT_FOUND);
        }
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("Username or password is wrong...", HttpStatus.NOT_FOUND);
//            return ResponseEntity.status(404).build();
//            throw new Exception("Incorrect Username or password.");
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getUsername());

        if(userDetails.isEnabled()){
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(jwt));
        }
        else{
            return ResponseEntity.ok("Not Verified!");
        }
    }
}
