package com.eventza.Eventza.Events;

import com.eventza.Eventza.Categories.CategoryModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EventModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private UUID id;
  private String eventName;
  private String organiserName;
  private Date eventDate;
  private String eventLocation;
  private Integer price;
  private Integer rating;
  private Integer totalTickets;
  private Integer registrations;
  private String eventDescription;
  @ManyToOne
  private CategoryModel category;

  public EventModel() {
  }

  public EventModel(String eventName, String organiserName, String eventDate,
      String eventLocation, Integer price, Integer rating, Integer totalTickets,
      Integer registrations, String eventDescription, String categoryName) throws ParseException {
    this.eventName = eventName;
    this.organiserName = organiserName;
    this.eventDate = new SimpleDateFormat("dd/mm/yyyy").parse(eventDate);
    //Time************************************************************************************************
    this.eventLocation = eventLocation;
    this.price = price;
    this.rating = rating;
    this.totalTickets = totalTickets;
    this.registrations = registrations;
    this.eventDescription = eventDescription;
    this.category = new CategoryModel(category.getId(), categoryName);
  }

  public UUID getId() {
    return id;
  }

  public Integer getRemainingTickets() {
    return totalTickets - registrations;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getOrganiserName() {
    return organiserName;
  }

  public void setOrganiserName(String organiserName) {
    this.organiserName = organiserName;
  }

  public Date getEventDate() {
    return eventDate;
  }

  public void setEventDate(String eventDate) throws ParseException {
    this.eventDate = new SimpleDateFormat("dd/mm/yyyy").parse(eventDate);
  }

  public String getEventLocation() {
    return eventLocation;
  }

  public void setEventLocation(String eventLocation) {
    this.eventLocation = eventLocation;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public Integer getTotalTickets() {
    return totalTickets;
  }

  public void setTotalTickets(Integer totalTickets) {
    this.totalTickets = totalTickets;
  }

  public Integer getRegistrations() {
    return registrations;
  }

  public void setRegistrations(Integer registrations) {
    this.registrations = registrations;
  }

  public String getEventDescription() {
    return eventDescription;
  }

  public void setEventDescription(String eventDescription) {
    this.eventDescription = eventDescription;
  }

  public CategoryModel getCategory() {
    return category;
  }

  public void setCategory(CategoryModel category) {
    this.category = category;
  }
}
