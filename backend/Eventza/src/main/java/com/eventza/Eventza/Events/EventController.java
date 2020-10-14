
package com.eventza.Eventza.Events;

import com.eventza.Eventza.Categories.CategoryService;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import com.eventza.Eventza.Exception.EventNotFoundException;
import com.eventza.Eventza.Service.FileUploadService;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.eventza.Eventza.model.NewEventRequest;
import com.eventza.Eventza.model.RequestEvent;
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
@RequestMapping("/api")
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


  @RequestMapping(method = RequestMethod.GET, path = "/categories/{categoryName}/events")
  public List<EventModel> getAllEventsFromRequestedCategory(@PathVariable String categoryName) {
    return eventService.getAllEventsFromRequestedCategory(categoryName);
  }

  @PostMapping("/categories/{categoryName}/events/{username}")
  public ResponseEntity<String> addNewEvent(@PathVariable String categoryName,
      @RequestBody NewEventRequest event, @PathVariable String username) throws IOException {
    try {
      UUID id = categoryService.getCategoryId(categoryName);
      User user = userService.getUserByUsername(username);
      if(!user.isOrganizer()){
        return new ResponseEntity<>("You are not a verified organizer yet!", HttpStatus.EXPECTATION_FAILED);
      }
      event.setCategoryModel(categoryService.getRequestedCategory(id));

      EventModel new_event = new EventModel(event.getEventName(), user.getName(), user.getEmail(),
          event.getStartDate(), event.getStartTime(), event.getEndDate(), event.getEndTime(),
          event.getLocation(), event.getPrice(), event.getTotalTickets(),
          event.getEventDescription(), event.getCategoryModel());

      Date startDateTime = eventService.convertToDateAndTime(event.getStartDate(), event.getStartTime());
      Date endDateTime = eventService.convertToDateAndTime(event.getEndDate(), event.getEndTime());
      if(endDateTime.equals(startDateTime) || endDateTime.before(startDateTime) ){
        return new ResponseEntity<>("start date and time cannot be equal to or after the end date and time", HttpStatus.BAD_REQUEST);
      }
      eventService.addNewEvent(new_event, user);
      return new ResponseEntity<String>("New Event added", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<String>(
          e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }


  @PutMapping("/categories/{categoryName}/events/{eventName}")
  public String updateExistingEvent(@PathVariable String eventName, @RequestBody EventModel event) {
    eventService.updateExistingEvent(event);
    return eventName + " updated";
  }


  @RequestMapping(method = RequestMethod.DELETE, path = "/events/{eventId}")
  public String delete_Event(@PathVariable String eventId) {
    UUID id;
    try {
      id = UUID.fromString(eventId);
    } catch (Exception e) {
      id = new UUID(
          new BigInteger(eventId.substring(0, 16), 16).longValue(),
          new BigInteger(eventId.substring(16), 16).longValue());
    }
    eventService.deleteEvent(id);
    return "deleted";
  }

  @RequestMapping(method = RequestMethod.POST, path = "/{eventName}/{rating}")
  public String rateAnEvent(@PathVariable String eventName, @PathVariable Integer rating,
      @RequestBody Map<String, String> username) {

    UUID id = eventService.getEventId(eventName);
    EventModel event = eventService.getEventById(id);
    User user = userService.getUserByUsername(username.get("username"));
    if(user.getEmail().equals(event.getOrganiserEmail())){
      return "cannot rate your own event!";
    }
    Double rate = eventService.rateAnEvent(id, rating, user);
    return eventName + " rated";
  }

  @RequestMapping(method = RequestMethod.GET, path = "/trendingEvents")
  public List<RequestEvent> getTrendingEvents() {
    return eventService.getTrendingEvents();
  }


  @RequestMapping(method = RequestMethod.GET, path = "/search/{location}")
  public List<EventModel> searchEventsByLocation(@PathVariable String location) {
    return eventService.searchEventsByLocation(location);

  }


  @GetMapping("/categories/getAllEvents")
  public List<String> getAllEvents() {

    //return eventService.getAllEvents();
    List<String> names = new ArrayList<>();
    eventService.getAllEvents().forEach(name -> names.add(
        name.getEventName()));
    return names;
  }


  @GetMapping("/getPastEvents")
  public List<RequestEvent> getPastEvents() throws ParseException {
    return eventService.getPastEvents();
  }


  @RequestMapping(method = RequestMethod.POST, path = "/{eventName}/register")
  public ResponseEntity<?> registerUserInEvent(@PathVariable String eventName,
      @RequestBody Map<String, String> username) {
    // UUID id = UUID.fromString(eventId);
    UUID id = eventService.getEventId(eventName);
    EventModel event = eventService.getEventById(id);
    if (eventService.getEventById(id).getRemainingTickets() == 0) {
      return new ResponseEntity<String>("No tickets available", HttpStatus.EXPECTATION_FAILED);
    }
    User user = userService.getUserByUsername(username.get("username"));
    if(user.getEmail().equals(event.getOrganiserEmail())){
      return new ResponseEntity<String>("Cannot register in own events", HttpStatus.BAD_REQUEST);
    }
    eventService.registerUserInEvent(id, user);
    user.registerEvent(eventService.getEventById(id));
    userService.updateUser(user);
    return new ResponseEntity<String>("User registered", HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/featuredEvents")
  public List<RequestEvent> getFeaturedEvents() {
    return eventService.getFeaturedEvents();
  }

  @RequestMapping(method = RequestMethod.GET, path = "/upcomingEvents")
  public List<RequestEvent> getUpcomingEvents() {
    return eventService.getUpcomingEvents();
  }

  @RequestMapping(method = RequestMethod.GET, path = "/ongoingEvents")
  public List<RequestEvent> getOngoingEvents() {
    return eventService.getOngoingEvents();
  }

  @GetMapping("/search")
  public List<RequestEvent> searchFor(@RequestParam String keyword) {
    return eventService.getSearchedEvents(keyword);
  }


  @PatchMapping("/update/{username}")
  public ResponseEntity<?> updateEvent(@RequestBody Map<String,String> data, @PathVariable("username") String username){
    User user  = userService.getUserByUsername(username);
    if(data.get("id")!=null) {
      UUID id;
      try {
        id = UUID.fromString(data.get("id"));
      } catch (IllegalArgumentException e) {
        id = new UUID(
            new BigInteger(data.get("id").substring(0, 16), 16).longValue(),
            new BigInteger(data.get("id").substring(16), 16).longValue());
      }

      EventModel event = eventService.getEventById(id);
      RequestEvent req_e = eventService.convertToRequestEvent(event);

      if(!user.getEmail().equals(event.getOrganiserEmail())){
        return new ResponseEntity<>("Only user who created the event can update it", HttpStatus.BAD_REQUEST);
      }
      if(eventService.getOngoingEvents().contains(req_e)){
        return new ResponseEntity<>("Ongoing events cannot be updated", HttpStatus.EXPECTATION_FAILED);
      }

      if (data.get("price") != null) {
        int price = Integer.parseInt(data.get("price"));
        eventRepository.updatePrice(id, price);
      }
      if (data.get("eventLocation") != null) {
        if(eventService.checkDate(event,2)){
          eventRepository.updateLocation(id, data.get("eventLocation"));
        }
      }
      if (data.get("eventName") != null) {
        if(eventService.checkDate(event,3)) {
          eventRepository.updateName(id, data.get("eventName"));
        }
      }
      if (data.get("totalTickets") != null) {
        eventRepository.updateTickets(id, Integer.parseInt(data.get("totalTickets")));
      }
      if (data.get("startDate") != null) {
        if(eventService.checkDate(event,1)) {
          eventRepository.updateStartDate(id, data.get("startDate"));
        }
      }
      if (data.get("endDate") != null) {
        if(eventService.checkDate(event,1)) {
          eventRepository.updateEndDate(id, data.get("endDate"));
        }
      }
      if (data.get("startTime") != null) {
        if(eventService.checkDate(event,2)) {
          eventRepository.updateStartTime(id, data.get("startTime"));
        }
      }
      if (data.get("endTime") != null) {
        if(eventService.checkDate(event,1)) {
          eventRepository.updateEndTime(id, data.get("endTime"));
        }
      }
      if (data.get("eventDescription") != null) {
        eventRepository.updateDescription(id, data.get("eventDescription"));
      }
      if (data.get("category") != null) {
        if(eventService.checkDate(event,2)) {
          String name = data.get("category");
          EventModel eventModel = eventRepository.getEventModelById(id);
          eventModel.setCategory(categoryService.getRequestedCategory(name));
        }
      }
      EventModel e = eventService.getEventById(id);
      RequestEvent req = new RequestEvent(e.getId(),e.getEventName(),e.getOrganiserName(),e.getEventLocation(),e.getPrice(),e.getAverageRating(),e.getRatingCounter(),e.getTotalTickets(),e.getRemainingTickets(),e.getRegistrations(),e.getStartDate(),e.getEndDate(),e.getStartTime(),e.getEndTime(),e.getEventDescription(),e.getCategory());
      return new ResponseEntity<RequestEvent>(req, HttpStatus.OK);
    }
    return new ResponseEntity<String>("Please provide the UUID of the event.",
        HttpStatus.BAD_REQUEST);
  }

}
