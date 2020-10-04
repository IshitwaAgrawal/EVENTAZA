package com.eventza.Eventza.controller;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Events.EventRepository;
import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.Service.FileUploadService;
import com.eventza.Eventza.Service.ImageCompressorService;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageUploader {

  @Autowired
  EventService eventService;

  @Autowired
  ImageCompressorService imageCompressorService;

  @Autowired
  EventRepository eventRepository;

  @Autowired
  FileUploadService fileUploadService;

  /*@PostMapping("/image/{eventName}")
  public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file,
      @PathVariable String eventName) throws Exception {

    try {

      EventModel event = eventService.getRequestedEvent(eventName);
      byte[] fileContent = file.getBytes();
      byte[] compressed = imageCompressorService.compressImageBytes(fileContent);
      eventRepository.setImageByteForEventModule(event.getId(), compressed);
      return new ResponseEntity<String>("image compressed and stored", HttpStatus.OK);

    }
    catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
  }
  */

  @PostMapping("/{eventName}/imageUpload")
  public ResponseEntity<String> uploadImage(@PathVariable String eventName,
      @RequestParam("image") MultipartFile image) throws Exception {
    try {
      EventModel event = eventService.getRequestedEvent(eventName);
      fileUploadService.imageUpload(image,event);
      return new ResponseEntity<String>("File upload Successful", HttpStatus.OK);
    }
    catch (Exception e){
      return new ResponseEntity<String>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
    }
  }
}
