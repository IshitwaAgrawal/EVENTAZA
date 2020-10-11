package com.eventza.Eventza.controller;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.Exception.EventNotFoundException;
import com.eventza.Eventza.Service.ImageDecompressorService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.DataFormatException;
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

 /* @GetMapping(path = "/{eventName}/image")
  public String retrieveImage(@PathVariable("eventName") String eventName)
      throws IOException, EventNotFoundException, DataFormatException {
    EventModel event = eventService.getRequestedEvent(eventName);
    byte[] imageByte = imageDecompressorService.decompressImage(event.getImageByte());
    String image = Base64.getEncoder().encodeToString(imageByte);
    return image;
  }
 */

  @GetMapping("/{eventName}/image")
  public byte[] retrieveImage(@PathVariable("eventName") String eventName)
      throws IOException {
    String filePath = "D:\\eventImages\\";
    String eventId = eventService.getEventId(eventName).toString();
    File file = new File(filePath + eventId);

    byte[] buffer = new byte[(int) file.length()];
    FileInputStream fileInputStream = new FileInputStream(file);

    fileInputStream.read(buffer);
    fileInputStream.close();
    
    return buffer;
  }

}