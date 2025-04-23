package com.example.redcrescentoopproject;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Module implements Serializable {
    private String volunteerId;
    private String moduleName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String trainerName;
    private String status;

    public Module(String volunteerId, String moduleName, LocalDate startDate, LocalDate endDate, String trainerName, String status) {
        this.volunteerId = volunteerId;
        this.moduleName = moduleName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.trainerName = trainerName;
        this.status = status;
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return volunteerId + " | " + moduleName + " | " + startDate + " | " + endDate + " | " + trainerName + " | " + status;
    }

    public static void saveToFile(Module module) throws IOException {
        List<Module> existingModules = new ArrayList<>();
        File file = new File("module.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        existingModules.add((Module) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new IOException("Error reading existing data");
            }
        }

        existingModules.add(module);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Module m : existingModules) {
                oos.writeObject(m);
            }
        }
    }

    public static List<Module> loadFromFile() throws IOException, ClassNotFoundException {
        List<Module> modules = new ArrayList<>();
        File file = new File("module.bin");

        if (!file.exists()) {
            return modules;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    modules.add((Module) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return modules;
    }
}