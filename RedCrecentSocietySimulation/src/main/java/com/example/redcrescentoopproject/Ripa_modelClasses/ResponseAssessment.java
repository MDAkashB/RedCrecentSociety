package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ResponseAssessment implements Serializable {
    private String disasterType;
    private String location;
    private LocalDateTime assessmentTime;
    private int affectedPopulation;
    private int volunteersDeployed;
    private int reliefPackagesDistributed;
    private String challengesFaced;
    private String recommendations;
    private String status;

    public ResponseAssessment(String disasterType, String location, int affectedPopulation) {
        this.disasterType = disasterType;
        this.location = location;
        this.affectedPopulation = affectedPopulation;
        this.assessmentTime = LocalDateTime.now();
        this.status = "In Progress";
        this.challengesFaced = "";
        this.recommendations = "";
    }

    // Getters and Setters
    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getAssessmentTime() {
        return assessmentTime;
    }

    public void setAssessmentTime(LocalDateTime assessmentTime) {
        this.assessmentTime = assessmentTime;
    }

    public int getAffectedPopulation() {
        return affectedPopulation;
    }

    public void setAffectedPopulation(int affectedPopulation) {
        this.affectedPopulation = affectedPopulation;
    }

    public int getVolunteersDeployed() {
        return volunteersDeployed;
    }

    public void setVolunteersDeployed(int volunteersDeployed) {
        this.volunteersDeployed = volunteersDeployed;
    }

    public int getReliefPackagesDistributed() {
        return reliefPackagesDistributed;
    }

    public void setReliefPackagesDistributed(int reliefPackagesDistributed) {
        this.reliefPackagesDistributed = reliefPackagesDistributed;
    }

    public String getChallengesFaced() {
        return challengesFaced;
    }

    public void setChallengesFaced(String challengesFaced) {
        this.challengesFaced = challengesFaced;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Disaster Type: " + disasterType + "\n" +
                "Location: " + location + "\n" +
                "Assessment Time: " + assessmentTime + "\n" +
                "Affected Population: " + affectedPopulation + "\n" +
                "Volunteers Deployed: " + volunteersDeployed + "\n" +
                "Relief Packages Distributed: " + reliefPackagesDistributed + "\n" +
                "Challenges Faced: " + challengesFaced + "\n" +
                "Recommendations: " + recommendations + "\n" +
                "Status: " + status;
    }
}