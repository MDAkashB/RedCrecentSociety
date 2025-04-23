package com.example.redcrescentoopproject;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HealthCamps implements Serializable {
    private String campId;
    private String campName;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String services;
    private String teamLeader;
    private int expectedParticipants;
    private String status;

    public HealthCamps(String campId, String campName, String location,
                       LocalDate startDate, LocalDate endDate, String services,
                       String teamLeader, int expectedParticipants, String status) {
        this.campId = campId;
        this.campName = campName;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.services = services;
        this.teamLeader = teamLeader;
        this.expectedParticipants = expectedParticipants;
        this.status = status;
    }

    // Getters and Setters
    public String getCampId() { return campId; }
    public String getCampName() { return campName; }
    public String getLocation() { return location; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getServices() { return services; }
    public String getTeamLeader() { return teamLeader; }
    public int getExpectedParticipants() { return expectedParticipants; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return campId + " | " + campName + " | " + location + " | " + startDate;
    }

    public static void saveToFile(HealthCamps camp) throws IOException {
        List<HealthCamps> existingCamps = new ArrayList<>();
        File file = new File("HealthCamps.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        existingCamps.add((HealthCamps) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new IOException("Error reading existing data");
            }
        }

        existingCamps.add(camp);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (HealthCamps hc : existingCamps) {
                oos.writeObject(hc);
            }
        }
    }

    public static void saveAllToFile(List<HealthCamps> camps) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("HealthCamps.bin"))) {
            for (HealthCamps hc : camps) {
                oos.writeObject(hc);
            }
        }
    }

    public static List<HealthCamps> loadFromFile() throws IOException, ClassNotFoundException {
        List<HealthCamps> camps = new ArrayList<>();
        File file = new File("HealthCamps.bin");

        if (!file.exists()) {
            return camps;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    camps.add((HealthCamps) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return camps;
    }
}