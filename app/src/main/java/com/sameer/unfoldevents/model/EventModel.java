package com.sameer.unfoldevents.model;

public class EventModel {

    private String eventType;
    private String eventDate;
    private String guests;
    private String venue;
    private String food;
    private String budget;

    public EventModel() {
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.guests = guests;
        this.venue = venue;
        this.food = food;
        this.budget = budget;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
