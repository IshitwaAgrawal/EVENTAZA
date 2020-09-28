package com.eventza.Eventza.controller;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.Service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BrochureUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    EventService eventService;

    @PostMapping("/brochure/{eventname}")
    public ResponseEntity<String> upload(@PathVariable String eventname , @RequestParam("file") MultipartFile file)throws Exception{
        try {
            EventModel eventModel = eventService.getRequestedEvent(eventname);
            eventModel.setBrochure_name(file.getOriginalFilename());
            eventService.updateExistingEvent(eventModel);
            fileUploadService.fileUpload(file,eventModel);
            return new ResponseEntity<String>("File upload Successful", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
