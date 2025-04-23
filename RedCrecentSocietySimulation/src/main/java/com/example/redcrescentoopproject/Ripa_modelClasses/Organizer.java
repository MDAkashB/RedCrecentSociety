package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Organizer extends User implements Serializable {
    private int organizerId;
    private String department;
    private List<Event> assignedEvents;
    private List<Training> trainings;
    private List<Feedback> feedbacks;

    public Organizer(int id, String name, String email, String phone, String password,
            int organizerId, String department) {
        super(id, name, email, phone, password);
        this.organizerId = organizerId;
        this.department = department;
        this.assignedEvents = new ArrayList<>();
        this.trainings = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
    }

    public void createEvent(Event event) {
        assignedEvents.add(event);
    }

    public void assignVolunteer(Volunteer volunteer, Event event) {
        event.addVolunteer(volunteer);
    }

    public void scheduleTraining(Training training) {
        trainings.add(training);
    }

    public String reviewReport(Report report) {
        return "Report reviewed by " + getName() + " from " + department;
    }

    public void sendNotification(Volunteer volunteer, String message) {
        Notification notification = new Notification(0, volunteer, message);
        volunteer.addNotification(notification);
    }

    // Getters and Setters
    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Event> getAssignedEvents() {
        return assignedEvents;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
}