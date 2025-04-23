package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Volunteer extends User implements Serializable {
    private int volunteerId;
    private String areaOfInterest;
    private boolean availability;
    private List<Task> assignedTasks;
    private List<Skill> skills;
    private List<Report> reports;

    public Volunteer(int id, String name, String email, String phone, String password,
            int volunteerId, String areaOfInterest) {
        super(id, name, email, phone, password);
        this.volunteerId = volunteerId;
        this.areaOfInterest = areaOfInterest;
        this.availability = true;
        this.assignedTasks = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.reports = new ArrayList<>();
    }

    public boolean registerForEvent(Event event) {
        if (availability) {
            event.addVolunteer(this);
            return true;
        }
        return false;
    }

    public void submitReport(Report report) {
        reports.add(report);
    }

    public List<Task> viewAssignedTasks() {
        return assignedTasks;
    }

    public void updateAvailability(boolean status) {
        this.availability = status;
    }

    public boolean attendTraining(Training training) {
        if (availability) {
            training.addAttendee(this);
            return true;
        }
        return false;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    // Getters and Setters
    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getAreaOfInterest() {
        return areaOfInterest;
    }

    public void setAreaOfInterest(String areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
    }

    public boolean isAvailable() {
        return availability;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Report> getReports() {
        return reports;
    }
}