package com.eventza.Eventza.controller;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Events.EventRepository;
import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.Service.MailService;
import com.eventza.Eventza.Service.UserService;
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

  @PostMapping("/contact")
  public ResponseEntity<String> sendContactMail(@RequestParam("name") String name,
      @RequestParam("email") String email, @RequestParam("message") String message) {

    try{
      mailService.sendContactMail(name, email, message );
    }
    catch (Exception e)
    {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<String>("Mail sent", HttpStatus.OK);
  }

  @PostMapping("/{eventName}/contactOrganizer")
  public ResponseEntity<String> sendMailToOrganizer(@PathVariable String eventName, @RequestParam("name") String name,
      @RequestParam("message") String message) {

    try{
      EventModel event = eventService.getRequestedEvent(eventName);
      String organiserMail = event.getOrganiserEmail();
      String subject = "Client query";
      String mailContent = "<p>" + message + "</p>";
      mailService.sendMail(organiserMail, subject, name, mailContent);
    }
    catch (Exception e){
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<String>("Mail sent", HttpStatus.OK);
  }


}
