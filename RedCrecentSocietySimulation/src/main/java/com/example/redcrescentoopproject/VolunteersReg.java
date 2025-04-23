package com.example.redcrescentoopproject;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VolunteersReg implements Serializable {
    private String name;
    private String id;
    private String bloodGroup;
    private LocalDate joinDate;
    private String contact;
    private String skills;

    public VolunteersReg(String name, String id, String bloodGroup, LocalDate joinDate, String contact, String skills) {
        this.name = name;
        this.id = id;
        this.bloodGroup = bloodGroup;
        this.joinDate = joinDate;
        this.contact = contact;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return name + " | " + id + " | " + bloodGroup + " | " + joinDate + " | " + contact + " | " + skills;
    }

    public static void saveToFile(VolunteersReg volunteer) throws IOException {
        List<VolunteersReg> existingVolunteers = new ArrayList<>();
        File file = new File("RegisterVol.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        existingVolunteers.add((VolunteersReg) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new IOException("Error reading existing data");
            }
        }

        existingVolunteers.add(volunteer);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (VolunteersReg v : existingVolunteers) {
                oos.writeObject(v);
            }
        }
    }

    public static List<VolunteersReg> loadFromFile() throws IOException, ClassNotFoundException {
        List<VolunteersReg> volunteers = new ArrayList<>();
        File file = new File("RegisterVol.bin");

        if (!file.exists()) {
            return volunteers;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    volunteers.add((VolunteersReg) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return volunteers;
    }
}