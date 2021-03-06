package com.eventza.Eventza.Service;

import com.eventza.Eventza.Repository.UserRepository;
import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        String roles[] = user.getRoles().split(",");
        List<SimpleGrantedAuthority> rolesL = new ArrayList<>();
        for(String r:roles){
            rolesL.add(new SimpleGrantedAuthority(r));
        }

       if(user == null){
           throw new UsernameNotFoundException("Could not find user");
       }

       return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.isVerified(),true,true,true,rolesL);

    }
}
