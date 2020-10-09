package com.eventza.Eventza.Events;

import com.eventza.Eventza.Categories.CategoryModel;
import com.eventza.Eventza.Categories.CategoryService;
import com.eventza.Eventza.Repository.RatingRepository;
import com.eventza.Eventza.Repository.UserRepository;
import com.eventza.Eventza.Service.MailService;
import com.eventza.Eventza.Service.NewsletterMail;
import com.eventza.Eventza.Service.ReminderMail;
import com.eventza.Eventza.model.Ratings;
import com.eventza.Eventza.model.RequestEvent;
import com.eventza.Eventza.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.eventza.Eventza.Exception.EventNotFoundException;
import com.eventza.Eventza.Service.UserService;
import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private UserService userService;

  @Autowired
  private ReminderMail reminderMail;

  @Autowired
  private RatingRepository ratingRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  NewsletterMail newsletterMail;

  @Autowired
  private MailService mailService;


  public UUID getEventId(String eventName) {
    return eventRepository.findByEventName(eventName).getId();
  }

  public EventModel getRequestedEvent(String eventName) throws EventNotFoundException {
    try {
      return eventRepository.findByEventName(eventName);
    } catch (Exception e) {
      throw new EventNotFoundException();
    }
  }

  public List<EventModel> getAllEventsFromRequestedCategory(String categoryName) {
    List<EventModel> events = new ArrayList<>();
    UUID id = categoryService.getCategoryId(categoryName);
    eventRepository.findByCategoryId(id).forEach(event -> events.add(event));
    return events;
  }


  public List<EventModel> getAllEvents() {
    return (List<EventModel>) eventRepository.findAll();
  }


  public void addNewEvent(EventModel eventModel, User user) {
    try {
//      eventRepository.save(eventModel);
      String subject = "Event Added";
      String mailContent = "Event succesfully added.";
      mailService.sendMail(user.getEmail(), subject, user.getName(), mailContent);
//      eventRepository.updateOrganiserMail(eventModel.getId(), user.getEmail());
//      eventRepository.setRemainingTickets(eventModel.getId(), eventModel.getTotalTickets());
//      user.addHostedEvents(eventModel);
//      userService.updateUser(user);
      userService.addHostedEvent(user,eventModel);
      List<User> users = userRepository.findAll();
      for (User u : users) {
        if (u.isNewsletter_service()) {
          newsletterMail.sendNewsletterMail(u, eventModel);
        }
      }
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
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

  public void updateExistingEvent(EventModel eventModel) {
    eventRepository.save(eventModel);
  }

//  public void deleteEvent(String eventName) {
//    EventModel event = eventRepository.findByEventName(eventName);
//    this.deleteWishlistEvents(event);
//    eventRepository.deleteById(getEventId(eventName));
//  }

  public void deleteEvent(UUID uuid) {
    try {
      EventModel event = eventRepository.findById(uuid).orElse(null);
      User user = userService.getUserByEmail(event.getOrganiserEmail());
      user.deleteHostedEvent(event);
      userService.updateUser(user);
      List<User> users = userService.getAllUsers();
      for (User u : users) {
        u.getWishlist().remove(event);
      }
      userService.deleteHostedEvent(user, event);
      eventRepository.deleteById(uuid);
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  public void deleteWishlistEvents(EventModel event){
  }

  public List<EventModel> searchEventsByLocation(String eventLocation) {
    List<EventModel> events = new ArrayList<>();
    eventRepository.findByEventLocation(eventLocation).forEach(event -> events.add(event));
    return events;
  }


  public List<EventModel> getTrendingEvents() {
    List<EventModel> events = new ArrayList<>();
    eventRepository.findByAverageRatingGreaterThanEqual(3.5).forEach(event -> events.add(event));
    return events;

  }

  public Double rateAnEvent(UUID id, Integer rating, User user) {
    EventModel event = eventRepository.findById(id).get();
    Integer previousTotalRating = event.getTotalRating();
    if (!event.getRatedByUsers().contains(user)) {
      event.getRatedByUsers().add(user);
      Integer ratingCounter = event.counter();
      Ratings rate = new Ratings(event.getId(), user.getId(), rating);
      ratingRepository.save(rate);
      eventRepository
          .setRatingForEventModule(id, (double) (previousTotalRating + rating) / ratingCounter,
              previousTotalRating + rating);
    } else {
      Integer ratingCounter = event.getRatingCounter();
      Ratings rate = ratingRepository.getRating(event.getId(), user.getId());
      Integer previousRatingOfUser = rate.getPreviousRating();
      eventRepository.setRatingForEventModule(id,
          (double) (previousTotalRating - previousRatingOfUser + rating) / ratingCounter,
          previousTotalRating + rating - previousRatingOfUser);
      ratingRepository.setPreviousRating(rate.getId(), rating);
    }
    return event.getAverageRating();
  }

  public EventModel getEventById(UUID id) {

    try {
      return eventRepository.getEventModelById(id);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }

  }

  public void registerUserInEvent(UUID id, User user) {
    EventModel event = eventRepository.findById(id).get();
    event.getRegisteredUsers().add(user);
    event.registrationCounter();
    event.updateRemainingTickets();
    String subject = "Successfully registered";
    String mailContent = "Successfully registered in " + event.getEventName();
    mailService.sendMail(user.getEmail(), subject, user.getName(), mailContent);
  }


  public List<EventModel> getFeaturedEvents() {
    List<CategoryModel> categories = categoryService.getAllCategories();
    List<EventModel> featuredEvents = new ArrayList<>();
    for (CategoryModel category : categories) {
      featuredEvents
          .add(eventRepository.findFirstByCategoryIdOrderByAverageRatingDesc(category.getId()));
    }
    return featuredEvents;
  }

  private Date convertToDateAndTime(String date, String time) {
    LocalDate datePart = LocalDate.parse(date);
    LocalTime timePart = LocalTime.parse(time);
    LocalDateTime dt = LocalDateTime.of(datePart, timePart);
    Date combinedDateAndTime = java.sql.Timestamp.valueOf(dt);
    return combinedDateAndTime;
  }

  public List<RequestEvent> getPastEvents() {
    Date d = new Date();
    List<EventModel> events = getAllEvents();
    List<RequestEvent> pastEvents = new ArrayList<>();
    for (EventModel event : events) {
      Date endDateTime = convertToDateAndTime(event.getEndDate(), event.getEndTime());
      if (endDateTime.before(d)) {
        pastEvents.add(getRequestEvent(event));
      }
    }
    return pastEvents;
  }

  public List<EventModel> getUpcomingEvents() {
    Date currentDateTime = new Date();
    List<EventModel> events = getAllEvents();
    List<EventModel> upcomingEvents = new ArrayList<>();
    for (EventModel event : events) {
      Date eventStartDateTime = convertToDateAndTime(event.getStartDate(), event.getStartTime());

      if (eventStartDateTime.after(currentDateTime)) {
        upcomingEvents.add(event);
      }
    }
    return upcomingEvents;
  }

  public List<RequestEvent> findSearchedEvents(String keyword){
    List<EventModel> events = eventRepository.findAll(keyword);
    List<RequestEvent> eventList = new ArrayList<>();
    for(EventModel e:events){
      eventList.add(getRequestEvent(e));
    }
    return eventList;
  }

  public List<EventModel> getOngoingEvents() {
    Date currentDateTime = new Date();
    List<EventModel> events = getAllEvents();
    List<EventModel> ongoingEvents = new ArrayList<>();
    for (EventModel event : events) {
      Date eventStartDateTime = convertToDateAndTime(event.getStartDate(), event.getStartTime());
      Date eventEndDateTime = convertToDateAndTime(event.getEndDate(), event.getEndTime());

      if (eventStartDateTime.before(currentDateTime) && eventEndDateTime.after(currentDateTime)) {
        ongoingEvents.add(event);
      }
    }
    return ongoingEvents;
  }

  public static RequestEvent getRequestEvent(EventModel event){
    return new RequestEvent(event.getId(),event.getEventName(),event.getOrganiserName(),event.getEventLocation(),event.getPrice(),event.getAverageRating(),event.getRatingCounter(),event.getTotalTickets(),event.getRemainingTickets(),event.getRegistrations(),event.getStartDate(),event.getEndDate(),event.getStartTime(),event.getEndTime(),event.getEventDescription(),event.getCategory());
  }
}
