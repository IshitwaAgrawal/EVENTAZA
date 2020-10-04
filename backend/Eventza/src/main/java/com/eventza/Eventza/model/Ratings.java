package com.eventza.Eventza.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ratings {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private UUID id;
  private UUID eventId;
  private UUID userId;
  private Integer previousRating;

  public Ratings() {
  }

  public Ratings(UUID eventId, UUID userId, Integer previousRating) {
    this.id = UUID.randomUUID();
    this.eventId = eventId;
    this.userId = userId;
    this.previousRating = previousRating;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getEventId() {
    return eventId;
  }

  public void setEventId(UUID eventId) {
    this.eventId = eventId;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public Integer getPreviousRating() {
    return previousRating;
  }

  public void setPreviousRating(Integer previousRating) {
    this.previousRating = previousRating;
  }
}
