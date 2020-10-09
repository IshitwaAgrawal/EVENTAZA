package com.eventza.Eventza.Service;

import com.eventza.Eventza.model.User;
import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MailService {

  @Autowired
  JavaMailSender mailSender;


  @Async
  public void sendMail(String userEmail, String subject, String senderName, String mailContent) {
    MimeMessage message = mailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(message);

    try {
      helper.setFrom("${spring.mail.username}", senderName);
      helper.setTo(userEmail);
      helper.setSubject(subject);
      helper.setText(mailContent, true);

      mailSender.send(message);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Async
  public void sendContactMail(String name, String email, String role, String userMessage) {

    String subject = role + " message";
    String mailContent = "<p>User's name: " + name + " </p>";
    mailContent += "<p>User's email: " + email + " </p><br>";
    mailContent += "<p>" + userMessage + "</p>";
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    try {
      helper.setFrom("${spring.mail.username}", name);
      helper.setTo("eventaza076@gmail.com");
      helper.setSubject(subject);
      helper.setText(mailContent, true);
      mailSender.send(message);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Async
  public void sendOrganizerVerificationMail(String userEmail, String subject, String senderName,
      String mailContent, MultipartFile file) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    try {
      helper.setFrom("${spring.mail.username}", senderName);
      helper.setTo("eventaza076@gmail.com");
      helper.setSubject(subject);
      helper.setText(mailContent, true);

      String path = "C:\\Users\\admin\\Desktop\\study\\2nd Sem\\comp";
      FileSystemResource f
          = new FileSystemResource(new File(path+"\\"+file.getOriginalFilename()));
      helper.addAttachment(file.getOriginalFilename(), f);

      mailSender.send(message);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
