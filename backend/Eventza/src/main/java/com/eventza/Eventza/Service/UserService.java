package com.eventza.Eventza.Service;

import com.eventza.Eventza.DTO.UserDTO;
import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Exception.EmailAlreadyExists;
import com.eventza.Eventza.Exception.PasswordException;
import com.eventza.Eventza.Exception.UserAlreadyExists;
import com.eventza.Eventza.Repository.UserRepository;
import com.eventza.Eventza.model.User;
import com.eventza.Eventza.model.UserSignUp;
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
    private VerificationMailService mailService;

    public List<User> getAllUsers(){
        return (List<User>)repo.findAll();
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserSignUp userSignUp) throws Exception {
        if(userExists(userSignUp.getUsername())){
            throw new UserAlreadyExists(userSignUp.getUsername());
        }

        if(emailExists(userSignUp.getEmail())){
            throw new EmailAlreadyExists(userSignUp.getEmail());
        }

        if(userSignUp.getPassword().length()<6){
            throw new PasswordException("Short password.Please add password with length > 10");
        }

        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(regex);
        String email = userSignUp.getEmail();

        Matcher m = pattern.matcher(email);

        if(!m.matches()){
            throw new PatternSyntaxException("not a valid address.",regex,0);
        }

        User new_user = new User();

        String k = RandomString.make(64);

        new_user.setName(userSignUp.getName());
        new_user.setUsername(userSignUp.getUsername());
        new_user.setPassword(new BCryptPasswordEncoder().encode(userSignUp.getPassword()));
        new_user.setId(UUID.randomUUID());
        new_user.setRoles(userSignUp.getRoles());
        new_user.setEnabled(true);
        new_user.setVerified(false);
        new_user.setEmail(userSignUp.getEmail());
        new_user.setVerificationToken(k);
        new_user.setCreated_events(0);
        new_user.setRegister_in_events(0);
        new_user.setNewsletter_service(false);
        mailService.sendVerificationEmail(new_user);
        repo.save(new_user);

        return new_user;
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
        User u = repo.getUserByUsername(username);
        if(u==null)
            throw new UsernameNotFoundException("Username not found!!");
        else
            return repo.getUserByUsername(username);
    }

    public void addHostedEvent(User user, EventModel eventModel){
        user.addHostedEvents(eventModel);
        this.updateUser(user);
    }

    public void registerEvent(User user,EventModel eventModel){
        user.registerEvent(eventModel);
        this.updateUser(user);
    }

    public boolean deleteHostedEvent(User user,EventModel eventModel){
        boolean status = user.deleteHostedEvent(eventModel);
        repo.save(user);
        return status;
    }

    public boolean deleteRegisteredEvent(User user,EventModel eventModel){
        boolean status = user.unregisterEvent(eventModel);
        repo.save(user);
        return status;
    }

    public void updateUser(User user){
        repo.save(user);
    }

}
