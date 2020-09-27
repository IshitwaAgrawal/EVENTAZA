package com.eventza.Eventza.Service;

import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsLetterService {
    @Autowired
    MailService mailService;

    public void sendNewsLetter(User user,boolean status){
        String subject = "NEWS LETTER FROM EVENTAZA";
        String senderName = "EVENTAZA APP";
        String content;
        if(status){
            content = "<h1>Welcome, to the EVENTAZA News Letter!!";
        }
        else{
            content = "<h1>You have successfully unsubscribed from EVENTAZA newsletter.</h1>";
        }

        mailService.sendMail(user,subject,senderName,content);
    }

}
