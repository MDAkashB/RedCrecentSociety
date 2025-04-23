package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicalAssistance implements Serializable {
    private String location;
    private String medicalNeeds;
    private List<VolunteerAssignment> medicalTeam;
    private String medicalSupplies;
    private List<String> patientRecords;
    private String status;

    public MedicalAssistance(String location) {
        this.location = location;
        this.medicalNeeds = "";
        this.medicalTeam = new ArrayList<>();
        this.medicalSupplies = "";
        this.patientRecords = new ArrayList<>();
        this.status = "Active";
    }

    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMedicalNeeds() {
        return medicalNeeds;
    }

    public void setMedicalNeeds(String medicalNeeds) {
        this.medicalNeeds = medicalNeeds;
    }

    public List<VolunteerAssignment> getMedicalTeam() {
        return medicalTeam;
    }

    public void addMedicalTeamMember(VolunteerAssignment member) {
        this.medicalTeam.add(member);
    }

    public String getMedicalSupplies() {
        return medicalSupplies;
    }

    public void setMedicalSupplies(String medicalSupplies) {
        this.medicalSupplies = medicalSupplies;
    }

    public List<String> getPatientRecords() {
        return patientRecords;
    }

    public void addPatientRecord(String record) {
        this.patientRecords.add(record);
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
                "Status: " + status + "\n" +
                "Medical Team Size: " + medicalTeam.size() + "\n" +
                "Patient Records: " + patientRecords.size();
    }
}