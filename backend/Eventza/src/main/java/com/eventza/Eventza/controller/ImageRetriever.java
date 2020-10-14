package com.eventza.Eventza.controller;

import com.eventza.Eventza.Events.EventService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ImageRetriever {

  @Autowired
  EventService eventService;

  @Autowired
  ImageDecompressorService imageDecompressorService;


  @GetMapping("/{eventName}/image")
  public byte[] retrieveImage(@PathVariable("eventName") String eventName)
      throws IOException {
    String filePath = "D:\\eventazaData\\eventImages\\";
    String eventId = eventService.getEventId(eventName).toString();
    File file = new File(filePath + eventId);

    byte[] buffer = new byte[(int) file.length()];
    FileInputStream fileInputStream = new FileInputStream(file);

    fileInputStream.read(buffer);
    fileInputStream.close();

    return buffer;
  }

}