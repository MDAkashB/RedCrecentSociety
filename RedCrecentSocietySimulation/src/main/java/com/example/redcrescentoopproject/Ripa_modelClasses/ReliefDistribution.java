package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReliefDistribution implements Serializable {
    private String location;
    private int foodPackages;
    private int waterBottles;
    private int medicalKits;
    private LocalDateTime distributionTime;
    private String coordinator;
    private String status;

    public ReliefDistribution(String location, int foodPackages, int waterBottles, int medicalKits,
            String coordinator) {
        this.location = location;
        this.foodPackages = foodPackages;
        this.waterBottles = waterBottles;
        this.medicalKits = medicalKits;
        this.coordinator = coordinator;
        this.distributionTime = LocalDateTime.now();
        this.status = "Planned";
    }

    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFoodPackages() {
        return foodPackages;
    }

    public void setFoodPackages(int foodPackages) {
        this.foodPackages = foodPackages;
    }

    public int getWaterBottles() {
        return waterBottles;
    }

    public void setWaterBottles(int waterBottles) {
        this.waterBottles = waterBottles;
    }

    public int getMedicalKits() {
        return medicalKits;
    }

    public void setMedicalKits(int medicalKits) {
        this.medicalKits = medicalKits;
    }

    public LocalDateTime getDistributionTime() {
        return distributionTime;
    }

    public void setDistributionTime(LocalDateTime distributionTime) {
        this.distributionTime = distributionTime;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Location: " + location + "\n" +
                "Food Packages: " + foodPackages + "\n" +
                "Water Bottles: " + waterBottles + "\n" +
                "Medical Kits: " + medicalKits + "\n" +
                "Distribution Time: " + distributionTime + "\n" +
                "Coordinator: " + coordinator + "\n" +
                "Status: " + status;
    }
}