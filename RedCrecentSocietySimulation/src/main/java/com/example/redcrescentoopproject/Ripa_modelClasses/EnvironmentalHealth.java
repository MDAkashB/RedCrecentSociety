package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentalHealth implements Serializable {
    private String campaignName;
    private String location;
    private LocalDate date;
    private String description;
    private String progress;
    private String impactAssessment;
    private String status;
    private List<VolunteerAssignment> volunteers;

    public EnvironmentalHealth(String campaignName) {
        this.campaignName = campaignName;
        this.date = LocalDate.now();
        this.status = "Planned";
        this.volunteers = new ArrayList<>();
    }

    public static class VolunteerAssignment implements Serializable {
        private String volunteerName;
        private String role;
        private String location;
        private String status;

        public VolunteerAssignment(String volunteerName, String role, String location) {
            this.volunteerName = volunteerName;
            this.role = role;
            this.location = location;
            this.status = "Assigned";
        }

        // Getters and setters
        public String getVolunteerName() {
            return volunteerName;
        }

        public void setVolunteerName(String volunteerName) {
            this.volunteerName = volunteerName;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    // Getters and setters
    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getImpactAssessment() {
        return impactAssessment;
    }

    public void setImpactAssessment(String impactAssessment) {
        this.impactAssessment = impactAssessment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<VolunteerAssignment> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<VolunteerAssignment> volunteers) {
        this.volunteers = volunteers;
    }

    @Override
    public String toString() {
        return "Campaign: " + campaignName + "\n" +
                "Location: " + location + "\n" +
                "Date: " + date + "\n" +
                "Status: " + status + "\n" +
                "Volunteers: " + volunteers.size();
    }
}