package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Report implements Serializable {
    private int reportId;
    private Task task;
    private Volunteer submittedBy;
    private String content;
    private LocalDateTime submittedAt;

    public Report(int reportId, Task task, Volunteer submittedBy, String content) {
        this.reportId = reportId;
        this.task = task;
        this.submittedBy = submittedBy;
        this.content = content;
        this.submittedAt = LocalDateTime.now();
    }

    public String getSummary() {
        return "Report ID: " + reportId + "\n" +
                "Task: " + task.getDescription() + "\n" +
                "Submitted by: " + submittedBy.getName() + "\n" +
                "Submitted at: " + submittedAt + "\n" +
                "Content: " + content;
    }

    public File exportAsPDF() {
        // TODO: Implement PDF export functionality
        return null;
    }

    // Getters and Setters
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Volunteer getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Volunteer submittedBy) {
        this.submittedBy = submittedBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
}