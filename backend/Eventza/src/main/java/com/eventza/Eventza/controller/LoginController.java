package com.eventza.Eventza.controller;

import com.eventza.Eventza.config.JwtUtil;
import com.eventza.Eventza.model.LoginRequest;
import com.eventza.Eventza.model.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createLoginToken(@RequestBody LoginRequest request)throws Exception{
//        System.out.println(request);
//        try{
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
//            );
//        }
//        catch(Exception e){
//            System.out.println(e.getMessage());
//            throw new Exception("Incorrect Username or password.");
//        }

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
