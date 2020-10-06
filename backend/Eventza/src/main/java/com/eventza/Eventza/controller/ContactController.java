package com.eventza.Eventza.controller;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Events.EventRepository;
import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.Service.MailService;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

  @Autowired
  MailService mailService;

  @Autowired
  EventService eventService;

  @Autowired
  UserService userService;

  @PostMapping("/{username}/contact")
  public ResponseEntity<String> sendContactMail(@PathVariable String username,
      @RequestParam("name") String name, @RequestParam("role") String role, @RequestParam("message") String message) {

    try {
      User user = userService.getUserByUsername(username);
      mailService.sendContactMail(name, user.getEmail(), role, message);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<String>("Mail sent", HttpStatus.OK);
  }

  @PostMapping("/{eventName}/{username}/contactOrganizer")
  public ResponseEntity<String> sendMailToOrganizer(@PathVariable String eventName,
      @PathVariable String username,
      @RequestParam("name") String name,
      @RequestParam("message") String message) {

    try {
      User user = userService.getUserByUsername(username);
      EventModel event = eventService.getRequestedEvent(eventName);
      String organiserMail = event.getOrganiserEmail();
      String subject = "Client query";
      String mailContent = "<p> Client-Email: " + user.getEmail() + "<p>";
      mailContent += "<p>" + message + "</p>";
      mailService.sendMail(organiserMail, subject, name, mailContent);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<String>("Mail sent", HttpStatus.OK);
  }


}
