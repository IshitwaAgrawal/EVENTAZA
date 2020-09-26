package com.eventza.Eventza.Events;

import com.eventza.Eventza.Categories.CategoryModel;
import com.eventza.Eventza.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

@Entity
public class EventModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private UUID id;
  private String username;
  private String eventName;
  private String organiserName;
  private String startDate;
  private String endDate;
  private String eventLocation;
  private int price;
  private int rating;
  private int totalTickets;
  private int registrations;
  private String eventDescription;
  @ManyToOne(cascade = CascadeType.ALL)
  private CategoryModel category;


  public EventModel() {
  }

  public EventModel(String eventName,
                    String organiserName,
                    String startDate,
                    String lastDate,
                    String eventLocation,
                    Integer price,
                    Integer rating,
                    Integer totalTickets,
                    Integer registrations,
                    String eventDescription ) throws ParseException {
    this.id = UUID.randomUUID();
    this.eventName = eventName;
    this.organiserName = organiserName;
//    this.endDate = new Date(lastDate);
//    this.startDate = new Date(startDate);
    this.endDate = parseDate(lastDate);
    this.startDate = parseDate(startDate);
    this.eventLocation = eventLocation;
    this.price = price;
    this.rating = rating;
    this.totalTickets = totalTickets;
    this.registrations = registrations;
    this.eventDescription = eventDescription;
  }

  private static String parseDate(String date) {
    try {
      Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
      return new SimpleDateFormat("yyyy-MM-dd").format(d).substring(0,10);
    } catch (ParseException e) {
      return null;
    }
  }


  public UUID getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setStartDate(String eventDate) {
    this.startDate = eventDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
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

  public String getStartDate() {
    return startDate ;
  }

  public String getEventLocation() {
    return eventLocation;
  }

  public void setEventLocation(String eventLocation) {
    this.eventLocation = eventLocation;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public int getTotalTickets() {
    return totalTickets;
  }

  public void setTotalTickets(int totalTickets) {
    this.totalTickets = totalTickets;
  }

  public int getRegistrations() {
    return registrations;
  }

  public void setRegistrations(int registrations) {
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
