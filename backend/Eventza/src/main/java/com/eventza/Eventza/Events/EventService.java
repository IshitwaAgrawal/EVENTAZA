package com.eventza.Eventza.Events;

import com.eventza.Eventza.Categories.CategoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;
  @Autowired
  private CategoryService categoryService;

  public EventModel getRequestedEvent(String eventName){
   return eventRepository.findByEventName(eventName);
  }

    public List<EventModel> getAllEventsFromRequestedCategory(String categoryName){
    List<EventModel> events = new ArrayList<>();
    UUID id = categoryService.getCategoryId(categoryName);
    eventRepository.findByCategoryId(id).forEach(event -> events.add(event));
    return events;
  }

  public void addNewEvent(EventModel eventModel){
    eventRepository.save(eventModel);
  }

  public void updateExistingEvent(EventModel eventModel){
    eventRepository.save(eventModel);
  }

  public void deleteEvent(String eventName){
    eventRepository.deleteById(getRequestedEvent(eventName).getId());
  }

  public List<EventModel> searchEventsByLocation(String eventLocation){
    List<EventModel> events = new ArrayList<>();
    eventRepository.findByEventLocation(eventLocation).forEach(event -> events.add(event));
    return events;
  }
}
