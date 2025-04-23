package com.example.redcrescentoopproject;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session implements Serializable {
    private String sessionName;
    private String moduleId;
    private LocalDate sessionDate;
    private String startTime;
    private String endTime;
    private String location;
    private String notes;

    public Session(String sessionName, String moduleId, LocalDate sessionDate, String startTime, String endTime, String location, String notes) {
        this.sessionName = sessionName;
        this.moduleId = moduleId;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.notes = notes;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
        return sessionName + " | " + moduleId + " | " + sessionDate + " | " + startTime + " | " + endTime + " | " + location + " | " + notes;
    }

    public static void saveToFile(Session session) throws IOException {
        List<Session> existingSessions = new ArrayList<>();
        File file = new File("session.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        existingSessions.add((Session) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new IOException("Error reading existing data");
            }
        }

        existingSessions.add(session);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Session s : existingSessions) {
                oos.writeObject(s);
            }
        }
    }

    public static List<Session> loadFromFile() throws IOException, ClassNotFoundException {
        List<Session> sessions = new ArrayList<>();
        File file = new File("session.bin");

        if (!file.exists()) {
            return sessions;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    sessions.add((Session) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return sessions;
    }
}