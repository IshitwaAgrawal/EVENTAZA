package com.eventza.Eventza.Events;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EventRepository extends CrudRepository<EventModel, UUID> {

  @Query("select e from EventModel e where e.eventName=?1")
  EventModel findByEventName(String eventName);


  @Query("select e from EventModel e where e.id=?1")
  EventModel getEventModelById(UUID id);

  List<EventModel> findByCategoryId(UUID categoryId);

  List<EventModel> findByEventLocation(String eventLocation);

  List<EventModel> findByAverageRatingGreaterThanEqual(Double cutoffRating);

  EventModel findFirstByCategoryIdOrderByAverageRatingDesc(UUID id);

  @Query("Select e from EventModel e where lower(e.eventName) like lower(concat('%', ?1, '%'))"
      + " or lower(e.eventLocation) like lower(concat('%', ?1, '%'))"
      + " or lower(e.organiserName) like lower(concat('%', ?1, '%'))"
      + " or lower(e.category.categoryName) like lower(concat('%', ?1, '%'))")
  List<EventModel> findAll(String keyword);

  @Transactional
  @Modifying
  @Query("UPDATE EventModel event set event.organiserEmail = ?2 where event.id=?1")
  void updateOrganiserMail(UUID id,String email);

  @Transactional
  @Modifying
  @Query("update EventModel event set event.averageRating = ?2, event.totalRating = ?3 where event.id = ?1")
  void setRatingForEventModule(UUID id, Double rating, Integer total_rating);

  @Transactional
  @Modifying
  @Query("UPDATE EventModel event set event.price = ?2 where event.id=?1")
  void updatePrice(UUID id,int price);

  @Transactional
  @Modifying
  @Query("UPDATE EventModel event set event.eventLocation = ?2 where event.id=?1")
  void updateLocation(UUID id,String location);

  @Transactional
  @Modifying
  @Query("UPDATE EventModel event set event.eventName = ?2 where event.id=?1")
  void updateName(UUID id,String new_name);

  @Transactional
  @Modifying
  @Query("UPDATE EventModel event set event.totalTickets =?2 where event.id=?1")
  void updateTickets(UUID id,int tickets);

  @Transactional
  @Modifying
  @Query("UPDATE EventModel event set event.startDate =?2 where event.id=?1")
  void updateStartDate(UUID id,String startDate);

  @Transactional
  @Modifying
  @Query("UPDATE EventModel event set event.endDate =?2 where event.id=?1")
  void updateEndDate(UUID id,String endDate);

  @Transactional
  @Modifying
  @Query("UPDATE EventModel event set event.startTime =?2 where event.id=?1")
  void updateStartTime(UUID id,String startTime);

  @Transactional
  @Modifying
  @Query("UPDATE EventModel event set event.endTime =?2 where event.id=?1")
  void updateEndTime(UUID id,String endTime);

  @Transactional
  @Modifying
  @Query("UPDATE EventModel event set event.eventDescription = ?2 where event.id=?1")
  void updateDescription(UUID id,String description);




  /*@Transactional
  @Modifying
  @Query("update EventModel event set event.imageByte = ?2 where event.id = ?1")
  void setImageByteForEventModule(UUID id, byte[] imageByte);
*/

}
