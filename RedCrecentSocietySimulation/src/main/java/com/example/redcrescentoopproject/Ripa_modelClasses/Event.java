package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event implements Serializable {
    private int eventId;
    private String title;
    private Date date;
    private String venue;
    private Organizer organizedBy;
    private List<Volunteer> volunteers;

    public Event(int eventId, String title, Date date, String venue, Organizer organizedBy) {
        this.eventId = eventId;
        this.title = title;
        this.date = date;
        this.venue = venue;
        this.organizedBy = organizedBy;
        this.volunteers = new ArrayList<>();
    }

    public void addVolunteer(Volunteer volunteer) {
        if (!volunteers.contains(volunteer)) {
            volunteers.add(volunteer);
        }
    }

    public void removeVolunteer(Volunteer volunteer) {
        volunteers.remove(volunteer);
    }

    public void updateSchedule(Date newDate) {
        this.date = newDate;
    }

    // Getters and Setters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Organizer getOrganizedBy() {
        return organizedBy;
    }

    public void setOrganizedBy(Organizer organizedBy) {
        this.organizedBy = organizedBy;
    }

    public List<Volunteer> getVolunteers() {
        return volunteers;
    }
}