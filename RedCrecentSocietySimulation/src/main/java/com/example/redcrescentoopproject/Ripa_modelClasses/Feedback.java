package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;

public class Feedback implements Serializable {
    private int feedbackId;
    private Organizer organizer;
    private Volunteer volunteer;
    private String comment;
    private int rating;

    public Feedback(int feedbackId, Organizer organizer, Volunteer volunteer, String comment, int rating) {
        this.feedbackId = feedbackId;
        this.organizer = organizer;
        this.volunteer = volunteer;
        this.comment = comment;
        this.rating = rating;
    }

    public void editFeedback(String comment, int rating) {
        this.comment = comment;
        this.rating = rating;
    }

    public String getFeedbackSummary() {
        return "Feedback from " + organizer.getName() + " to " + volunteer.getName() +
                "\nRating: " + rating + "/5" +
                "\nComment: " + comment;
    }

    // Getters and Setters
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}