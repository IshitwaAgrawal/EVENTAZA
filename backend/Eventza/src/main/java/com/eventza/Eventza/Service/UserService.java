package com.eventza.Eventza.Service;

import com.eventza.Eventza.DTO.UserDTO;
import com.eventza.Eventza.Exception.EmailAlreadyExists;
import com.eventza.Eventza.Exception.PasswordException;
import com.eventza.Eventza.Exception.UserAlreadyExists;
import com.eventza.Eventza.Repository.UserRepository;
import com.eventza.Eventza.model.User;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
public class UserService implements UserServiceI{
    @Autowired
    private UserRepository repo;

    @Autowired
    private MailService mailService;

    @Transactional
    @Override
    public User registerNewUserAccount(UserDTO userDTO) throws Exception {
        if(userExists(userDTO.getUsername())){
            throw new UserAlreadyExists(userDTO.getUsername());
        }

        if(emailExists(userDTO.getEmail())){
            throw new EmailAlreadyExists(userDTO.getEmail());
        }

        if(userDTO.getPassword().length()<6){
            throw new PasswordException("Short password.Please add password with length > 10");
        }

        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(regex);
        String email = userDTO.getEmail();

        Matcher m = pattern.matcher(email);

        if(!m.matches()){
            throw new PatternSyntaxException("not a valid address.",regex,0);
        }

        User user = new User();
        String k = RandomString.make(64);
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setId(UUID.randomUUID());
        user.setRoles(userDTO.getRoles());
        user.setEnabled(true);
        user.setVerified(false);
        user.setEmail(userDTO.getEmail());
        user.setVerificationToken(k);
        user.setCreated_events(0);
        user.setRegister_in_events(0);
        mailService.sendVerificationEmail(user);
        return repo.save(user);
    }

    public User getUserByEmail(String email){
        return repo.getUserByEmail(email);
    }

    public boolean emailExists(String email){
        return this.getUserByEmail(email)!=null;
    }

    public User getUserByVerificationToken(String code){
        return repo.getUserByVerificationToken(code);
    }

    public boolean userExists(String username){
        return repo.getUserByUsername(username) != null;
    }


    public void increaseCreatedEvent(User user){
        int c = user.getCreated_events();
        user.setCreated_events(c+1);
        repo.save(user);
    }

    public void deleteCreatedEvent(User user){
        int c = user.getCreated_events();
        if(c>0){
            user.setCreated_events(c-1);
            repo.save(user);
        }
    }

    public void increaseRegisteredEvents(User user){
        int c = user.getRegister_in_events();
        user.setRegister_in_events(c+1);
        repo.save(user);
    }

    public void decreaseRegisteredEvent(User user){
        int c = user.getRegister_in_events();
        if(c>0){
            user.setRegister_in_events(c-1);
            repo.save(user);
        }
    }

    public User getUserByUsername(String username){
        try{
            return repo.getUserByUsername(username);
        }
        catch (Exception e){
            throw new UsernameNotFoundException("Username not found!!");
        }
    }

}
