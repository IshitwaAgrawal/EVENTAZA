  package com.eventza.Eventza.Events;

  import com.eventza.Eventza.Categories.CategoryModel;
  import com.eventza.Eventza.Categories.CategoryService;

  import java.text.ParseException;
  import java.text.SimpleDateFormat;
  import java.util.ArrayList;
  import java.util.Date;
  import java.util.List;
  import java.util.UUID;

  import com.eventza.Eventza.Service.UserService;
  import com.eventza.Eventza.model.User;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.security.core.userdetails.UsernameNotFoundException;
  import org.springframework.web.bind.annotation.*;

  @RestController
  public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @GetMapping("/categories/{categoryName}/events/{eventName}")
    public EventModel getRequestedEvent(@PathVariable String eventName){
      return eventService.getRequestedEvent(eventName);
    }

    @GetMapping("/categories/{categoryName}/events")
    public List<EventModel> getAllEventsFromRequestedCategory(@PathVariable String categoryName){
      return eventService.getAllEventsFromRequestedCategory(categoryName);
    }

    @PostMapping("/categories/{categoryName}/events")
    public String addNewEvent(@PathVariable String categoryName, @RequestBody EventModel event){
      try {
        UUID id = categoryService.getCategoryId(categoryName);
        event.setCategory(categoryService.getRequestedCategory(id));
        eventService.addNewEvent(event);
        return "New event added";
      }
      catch (Exception e){
        return e.getMessage();
      }
    }

    @PutMapping("/categories/{categoryName}/events/{eventName}")
    public String updateExistingEvent(@PathVariable String eventName, @RequestBody EventModel event){
      eventService.updateExistingEvent(event);
      return eventName + " updated";
    }

    @DeleteMapping("/categories/{categoryName}/events/{eventName}")
    public String deleteEvent(@PathVariable String eventName){
      eventService.deleteEvent(eventName);
      return eventName + " deleted";
    }

    @GetMapping("/search/{location}")
    public List<EventModel> searchEventsByLocation(@PathVariable String location){
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
