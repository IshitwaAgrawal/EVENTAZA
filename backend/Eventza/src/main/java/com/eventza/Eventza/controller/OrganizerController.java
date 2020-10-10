package com.eventza.Eventza.controller;

import com.eventza.Eventza.Repository.UserRepository;
import com.eventza.Eventza.Service.FileUploadService;
import com.eventza.Eventza.Service.OrganizerVerificationService;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OrganizerController {

  @Autowired
  OrganizerVerificationService organizerVerificationService;

  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  FileUploadService fileUploadService;

  @PostMapping("/{username}/becomeAnOrganizer")
  public ResponseEntity<?> becomeAnOrganizer(@PathVariable("username") String username,
      @RequestParam("PAN_card_number") String cardNumber, @RequestParam("ID_Proof") MultipartFile file)
      throws Exception {

    User user = userService.getUserByUsername(username);

    if(cardNumber.isEmpty()){
      return new ResponseEntity<>("PAN card number required", HttpStatus.EXPECTATION_FAILED);
    }

    if(cardNumber.length() != 10){
      return new ResponseEntity<>("invalid PAN card number", HttpStatus.EXPECTATION_FAILED);
    }

    if(file.isEmpty()){
      return new ResponseEntity<>("ID Proof required!", HttpStatus.EXPECTATION_FAILED);
    }

    fileUploadService.documentUpload(file, username);
    organizerVerificationService.organizerVerificationMail(user, cardNumber);

    return new ResponseEntity<>("Mail sent", HttpStatus.OK);

  }

  @GetMapping("/acceptOrganizer/{username}")
  public ResponseEntity<?> acceptOrganizer(@PathVariable("username") String username){
    User user = userService.getUserByUsername(username);
    userRepository.updateOrganiser(user.getId(), true);

    return new ResponseEntity<>("Organizer Verified", HttpStatus.ACCEPTED);
  }
}
