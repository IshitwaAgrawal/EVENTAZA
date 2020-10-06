package com.eventza.Eventza.Service;

import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    JavaMailSender mailSender;


    @Async
    public void sendMail(String userEmail,String subject,String senderName,String mailContent){
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        try{
            helper.setFrom("${spring.mail.username}",senderName);
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setText(mailContent,true);

            mailSender.send(message);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Async
    public void sendContactMail(String name, String email, String role, String userMessage){

        String subject = role + " message";
        String mailContent = "<p>User's name: " + name + " </p>";
        mailContent += "<p>User's email: " + email + " </p><br>";
        mailContent += "<p>" + userMessage + "</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try{
            helper.setFrom("${spring.mail.username}", name);
            helper.setTo("eventaza076@gmail.com");
            helper.setSubject(subject);
            helper.setText(mailContent, true);
            mailSender.send(message);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
