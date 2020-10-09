package com.eventza.Eventza.Service;

import com.eventza.Eventza.Events.EventModel;
import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EventReminder {

  @Autowired
  EventService eventService;

  @Autowired
  private ReminderMail reminderMail;


  @Scheduled(cron = "1 0 0 * * ?")
  public void sendEventReminder() throws ParseException {
    System.out.println("start");
    LocalDate localDate = LocalDate.now().plusDays(1);
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    List<EventModel> events = eventService.getAllEvents();
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
}
