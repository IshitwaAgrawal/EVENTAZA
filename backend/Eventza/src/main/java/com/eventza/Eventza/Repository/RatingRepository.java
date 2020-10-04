package com.eventza.Eventza.Repository;

import com.eventza.Eventza.model.Ratings;
import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RatingRepository extends CrudRepository<Ratings, UUID> {

  @Query("select r from Ratings r where r.eventId = ?1 and r.userId = ?2")
  Ratings getRating(UUID eventId, UUID userId);

  @Transactional
  @Modifying
  @Query("update Ratings r set r.previousRating = ?2 where r.id = ?1")
  void setPreviousRating(UUID id, Integer previousRating);

}
