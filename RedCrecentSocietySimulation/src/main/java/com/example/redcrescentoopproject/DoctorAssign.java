package com.example.redcrescentoopproject;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorAssign implements Serializable {
    private String patientId;
    private String patientName;
    private String medicalCondition;
    private String doctorName;
    private String specialty;
    private String priority;
    private LocalDate assignmentDate;
    private String notes;

    public DoctorAssign(String patientId, String patientName, String medicalCondition,
                        String doctorName, String specialty, String priority,
                        LocalDate assignmentDate, String notes) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.medicalCondition = medicalCondition;
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.priority = priority;
        this.assignmentDate = assignmentDate;
        this.notes = notes;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getPriority() {
        return priority;
    }

    public LocalDate getAssignmentDate() {
        return assignmentDate;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return patientId + " | " + patientName + " | " + doctorName + " | " +
                specialty + " | " + priority + " | " + assignmentDate;
    }

    public static void saveToFile(DoctorAssign assignment) throws IOException {
        List<DoctorAssign> existingAssignments = new ArrayList<>();
        File file = new File("DoctorAssign.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        existingAssignments.add((DoctorAssign) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new IOException("Error reading existing data");
            }
        }

        existingAssignments.add(assignment);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (DoctorAssign da : existingAssignments) {
                oos.writeObject(da);
            }
        }
    }

    public static List<DoctorAssign> loadFromFile() throws IOException, ClassNotFoundException {
        List<DoctorAssign> assignments = new ArrayList<>();
        File file = new File("DoctorAssign.bin");

        if (!file.exists()) {
            return assignments;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    assignments.add((DoctorAssign) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return assignments;
    }
}