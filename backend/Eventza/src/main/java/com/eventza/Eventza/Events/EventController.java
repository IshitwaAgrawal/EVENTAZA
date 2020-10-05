
package com.eventza.Eventza.Events;

import com.eventza.Eventza.Categories.CategoryService;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

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

import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import org.springframework.web.bind.annotation.*;


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
  @Autowired
  private EventRepository eventRepository;

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


  @RequestMapping(method = RequestMethod.GET, path = "/events/{eventId}")
  public EventModel getRequestedEvent(@PathVariable String eventId)
      throws EventNotFoundException {
    try {
      // UUID uuid = new UUID(
      //   new BigInteger(eventId.substring(0, 16), 16).longValue(),
      //    new BigInteger(eventId.substring(16), 16).longValue());
      UUID id = UUID.fromString(eventId);
      return eventService.getEventById(id);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  /*
  @GetMapping("/categories/{categoryName}/events")
  public EventModel getRequestedEvent(@RequestBody Map<String,String> event_id)
      throws EventNotFoundException {
    UUID id = UUID.fromString(event_id.get("id"));
    System.out.println(id);
    return eventService.getEventById(id);
  }
*/
  @RequestMapping(method = RequestMethod.GET, path = "/categories/{categoryName}/events")
  public List<EventModel> getAllEventsFromRequestedCategory(@PathVariable String categoryName) {
    return eventService.getAllEventsFromRequestedCategory(categoryName);
  }

  @PostMapping("/categories/{categoryName}/events")
  public ResponseEntity<String> addNewEvent(@PathVariable String categoryName,
      @RequestBody EventModel event) throws IOException {
    try {
      UUID id = categoryService.getCategoryId(categoryName);
      event.setCategory(categoryService.getRequestedCategory(id));
      eventService.addNewEvent(event);
      return new ResponseEntity<String>("New Event added", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<String>(
          e.getMessage(), HttpStatus.BAD_REQUEST);
    }
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
  public String updateExistingEvent(@PathVariable String eventName, @RequestBody EventModel event) {
    eventService.updateExistingEvent(event);
    return eventName + " updated";
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/categories/{categoryName}/events/{eventName}")
  public String deleteEvent(@PathVariable String eventName) {
    eventService.deleteEvent(eventName);
    return eventName + " deleted";
  }

  @RequestMapping(method = RequestMethod.POST, path = "/{eventName}/{rating}")
  public String rateAnEvent(@PathVariable String eventName, @PathVariable Integer rating,
      @RequestBody Map<String, String> username) {

    UUID id = eventService.getEventId(eventName);
    User user = userService.getUserByUsername(username.get("username"));
    Double rate = eventService.rateAnEvent(id, rating, user);
    return eventName + " rated" + rate;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/recommendedEvents")
  public List<EventModel> getRecommendedEvents() {
    return eventService.getRecommendedEvents();
  }


  @RequestMapping(method = RequestMethod.GET, path = "/search/{location}")
  public List<EventModel> searchEventsByLocation(@PathVariable String location) {
    return eventService.searchEventsByLocation(location);
  }


  @GetMapping("/categories/getAllEvents")
  public List<EventModel> getAllEvents() {
    return eventService.getAllEvents();
  }

  @GetMapping("/getPastEvents")
  public List<EventModel> getPastEvents() throws ParseException {
    return eventService.getPastEvents();
  }


  @RequestMapping(method = RequestMethod.POST, path = "/{eventName}/register")
  public ResponseEntity<?> registerUserInEvent(@PathVariable String eventName,
      @RequestBody Map<String, String> username) {
   // UUID id = UUID.fromString(eventId);
    UUID id = eventService.getEventId(eventName);
    User user = userService.getUserByUsername(username.get("username"));
    eventService.registerUserInEvent(id, user);
    user.registerEvent(eventService.getEventById(id));
    userService.updateUser(user);
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/featuredEvents")
  public List<EventModel> getFeaturedEvents() {
    return eventService.getFeaturedEvents();
  }

  @RequestMapping(method = RequestMethod.GET, path = "/upcomingEvents")
  public List<EventModel> getUpcomingEvents() {
    return eventService.getUpcomingEvents();
  }

  @RequestMapping(method = RequestMethod.GET, path = "/ongoingEvents")
  public List<EventModel> getOngoingEvents() {
    return eventService.getOngoingEvents();
  }

  @GetMapping("/search")
  public List<EventModel> searchFor(@RequestParam String keyword) {
    return eventRepository.findAll(keyword);
  }

}
