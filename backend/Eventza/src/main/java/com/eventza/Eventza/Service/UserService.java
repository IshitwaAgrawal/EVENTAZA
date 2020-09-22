package com.eventza.Eventza.Service;

import com.eventza.Eventza.DTO.UserDTO;
import com.eventza.Eventza.Repository.UserRepository;
import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService implements UserServiceI{
    @Autowired
    private UserRepository repo;

    @Transactional
    @Override
    public User registerNewUserAccount(UserDTO userDTO) throws Exception {
        if(userExists(userDTO.getUsername())){
            throw new Exception("There is an account with that username : "+userDTO.getUsername());
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setId(UUID.randomUUID());
        user.setRoles(userDTO.getRoles());
        user.setEnabled(true);
        user.setVerified(false);
        user.setEmail(userDTO.getEmail());
        return repo.save(user);
    }

    private boolean userExists(String username){
        return repo.getUserByUsername(username) != null;
    }
}
