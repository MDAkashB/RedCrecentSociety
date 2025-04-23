package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BloodDonationDrive implements Serializable {
    private String driveName;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int targetDonors;
    private int actualDonors;
    private List<VolunteerAssignment> volunteerAssignments;
    private String safetyMeasures;
    private String promotionDetails;
    private String status;
    private List<String> donorFollowUps;

    public BloodDonationDrive(String driveName, String location, LocalDateTime startTime,
            LocalDateTime endTime, int targetDonors) {
        this.driveName = driveName;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.targetDonors = targetDonors;
        this.actualDonors = 0;
        this.volunteerAssignments = new ArrayList<>();
        this.safetyMeasures = "";
        this.promotionDetails = "";
        this.status = "Planned";
        this.donorFollowUps = new ArrayList<>();
    }

    // Getters and Setters
    public String getDriveName() {
        return driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getTargetDonors() {
        return targetDonors;
    }

    public void setTargetDonors(int targetDonors) {
        this.targetDonors = targetDonors;
    }

    public int getActualDonors() {
        return actualDonors;
    }

    public void setActualDonors(int actualDonors) {
        this.actualDonors = actualDonors;
    }

    public List<VolunteerAssignment> getVolunteerAssignments() {
        return volunteerAssignments;
    }

    public void setVolunteerAssignments(List<VolunteerAssignment> volunteerAssignments) {
        this.volunteerAssignments = volunteerAssignments;
    }

    public String getSafetyMeasures() {
        return safetyMeasures;
    }

    public void setSafetyMeasures(String safetyMeasures) {
        this.safetyMeasures = safetyMeasures;
    }

    public String getPromotionDetails() {
        return promotionDetails;
    }

    public void setPromotionDetails(String promotionDetails) {
        this.promotionDetails = promotionDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getDonorFollowUps() {
        return donorFollowUps;
    }

    public void setDonorFollowUps(List<String> donorFollowUps) {
        this.donorFollowUps = donorFollowUps;
    }

    public void addVolunteerAssignment(VolunteerAssignment assignment) {
        volunteerAssignments.add(assignment);
    }

    public void addDonorFollowUp(String followUp) {
        donorFollowUps.add(followUp);
    }

    @Override
    public String toString() {
        return "Drive Name: " + driveName + "\n" +
                "Location: " + location + "\n" +
                "Start Time: " + startTime + "\n" +
                "End Time: " + endTime + "\n" +
                "Target Donors: " + targetDonors + "\n" +
                "Actual Donors: " + actualDonors + "\n" +
                "Status: " + status;
    }
}