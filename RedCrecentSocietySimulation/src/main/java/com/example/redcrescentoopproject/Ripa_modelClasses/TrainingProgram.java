package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrainingProgram implements Serializable {
    private String topic;
    private LocalDate date;
    private String status;
    private String trainer;
    private String materials;
    private List<String> feedback;
    private int participants;
    private String location;

    public TrainingProgram(String topic, LocalDate date, String trainer, String location) {
        this.topic = topic;
        this.date = date;
        this.trainer = trainer;
        this.location = location;
        this.status = "Planned";
        this.materials = "";
        this.feedback = new ArrayList<>();
        this.participants = 0;
    }

    // Getters and Setters
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public List<String> getFeedback() {
        return feedback;
    }

    public void addFeedback(String feedback) {
        this.feedback.add(feedback);
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Training Topic: " + topic + "\n" +
                "Date: " + date + "\n" +
                "Trainer: " + trainer + "\n" +
                "Location: " + location + "\n" +
                "Status: " + status + "\n" +
                "Participants: " + participants;
    }
}