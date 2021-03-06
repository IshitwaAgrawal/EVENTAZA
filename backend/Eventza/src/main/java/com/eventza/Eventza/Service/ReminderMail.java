package com.eventza.Eventza.Service;

import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReminderMail {


  @Autowired
  MailService mailService;


 public void sendReminderMail(String eventName, String userEmail) {
   String subject = "Event Reminder";

   String senderName = "EVENTAZA APP";

   String mailContent = "<p>Dear "+userEmail+", </p>";
   mailContent += "<p>Your registered event " + eventName + " is 1 day away.</p>";
   mailContent += "<p> Get ready to roll on! </p>";

   mailService.sendMail(userEmail,subject,senderName,mailContent);
 }
}
