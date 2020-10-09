package com.eventza.Eventza.controller;

import com.eventza.Eventza.Service.MailService;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import java.lang.ref.ReferenceQueue;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgetPassword {

  @Autowired
  private UserService userService;

  @Autowired
  private MailService mailService;

  @RequestMapping(method = RequestMethod.POST, path = "/forgotPassword")
  public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> username){
    User user = userService.getUserByUsername(username.get("username"));

    String subject = "Reset password";
    String sender = "Eventaza";
    String mailContent = "<p>Dear " + user.getName() + "</p>";
    String site = "http://localhost:8000/resetPassword";
    mailContent += "<p>Please click the link below to reset your password</p>";
    mailContent += "<a href='"+site+"'>RESET PASSWORD</a><br>";
    mailService.sendMail(user.getEmail(), subject, sender, mailContent);

    return new ResponseEntity<>("Check your mail to update your password", HttpStatus.OK);
  }
}
