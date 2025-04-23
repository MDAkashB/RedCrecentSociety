package com.example.redcrescentoopproject;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Performance implements Serializable {
    private String volunteerId;
    private String moduleId;
    private LocalDate assessmentDate;
    private int performanceScore;
    private int attendanceRate;
    private String feedback;

    public Performance(String volunteerId, String moduleId, LocalDate assessmentDate, int performanceScore, int attendanceRate, String feedback) {
        this.volunteerId = volunteerId;
        this.moduleId = moduleId;
        this.assessmentDate = assessmentDate;
        this.performanceScore = performanceScore;
        this.attendanceRate = attendanceRate;
        this.feedback = feedback;
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public LocalDate getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(LocalDate assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public int getPerformanceScore() {
        return performanceScore;
    }

    public void setPerformanceScore(int performanceScore) {
        this.performanceScore = performanceScore;
    }

    public int getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(int attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return volunteerId + " | " + moduleId + " | " + assessmentDate + " | " + performanceScore + " | " + attendanceRate + " | " + feedback;
    }

    public static void saveToFile(Performance performance) throws IOException {
        List<Performance> existingPerformances = new ArrayList<>();
        File file = new File("performance.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        existingPerformances.add((Performance) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new IOException("Error reading existing data");
            }
        }

        existingPerformances.add(performance);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Performance p : existingPerformances) {
                oos.writeObject(p);
            }
        }
    }

    public static List<Performance> loadFromFile() throws IOException, ClassNotFoundException {
        List<Performance> performances = new ArrayList<>();
        File file = new File("performance.bin");

        if (!file.exists()) {
            return performances;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    performances.add((Performance) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return performances;
    }
}