package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DisasterAlert implements Serializable {
    private String alertType;
    private String description;
    private String severity;
    private LocalDateTime timestamp;
    private String location;
    private String status;

    public DisasterAlert(String alertType, String description, String severity, String location) {
        this.alertType = alertType;
        this.description = description;
        this.severity = severity;
        this.location = location;
        this.timestamp = LocalDateTime.now();
        this.status = "Active";
    }

    // Getters and Setters
    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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

    @Override
    public String toString() {
        return "Alert Type: " + alertType + "\n" +
                "Description: " + description + "\n" +
                "Severity: " + severity + "\n" +
                "Location: " + location + "\n" +
                "Time: " + timestamp + "\n" +
                "Status: " + status;
    }
}