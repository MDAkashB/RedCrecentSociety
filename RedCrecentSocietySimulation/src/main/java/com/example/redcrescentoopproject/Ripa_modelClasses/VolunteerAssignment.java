package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;

public class VolunteerAssignment implements Serializable {
    private Volunteer volunteer;
    private String assignedRole;
    private String status;
    private String location;
    private String notes;

    public VolunteerAssignment(Volunteer volunteer, String assignedRole, String location) {
        this.volunteer = volunteer;
        this.assignedRole = assignedRole;
        this.location = location;
        this.status = "Pending";
        this.notes = "";
    }

    // Getters and Setters
    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public String getAssignedRole() {
        return assignedRole;
    }

    public void setAssignedRole(String assignedRole) {
        this.assignedRole = assignedRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Volunteer: " + volunteer.getName() + "\n" +
                "Role: " + assignedRole + "\n" +
                "Location: " + location + "\n" +
                "Status: " + status + "\n" +
                "Notes: " + notes;
    }
}