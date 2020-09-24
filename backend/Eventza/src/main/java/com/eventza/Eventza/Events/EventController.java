package com.eventza.Eventza.Events;

import com.eventza.Eventza.Categories.CategoryModel;
import com.eventza.Eventza.Categories.CategoryService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

  @Autowired
  private EventService eventService;
  @Autowired
  private CategoryService categoryService;

  @RequestMapping(method = RequestMethod.GET, path = "/categories/{categoryName}/events/{eventName}")
  public EventModel getRequestedEvent(@PathVariable String eventName) {
    return eventService.getRequestedEvent(eventName);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/categories/{categoryName}/events")
  public List<EventModel> getAllEventsFromRequestedCategory(@PathVariable String categoryName) {
    return eventService.getAllEventsFromRequestedCategory(categoryName);
  }

  @RequestMapping(method = RequestMethod.POST, path = "/categories/{categoryName}/events")
  public String addNewEvent(@PathVariable String categoryName, @RequestBody EventModel event) {
    UUID id = categoryService.getCategoryId(categoryName);
    event.setCategory(new CategoryModel(id, categoryName));
    eventService.addNewEvent(event);
    return "New event added";
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/categories/{categoryName}/events/{eventName}")
  public String updateExistingEvent(@PathVariable String categoryName,
      @PathVariable String eventName, @RequestBody EventModel event) {
    UUID id = categoryService.getCategoryId(categoryName);
    event.setCategory(new CategoryModel(id, categoryName));
    UUID event_id = eventService.getEventId(eventName);
    eventService.updateExistingEvent(event_id, event);

    return eventName + " updated";

  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/categories/{categoryName}/events/{eventName}")
  public String deleteEvent(@PathVariable String eventName) {
    eventService.deleteEvent(eventName);
    return eventName + " deleted";
  }

  @RequestMapping(method = RequestMethod.GET, path = "/search/{location}")
  public List<EventModel> searchEventsByLocation(@PathVariable String location) {
    return eventService.searchEventsByLocation(location);
  }
}
