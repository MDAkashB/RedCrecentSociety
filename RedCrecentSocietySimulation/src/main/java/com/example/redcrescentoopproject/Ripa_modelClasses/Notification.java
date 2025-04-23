package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Notification implements Serializable {
    private int notificationId;
    private User sentTo;
    private String message;
    private LocalDateTime timestamp;
    private boolean isRead;

    public Notification(int notificationId, User sentTo, String message) {
        this.notificationId = notificationId;
        this.sentTo = sentTo;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.isRead = false;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public String getNotificationDetails() {
        return "Notification ID: " + notificationId + "\n" +
                "To: " + sentTo.getName() + "\n" +
                "Message: " + message + "\n" +
                "Time: " + timestamp + "\n" +
                "Status: " + (isRead ? "Read" : "Unread");
    }

    // Getters and Setters
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public User getSentTo() {
        return sentTo;
    }

    public void setSentTo(User sentTo) {
        this.sentTo = sentTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isRead() {
        return isRead;
    }
}