package com.eventza.Eventza.controller;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Events.EventRepository;
import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.Service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ImageUploader {

  @Autowired
  EventService eventService;

  @Autowired
  ImageCompressorService imageCompressorService;

  @Autowired
  EventRepository eventRepository;

  @Autowired
  FileUploadService fileUploadService;


  @PostMapping("/{eventName}/imageUpload")
  public ResponseEntity<String> uploadImage(@PathVariable String eventName,
      @RequestParam("image") MultipartFile image) throws Exception {
    try {
      EventModel event = eventService.getRequestedEvent(eventName);
      fileUploadService.imageUpload(image,event);
      return new ResponseEntity<String>("Image uploaded Successfully", HttpStatus.OK);
    }
    catch (Exception e){
      return new ResponseEntity<String>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
    }
  }
}
