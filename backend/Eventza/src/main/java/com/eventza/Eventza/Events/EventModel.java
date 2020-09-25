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
  private Date eventStartDate;
  private Date eventEndDate;
  private String eventLocation;
  private Integer price;
  private Double averageRating = 0.0;
  private Integer totalRating = 0;
  private Integer ratingCounter = 0;
  private Integer totalTickets;
  private Integer registrations = 0;
  private String eventDescription;
  @ManyToOne
  private CategoryModel category;

  public EventModel() {
  }

  public EventModel(String eventName, String organiserName, String eventStartDate,
      String eventEndDate, String eventLocation, Integer price,
      Integer totalTickets, String eventDescription, String categoryName) throws ParseException {
    this.eventName = eventName;
    this.organiserName = organiserName;
    this.eventStartDate = new SimpleDateFormat("dd/mm/yyyy").parse(eventStartDate);
    this.eventEndDate = new SimpleDateFormat("dd/mm/yyyy").parse(eventEndDate);
    //Time************************************************************************************************
    this.eventLocation = eventLocation;
    this.price = price;
    this.totalTickets = totalTickets;
    this.eventDescription = eventDescription;
    this.category = new CategoryModel(category.getId(), categoryName);
  }

  public Integer counter(){
    return ++ratingCounter;
  }

  public Integer getTotalRating(){
    return totalRating;
  }

  public void setTotalRating(Integer rating){
    this.totalRating += rating;
  }

  public UUID getId() {
    return id;
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

  public Date getEventStartDate() {
    return eventStartDate;
  }

  public void setEventStartDate(String eventStartDate) throws ParseException {
    this.eventStartDate = new SimpleDateFormat("dd/mm/yyyy").parse(eventStartDate);
  }

  public Date getEventEndDate() {
    return eventEndDate;
  }

  public void setEventEndDate(String eventEndDate) throws ParseException {
    this.eventEndDate = new SimpleDateFormat("dd/mm/yyyy").parse(eventEndDate);
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

  public Double getAverageRating() {
    return averageRating;
  }

  public void setAverageRating(Double averageRating) {
    this.averageRating = averageRating;
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
