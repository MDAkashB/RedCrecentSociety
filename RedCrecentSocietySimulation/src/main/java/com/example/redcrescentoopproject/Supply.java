package com.example.redcrescentoopproject;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Supply implements Serializable {
    private String requestId;
    private String itemName;
    private String category;
    private int quantity;
    private String urgency;
    private LocalDate requestDate;
    private String requestedBy;
    private String purpose;
    private String status;

    public Supply(String requestId, String itemName, String category, int quantity,
                  String urgency, LocalDate requestDate, String requestedBy,
                  String purpose, String status) {
        this.requestId = requestId;
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
        this.urgency = urgency;
        this.requestDate = requestDate;
        this.requestedBy = requestedBy;
        this.purpose = purpose;
        this.status = status;
    }

    // Getters and Setters
    public String getRequestId() { return requestId; }
    public String getItemName() { return itemName; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public String getUrgency() { return urgency; }
    public LocalDate getRequestDate() { return requestDate; }
    public String getRequestedBy() { return requestedBy; }
    public String getPurpose() { return purpose; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return requestId + " | " + itemName + " | " + category + " | " + quantity;
    }

    public static void saveToFile(Supply supply) throws IOException {
        List<Supply> existingSupplies = new ArrayList<>();
        File file = new File("Supply.bin");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    try {
                        existingSupplies.add((Supply) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new IOException("Error reading existing data");
            }
        }

        existingSupplies.add(supply);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Supply s : existingSupplies) {
                oos.writeObject(s);
            }
        }
    }

    public static void saveAllToFile(List<Supply> supplies) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Supply.bin"))) {
            for (Supply s : supplies) {
                oos.writeObject(s);
            }
        }
    }

    public static List<Supply> loadFromFile() throws IOException, ClassNotFoundException {
        List<Supply> supplies = new ArrayList<>();
        File file = new File("Supply.bin");

        if (!file.exists()) {
            return supplies;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    supplies.add((Supply) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return supplies;
    }
}