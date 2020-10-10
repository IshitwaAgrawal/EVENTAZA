package com.eventza.Eventza.Service;

import com.eventza.Eventza.model.User;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OrganizerVerificationService {

  @Autowired
  private MailService mailService;

  public void organizerVerificationMail(User user, String cardNumber)
      throws MessagingException {

    String subject = "Organizer verification";
    String senderName = user.getName();
    String site = "http://localhost:8000/acceptOrganizer" + "/" + user.getUsername();

    String mailContent = "<p>From "+user.getName()+", </p>";
    mailContent += "<p> PAN card number: " + cardNumber + "</p>";
    mailContent += "<p> Click the link to verify the organizer</p>";
    mailContent += "<a href='" + site + "'>VERIFY ORGANIZER</a><br>";
    mailContent += "<p> Below is the attachment of id proof <p>";

    mailService.sendOrganizerVerificationMail(user.getEmail(), subject, senderName, mailContent, user.getUsername());
  }
}
