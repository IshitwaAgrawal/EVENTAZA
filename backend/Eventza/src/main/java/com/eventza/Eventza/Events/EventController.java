
package com.eventza.Eventza.Events;

import com.eventza.Eventza.Categories.CategoryModel;
import com.eventza.Eventza.Categories.CategoryService;
import java.util.List;
import java.util.UUID;

import com.eventza.Eventza.Exception.EventNotFoundException;
import com.eventza.Eventza.Service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class EventController {

  @Autowired
  private EventService eventService;
  @Autowired
  private CategoryService categoryService;
  @Autowired
  private UserService userService;
  @Autowired
  private FileUploadService fileUploadService;

//    @PostMapping("/categories/{categoryName}/events")
//    public String addNewEvent(@PathVariable String categoryName, @RequestBody EventModel event){
//        try {
//            UUID id = categoryService.getCategoryId(categoryName);
//            event.setCategory(categoryService.getRequestedCategory(id));
//            eventService.addNewEvent(event);
//            return "New event added";
//        }
//        catch (Exception e){
//            return e.getMessage();
//        }
//    }

  @RequestMapping(method = RequestMethod.GET, path = "/categories/{categoryName}/events/{eventName}")
  public EventModel getRequestedEvent(@PathVariable String eventName)throws EventNotFoundException {
    return eventService.getRequestedEvent(eventName);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/categories/{categoryName}/events")
  public List<EventModel> getAllEventsFromRequestedCategory(@PathVariable String categoryName) {
    return eventService.getAllEventsFromRequestedCategory(categoryName);
  }

  @PostMapping("/categories/{categoryName}/events")
  public ResponseEntity<?> addNewEvent(@PathVariable String categoryName, @RequestBody EventModel event,@RequestParam("file") MultipartFile file){
    User user = userService.getUserByUsername(event.getUsername());
    userService.increaseCreatedEvent(user);
    UUID id = categoryService.getCategoryId(categoryName);
    event.setCategory(categoryService.getRequestedCategory(id));
      try {
          fileUploadService.fileUpload(file,event);
//          return new ResponseEntity<String>("File upload Successful", HttpStatus.OK);
      }
      catch (Exception e){
          return new ResponseEntity<String>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
      }
    eventService.addNewEvent(event);
    return new ResponseEntity<>("New Event added",HttpStatus.OK);
  }

 /* @RequestMapping(method = RequestMethod.PUT, path = "/categories/{categoryName}/events/{eventName}")
  public String updateExistingEvent(@PathVariable String categoryName,
      @PathVariable String eventName, @RequestBody EventModel event) {
    UUID id = categoryService.getCategoryId(categoryName);
    event.setCategory(new CategoryModel(id, categoryName));
    UUID event_id = eventService.getEventId(eventName);
    eventService.updateExistingEvent(event_id, event);
    return eventName + " updated";
  }
  */
  @PutMapping("/categories/{categoryName}/events/{eventName}")
  public String updateExistingEvent(@PathVariable String eventName, @RequestBody EventModel event){
    eventService.updateExistingEvent(event);
    return eventName + " updated";
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/categories/{categoryName}/events/{eventName}")
  public String deleteEvent(@PathVariable String eventName) {
    eventService.deleteEvent(eventName);
    return eventName + " deleted";
  }

  @RequestMapping(method = RequestMethod.POST, path = "/categories/{categoryName}/events/{eventName}/{rating}")
    public String rateAnEvent(@PathVariable String eventName, @PathVariable Integer rating){
    UUID id = eventService.getEventId(eventName);
    Double rate = eventService.rateAnEvent(id, rating);
    return eventName + " rated" + rate;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/recommendedEvents")
  public List<EventModel> getRecommendedEvents(){
   return eventService.getRecommendedEvents();
  }


  @RequestMapping(method = RequestMethod.GET, path = "/search/{location}")
  public List<EventModel> searchEventsByLocation(@PathVariable String location) {
    return eventService.searchEventsByLocation(location);
  }


    @GetMapping("/categories/getAllEvents")
    public List<EventModel> getAllEvents(){
      return eventService.getAllEvents();
    }

    @GetMapping("/getPastEvents")
    public List<EventModel> getPastEvents()throws ParseException {
      Date d = new Date();
      List<EventModel> events = new ArrayList<>();
      List<EventModel> pastEvents = new ArrayList<>();
      events = eventService.getAllEvents();
      for(EventModel event:events){
        Date endD = new SimpleDateFormat("yyyy-MM-dd").parse(event.getEndDate().substring(0,10));
        if(endD.before(d)){
          pastEvents.add(event);
        }
      }
      return pastEvents;
    }

  }
