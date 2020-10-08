package com.eventza.Eventza.model;

import com.eventza.Eventza.Categories.CategoryModel;

import java.util.UUID;

public class RequestEvent {

    private UUID id;
    private String eventName;
    private String organiserName;
    private String eventLocation;
    private Integer price;
    private Double averageRating = 0.0;
    private Integer ratingCounter = 0;
    private Integer totalTickets;
    private Integer remainingTickets;
    private Integer registrations = 0;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String eventDescription;
    private CategoryModel categoryModel;

    public RequestEvent(UUID id,String eventName,String organiserName,String eventLocation,int price,double averageRating,int ratingCounter,int totalTickets,int remainingTickets,int registrations,String startDate,String endDate,String startTime,String endTime,String eventDescription,CategoryModel categoryModel){
        this.id = id;
        this.eventName = eventName;
        this.organiserName = organiserName;
        this.eventLocation = eventLocation;
        this.price = price;
        this.averageRating = averageRating;
        this.ratingCounter = ratingCounter;
        this.totalTickets = totalTickets;
        this.remainingTickets = remainingTickets;
        this.registrations = registrations;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.eventDescription = eventDescription;
        this.categoryModel = categoryModel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Integer getRatingCounter() {
        return ratingCounter;
    }

    public void setRatingCounter(Integer ratingCounter) {
        this.ratingCounter = ratingCounter;
    }

    public Integer getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(Integer totalTickets) {
        this.totalTickets = totalTickets;
    }

    public Integer getRemainingTickets() {
        return remainingTickets;
    }

    public void setRemainingTickets(Integer remainingTickets) {
        this.remainingTickets = remainingTickets;
    }

    public Integer getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Integer registrations) {
        this.registrations = registrations;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
