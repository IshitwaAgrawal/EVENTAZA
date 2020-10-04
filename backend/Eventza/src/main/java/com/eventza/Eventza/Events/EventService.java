package com.eventza.Eventza.Events;

import com.eventza.Eventza.Categories.CategoryModel;
import com.eventza.Eventza.Categories.CategoryService;
import com.eventza.Eventza.Repository.RatingRepository;
import com.eventza.Eventza.Service.MailService;
import com.eventza.Eventza.Service.ReminderMail;
import com.eventza.Eventza.model.Ratings;
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

  @Autowired
  private ReminderMail reminderMail;

  @Autowired
  private RatingRepository ratingRepository;


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

  public void addNewEvent(EventModel eventModel) {
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

  public void updateExistingEvent(EventModel eventModel) {
    eventRepository.save(eventModel);
  }

  public void deleteEvent(String eventName) {
    EventModel event = eventRepository.findByEventName(eventName);
    User user = userService.getUserByUsername(event.getOrganiserName());
    userService.deleteHostedEvent(user, event);
    eventRepository.deleteById(getEventId(eventName));
  }

  public List<EventModel> searchEventsByLocation(String eventLocation) {
    List<EventModel> events = new ArrayList<>();
    eventRepository.findByEventLocation(eventLocation).forEach(event -> events.add(event));
    return events;
  }


  public List<EventModel> getRecommendedEvents() {
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
      new Ratings(event.getId(), user.getId(), rating);
      eventRepository.setRatingForEventModule(id, (double) (previousTotalRating + rating) / ratingCounter,
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
  }


  @Scheduled(cron = "0 0 0 * * ?")
  public void sendEventReminder() throws ParseException {
    LocalDate localDate = LocalDate.now().plusDays(1);
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    List<EventModel> events = getAllEvents();
    for (EventModel event : events) {
      Date eventStartDate = new SimpleDateFormat("yyyy-MM-dd")
          .parse(event.getStartDate().substring(0, 10));
      if (date.equals(eventStartDate)) {
        for (User u : event.getRegisteredUsers()) {
          reminderMail.sendReminderMail(event.getEventName(), u.getEmail());
        }
      }
    }

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

  public List<EventModel> getPastEvents() {
    Date d = new Date();
    List<EventModel> events = getAllEvents();
    List<EventModel> pastEvents = new ArrayList<>();
    for (EventModel event : events) {
      Date endDateTime = convertToDateAndTime(event.getEndDate(), event.getEndTime());

      if (endDateTime.before(d)) {
        pastEvents.add(event);
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
}
