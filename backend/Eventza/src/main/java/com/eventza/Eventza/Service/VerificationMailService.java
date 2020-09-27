package com.eventza.Eventza.Service;

import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class VerificationMailService {

    @Autowired
    MailService mailService;

    public void sendVerificationEmail(User user){
        String subject = "Please verify your registration.";

        String senderName = "EVENTAZA APP";

        String mailContent = "<p>Dear "+user.getName()+", </p>";

        String site = "{app.hostUrl}";

        String verifyUrl = "/verify/"+user.getVerificationToken();
        mailContent += "<p>Please click the link below to verify the registration</p>";
        // <a href="">VERIFY</a>
        mailContent += "<a href=\""+site+verifyUrl+"\">VERIFY</a><br>";
        //mailContent += site+verifyUrl+" , Please access this url!";

        mailService.sendMail(user,subject,senderName,mailContent);
    }
}
