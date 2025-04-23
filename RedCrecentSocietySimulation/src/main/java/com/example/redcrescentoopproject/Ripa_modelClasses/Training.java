package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Training implements Serializable {
    private int trainingId;
    private String topic;
    private LocalDateTime schedule;
    private String instructor;
    private List<Volunteer> attendees;
    private boolean isCancelled;

    public Training(int trainingId, String topic, LocalDateTime schedule, String instructor) {
        this.trainingId = trainingId;
        this.topic = topic;
        this.schedule = schedule;
        this.instructor = instructor;
        this.attendees = new ArrayList<>();
        this.isCancelled = false;
    }

    public void addAttendee(Volunteer volunteer) {
        if (!attendees.contains(volunteer)) {
            attendees.add(volunteer);
        }
    }

    public void cancelTraining() {
        this.isCancelled = true;
        // Notify all attendees
        for (Volunteer attendee : attendees) {
            attendee.addNotification(new Notification(0, attendee,
                    "Training '" + topic + "' has been cancelled."));
        }
    }

    public void reschedule(LocalDateTime newSchedule) {
        this.schedule = newSchedule;
        // Notify all attendees
        for (Volunteer attendee : attendees) {
            attendee.addNotification(new Notification(0, attendee,
                    "Training '" + topic + "' has been rescheduled to " + newSchedule));
        }
    }

    // Getters and Setters
    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDateTime getSchedule() {
        return schedule;
    }

    public void setSchedule(LocalDateTime schedule) {
        this.schedule = schedule;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public List<Volunteer> getAttendees() {
        return attendees;
    }

    public boolean isCancelled() {
        return isCancelled;
    }
}