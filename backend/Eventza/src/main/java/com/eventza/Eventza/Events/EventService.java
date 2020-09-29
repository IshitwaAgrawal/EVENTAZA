package com.eventza.Eventza.Events;

import com.eventza.Eventza.Categories.CategoryModel;
import com.eventza.Eventza.Categories.CategoryService;
import com.eventza.Eventza.Service.MailService;
import com.eventza.Eventza.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.eventza.Eventza.Exception.EventNotFoundException;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;
  @Autowired
  private CategoryService categoryService;
  @Autowired
  private UserService userService;
  MailService mailService;


  public UUID getEventId(String eventName){
    return eventRepository.findByEventName(eventName).getId();
  }

  public EventModel getRequestedEvent(String eventName)throws EventNotFoundException{
    try {
      return eventRepository.findByEventName(eventName);
    }
    catch(Exception e){
      throw new EventNotFoundException();
    }
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
    EventModel event = eventRepository.findByEventName(eventName);
    User user = userService.getUserByUsername(event.getOrganiserName());
    userService.deleteHostedEvent(user,event);
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
    eventRepository.setRatingForEventModule(id, (double) (prev_rating + rating) / ratingCounter, prev_rating + rating);
    return event.getAverageRating();
  }
  public EventModel getEventById(UUID id) {
    return eventRepository.getEventModelById(id);
  }

  public void registerUserInEvent(UUID id, User user){
    EventModel event = eventRepository.findById(id).get();
    event.getRegisteredUsers().add(user);
  }


  @Scheduled(fixedDelay = 2000)
  public void sendEventReminder() throws ParseException {
    LocalDate localDate = LocalDate.now().minusDays(1);
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    List<EventModel> events = getAllEvents();
    for(EventModel event: events){
     Date eventStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(event.getStartDate().substring(0,10));
      if(date.equals(eventStartDate)){
        for(User u : event.getRegisteredUsers()){
          mailService.sendEventReminder(event.getEventName(), u);
        }
      }
    }

  }

  public List<EventModel> getFeaturedEvents() {
    List<CategoryModel> categories = categoryService.getAllCategories();
    List<EventModel> featuredEvents = new ArrayList<>();
    for(CategoryModel category: categories){
        featuredEvents.add(eventRepository.findFirstByCategoryIdOrderByAverageRatingDesc(category.getId()));
    }
    return featuredEvents;
  }
}
