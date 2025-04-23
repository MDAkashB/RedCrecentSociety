package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.util.Date;

public class Task  implements Serializable {
    private int taskId;
    private String description;
    private Date deadline;
    private Volunteer assignedTo;
    private String status;
    private Report taskReport;

    public Task(int taskId, String description, Date deadline, Volunteer assignedTo) {
        this.taskId = taskId;
        this.description = description;
        this.deadline = deadline;
        this.assignedTo = assignedTo;
        this.status = "Pending";
        this.taskReport = null;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public void submitTaskReport(Report report) {
        this.taskReport = report;
        this.status = "Completed";
    }

    // Getters and Setters
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Volunteer getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Volunteer assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Report getTaskReport() {
        return taskReport;
    }
}