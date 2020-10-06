package com.eventza.Eventza.Service;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsletterMail {


  @Autowired
  MailService mailService;

  public void sendNewsletterMail(User user, EventModel event) {
    String subject = "New event added!";

    String senderName = "EVENTAZA APP";

    String userEmail = user.getEmail();
    String mailContent = "<p>Dear " + user.getName() + ", </p>";
    mailContent += "<p>A new event '" + event.getEventName() + "' is waiting for you. Go and check it out.</p>";

    mailService.sendMail(userEmail, subject, senderName, mailContent);
  }
}