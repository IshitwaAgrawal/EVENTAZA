package com.eventza.Eventza.controller;

import com.eventza.Eventza.Service.MailService;
import com.eventza.Eventza.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

  @Autowired
  MailService mailService;

  @PostMapping("/contact")
  public ResponseEntity<String> sendContactMail(@RequestParam("name") String name,
      @RequestParam("email") String email, @RequestParam("message") String message) {

    try{
      mailService.sentContactMail(name, email, message );
    }
    catch (Exception e)
    {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<String>("Mail sent", HttpStatus.OK);
  }


}
