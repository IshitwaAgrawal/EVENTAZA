package com.eventza.Eventza.controller;

import com.eventza.Eventza.Repository.UserRepository;
import com.eventza.Eventza.Service.MailService;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import com.eventza.Eventza.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResetPasswordController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MailService mailService;

  @RequestMapping(method = RequestMethod.POST, path = "/resetPassword")
  public ResponseEntity<?> resetPassword(@RequestBody UserDetails userDetails) throws Exception {

    User user = userService.getUserByUsername(userDetails.getUsername());
    if (user == null) {
     return new ResponseEntity<String>("Username doesn't exist", HttpStatus.NOT_FOUND);
  }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    boolean check = encoder.matches(userDetails.getOldPassword(), user.getPassword());

    if (!check) {
      return new ResponseEntity<String>("Old Password is incorrect!", HttpStatus.BAD_REQUEST);
    }

    String newPassword = userDetails.getNewPassword();
    String confirmPassword = userDetails.getConfirmPassword();

    if (!newPassword.equals(confirmPassword)) {
      return new ResponseEntity<String>("Your new password and confirm password doesn't match!",
          HttpStatus.BAD_REQUEST);
    }

    userRepository.updatePassword(user.getId(), BCrypt.hashpw(newPassword, BCrypt.gensalt()));

    String mailContent = "<p>Password updated successfully</p>";
    mailService
        .sendMail(user.getEmail(), "Password updated", "Eventaza", mailContent);
    return new ResponseEntity<String>("Password updated", HttpStatus.OK);

  }
}
