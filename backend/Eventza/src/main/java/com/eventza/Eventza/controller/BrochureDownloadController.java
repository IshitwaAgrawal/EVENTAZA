package com.eventza.Eventza.controller;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.Exception.EventNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
public class BrochureDownloadController {

    @Autowired
    EventService eventService;

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestBody Map<String,String> event){
        try {
            EventModel eventModel = eventService.getRequestedEvent(event.get("eventName"));
            String filename = eventModel.getBrochure_name();
            if(filename == null){
                return new ResponseEntity<String>("Brochure not found!",HttpStatus.NOT_FOUND);
            }
            System.out.println(filename);
            String filebase = "C:\\Users\\ISHITWA\\Desktop\\EVENTAZA\\backend\\Eventza\\src\\main\\resources\\Files\\";
            Path path = Paths.get(filebase + filename);
            Resource resource = null;
            try {
                resource = new UrlResource(path.toUri());
            } catch (Exception e) {
                e.getMessage();
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"" + resource.getFilename() + "\"")
                    .body(resource);
        }
        catch(EventNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
