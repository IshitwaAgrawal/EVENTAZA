package com.eventza.Eventza.Service;

import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendVerificationEmail(User user){
        String subject = "Please verify your registration.";
        String senderName = "EVENTAZA APP";
        String mailContent = "<p>Dear "+user.getName()+", </p>";
        String site = "localhost:8000";
        String verifyUrl = "/verify/"+user.getVerificationToken();
        mailContent += "<p>Please click the link below to verify the registration</p>";
        mailContent += "<a href=\""+site+verifyUrl+"\">VERIFY ME!</a>";
        mailContent += site+verifyUrl+" , Please access this url!";

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        try{
            helper.setFrom("${spring.mail.username}",senderName);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(mailContent,true);

            mailSender.send(message);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
