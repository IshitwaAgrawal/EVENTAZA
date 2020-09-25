package com.eventza.Eventza.Events;

import com.eventza.Eventza.Categories.CategoryModel;
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


  public UUID getEventId(String eventName){
    return eventRepository.findByEventName(eventName).getId();
  }
  public EventModel getRequestedEvent(String eventName){
   return eventRepository.findByEventName(eventName);
  }

    public List<EventModel> getAllEventsFromRequestedCategory(String categoryName){
    List<EventModel> events = new ArrayList<>();
    UUID id = categoryService.getCategoryId(categoryName);
    eventRepository.findByCategoryId(id).forEach(event -> events.add(event));
    return events;
  }

  public List<EventModel> getAllEvents(){
    return (List<EventModel>) eventRepository.findAll();
  }

  public void addNewEvent(EventModel eventModel){
    eventRepository.save(eventModel);
  }

  /*public void updateExistingEvent(UUID id, EventModel eventModel){
    EventModel event = eventRepository.findById(id).get();
    Double rating = event.getAverageRating();
    Integer registrations = event.getRegistrations();
    eventRepository.deleteById(id);
    eventModel.setAverageRating(rating);
    eventModel.setRegistrations(registrations);
    eventRepository.save(eventModel);
  }*/

  public void updateExistingEvent(EventModel eventModel){
    eventRepository.save(eventModel);
  }

  public void deleteEvent(String eventName){
    eventRepository.deleteById(getEventId(eventName));
  }

  public List<EventModel> searchEventsByLocation(String eventLocation){
    List<EventModel> events = new ArrayList<>();
    eventRepository.findByEventLocation(eventLocation).forEach(event -> events.add(event));
    return events;
  }

  public List<EventModel> getRecommendedEvents(){
    List<EventModel> events = new ArrayList<>();
    eventRepository.findByAverageRatingGreaterThanEqual(3.5).forEach(event -> events.add(event));
    return events;

  }

  public Double rateAnEvent(UUID id, Integer rating) {
    EventModel event = eventRepository.findById(id).get();
    Integer ratingCounter = event.counter();
    Integer prev_rating = event.getTotalRating();
    eventRepository.setRatingForEventModule(id, (double)(prev_rating + rating)/ratingCounter, prev_rating + rating);
    return event.getAverageRating();
  }
}
