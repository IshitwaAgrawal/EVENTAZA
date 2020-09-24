package com.eventza.Eventza.Events;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<EventModel, UUID> {

  @Query("select e from EventModel e where e.eventName=?1")
  EventModel findByEventName(String eventName);

  List<EventModel> findByCategoryId(UUID categoryId);
  List<EventModel> findByEventLocation(String eventLocation);

}
