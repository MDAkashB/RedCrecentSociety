package com.example.redcrescentoopproject;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientReg implements Serializable {
    private String patientId;
    private String fullName;
    private int age;
    private String gender;
    private String bloodGroup;
    private String contactNumber;
    private String medicalCondition;
    private LocalDate registrationDate;

    public PatientReg(String patientId, String fullName, int age, String gender,
                      String bloodGroup, String contactNumber, String medicalCondition,
                      LocalDate registrationDate) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
        this.registrationDate = registrationDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return patientId + " | " + fullName + " | " + age + " | " + gender + " | " +
                bloodGroup + " | " + contactNumber + " | " + registrationDate;
    }

    public static void saveToFile(PatientReg patient) throws IOException {
        List<PatientReg> existingPatients = new ArrayList<>();
        File file = new File("PatientReg.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        existingPatients.add((PatientReg) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new IOException("Error reading existing data");
            }
        }

        existingPatients.add(patient);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (PatientReg p : existingPatients) {
                oos.writeObject(p);
            }
        }
    }

    public static List<PatientReg> loadFromFile() throws IOException, ClassNotFoundException {
        List<PatientReg> patients = new ArrayList<>();
        File file = new File("PatientReg.bin");

        if (!file.exists()) {
            return patients;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    patients.add((PatientReg) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return patients;
    }
}