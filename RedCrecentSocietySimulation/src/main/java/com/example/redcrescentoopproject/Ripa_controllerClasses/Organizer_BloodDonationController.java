package com.example.bangladeshredcrescentsociety.Ripa_controllerClasses;

import com.example.bangladeshredcrescentsociety.Ripa_modelClasses.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Organizer_BloodDonationController {
    private User user;
    private ObservableList<BloodDonationDrive> drives;
    private ObservableList<VolunteerAssignment> volunteerAssignments;
    private ObservableList<String> donorFollowUps;
    private BloodDonationDrive currentDrive;

    @FXML
    private TableView<BloodDonationDrive> driveTable;
    @FXML
    private TableColumn<BloodDonationDrive, String> driveNameColumn;
    @FXML
    private TableColumn<BloodDonationDrive, String> locationColumn;
    @FXML
    private TableColumn<BloodDonationDrive, String> startTimeColumn;
    @FXML
    private TableColumn<BloodDonationDrive, String> statusColumn;

    @FXML
    private TableView<VolunteerAssignment> volunteerTable;
    @FXML
    private TableColumn<VolunteerAssignment, String> volunteerNameColumn;
    @FXML
    private TableColumn<VolunteerAssignment, String> roleColumn;
    @FXML
    private TableColumn<VolunteerAssignment, String> assignmentStatusColumn;
    @FXML
    private TableColumn<VolunteerAssignment, String> driveColumn;

    @FXML
    private TextArea promotionTextArea;
    @FXML
    private TextArea safetyTextArea;

    @FXML
    private TableView<String> followUpTable;
    @FXML
    private TableColumn<String, String> donorNameColumn;
    @FXML
    private TableColumn<String, String> followUpDateColumn;
    @FXML
    private TableColumn<String, String> followUpStatusColumn;
    @FXML
    private TableColumn<String, String> followUpNotesColumn;

    @FXML
    public void initialize() {
        // Initialize lists
        drives = FXCollections.observableArrayList();
        volunteerAssignments = FXCollections.observableArrayList();
        donorFollowUps = FXCollections.observableArrayList();

        // Set up drive table columns
        driveNameColumn.setCellValueFactory(cellData -> {
            BloodDonationDrive drive = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(drive.getDriveName());
        });

        locationColumn.setCellValueFactory(cellData -> {
            BloodDonationDrive drive = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(drive.getLocation());
        });

        startTimeColumn.setCellValueFactory(cellData -> {
            BloodDonationDrive drive = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(
                    drive.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        });

        statusColumn.setCellValueFactory(cellData -> {
            BloodDonationDrive drive = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(drive.getStatus());
        });

        // Set up volunteer table columns
        volunteerNameColumn.setCellValueFactory(cellData -> {
            VolunteerAssignment assignment = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(assignment.getVolunteer().getName());
        });
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("assignedRole"));
        assignmentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        driveColumn.setCellValueFactory(cellData -> {
            VolunteerAssignment assignment = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(assignment.getLocation());
        });

        // Set up follow-up table columns
        donorNameColumn.setCellValueFactory(cellData -> {
            String followUp = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(followUp.split("\\|")[0]);
        });
        followUpDateColumn.setCellValueFactory(cellData -> {
            String followUp = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(followUp.split("\\|")[1]);
        });
        followUpStatusColumn.setCellValueFactory(cellData -> {
            String followUp = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(followUp.split("\\|")[2]);
        });
        followUpNotesColumn.setCellValueFactory(cellData -> {
            String followUp = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(followUp.split("\\|")[3]);
        });

        // Load existing data
        loadData();

        // Set up table selection listeners
        driveTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                currentDrive = newSelection;
                updateDriveDetails();
            }
        });
    }

    private void loadData() {
        try {
            // Load drives
            File driveFile = new File("BloodDonationDrives.bin");
            if (driveFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(driveFile));
                drives.clear(); // Clear existing items before loading
                while (true) {
                    try {
                        BloodDonationDrive drive = (BloodDonationDrive) ois.readObject();
                        drives.add(drive);
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            }
            driveTable.setItems(drives);
            driveTable.refresh(); // Force table refresh

            // Load volunteer assignments
            File assignmentFile = new File("BloodDonationAssignments.bin");
            if (assignmentFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(assignmentFile));
                while (true) {
                    try {
                        volunteerAssignments.add((VolunteerAssignment) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            }
            volunteerTable.setItems(volunteerAssignments);

            // Load follow-ups
            File followUpFile = new File("DonorFollowUps.bin");
            if (followUpFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(followUpFile));
                while (true) {
                    try {
                        donorFollowUps.add((String) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            }
            followUpTable.setItems(donorFollowUps);

        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Failed to load data: " + e.getMessage());
        }
    }

    private void updateDriveDetails() {
        if (currentDrive != null) {
            promotionTextArea.setText(currentDrive.getPromotionDetails());
            safetyTextArea.setText(currentDrive.getSafetyMeasures());
        }
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        try {
            // Save drives
            File driveFile = new File("BloodDonationDrives.bin");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(driveFile));
            for (BloodDonationDrive drive : drives) {
                oos.writeObject(drive);
            }
            oos.close();

            // Save volunteer assignments
            File assignmentFile = new File("BloodDonationAssignments.bin");
            oos = new ObjectOutputStream(new FileOutputStream(assignmentFile));
            for (VolunteerAssignment assignment : volunteerAssignments) {
                oos.writeObject(assignment);
            }
            oos.close();

            // Save follow-ups
            File followUpFile = new File("DonorFollowUps.bin");
            oos = new ObjectOutputStream(new FileOutputStream(followUpFile));
            for (String followUp : donorFollowUps) {
                oos.writeObject(followUp);
            }
            oos.close();

            showAlert("Success", "Data saved successfully!");
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    @FXML
    public void handleAddDrive(ActionEvent actionEvent) {
        Dialog<BloodDonationDrive> dialog = new Dialog<>();
        dialog.setTitle("Add Blood Donation Drive");
        dialog.setHeaderText("Enter drive details");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextField driveNameField = new TextField();
        driveNameField.setPromptText("Example: Annual Blood Donation Camp 2024");

        TextField locationField = new TextField();
        locationField.setPromptText("Example: Dhaka Medical College Auditorium");

        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Select drive date");

        TextField startTimeField = new TextField();
        startTimeField.setPromptText("Example: 09:00");

        TextField targetDonorsField = new TextField();
        targetDonorsField.setPromptText("Example: 100");

        grid.add(new Label("Drive Name:"), 0, 0);
        grid.add(driveNameField, 1, 0);
        grid.add(new Label("Location:"), 0, 1);
        grid.add(locationField, 1, 1);
        grid.add(new Label("Start Date:"), 0, 2);
        grid.add(startDatePicker, 1, 2);
        grid.add(new Label("Start Time (HH:mm):"), 0, 3);
        grid.add(startTimeField, 1, 3);
        grid.add(new Label("Target Donors:"), 0, 4);
        grid.add(targetDonorsField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    LocalDateTime startTime = LocalDateTime.of(
                            startDatePicker.getValue(),
                            java.time.LocalTime.parse(startTimeField.getText()));
                    int targetDonors = Integer.parseInt(targetDonorsField.getText());
                    return new BloodDonationDrive(
                            driveNameField.getText(),
                            locationField.getText(),
                            startTime,
                            startTime.plusHours(8), // Assuming 8-hour drive
                            targetDonors);
                } catch (Exception e) {
                    showAlert("Error", "Invalid input: " + e.getMessage());
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(drive -> {
            drives.add(drive);
            driveTable.setItems(drives);
        });
    }

    @FXML
    public void handleAddAssignment(ActionEvent actionEvent) {
        if (currentDrive == null) {
            showAlert("Error", "Please select a drive first");
            return;
        }

        Dialog<VolunteerAssignment> dialog = new Dialog<>();
        dialog.setTitle("Add Volunteer Assignment");
        dialog.setHeaderText("Assign a volunteer to the drive");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        ComboBox<Volunteer> volunteerCombo = new ComboBox<>();
        volunteerCombo.setPromptText("Select a volunteer");
        try {
            File volunteerFile = new File("Volunteers.bin");
            if (volunteerFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(volunteerFile));
                while (true) {
                    try {
                        Volunteer volunteer = (Volunteer) ois.readObject();
                        volunteerCombo.getItems().add(volunteer);
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Failed to load volunteers: " + e.getMessage());
        }

        ComboBox<String> roleCombo = new ComboBox<>();
        roleCombo.setPromptText("Select a role");
        roleCombo.getItems().addAll(
                "Registration - Handle donor check-in and forms",
                "Medical Screening - Conduct health checks",
                "Donation Assistant - Help during donation process",
                "Refreshment - Manage post-donation care",
                "Logistics - Handle supplies and setup");

        grid.add(new Label("Volunteer:"), 0, 0);
        grid.add(volunteerCombo, 1, 0);
        grid.add(new Label("Role:"), 0, 1);
        grid.add(roleCombo, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new VolunteerAssignment(
                        volunteerCombo.getValue(),
                        roleCombo.getValue(),
                        currentDrive.getDriveName());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(assignment -> {
            volunteerAssignments.add(assignment);
            currentDrive.addVolunteerAssignment(assignment);
            volunteerTable.setItems(volunteerAssignments);
        });
    }

    @FXML
    public void handleAddFollowUp(ActionEvent actionEvent) {
        if (currentDrive == null) {
            showAlert("Error", "Please select a drive first");
            return;
        }

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add Donor Follow-up");
        dialog.setHeaderText("Enter follow-up details");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextField donorNameField = new TextField();
        donorNameField.setPromptText("Example: John Doe");

        DatePicker followUpDatePicker = new DatePicker();
        followUpDatePicker.setPromptText("Select follow-up date");

        ComboBox<String> statusCombo = new ComboBox<>();
        statusCombo.setPromptText("Select follow-up status");
        statusCombo.getItems().addAll(
                "Scheduled - Follow-up planned",
                "Completed - Follow-up done",
                "Cancelled - Follow-up cancelled");

        TextArea notesArea = new TextArea();
        notesArea.setPrefRowCount(3);
        notesArea.setPromptText(
                "Example:\n- Donor feeling well\n- No adverse reactions\n- Eligible for next donation in 3 months");

        grid.add(new Label("Donor Name:"), 0, 0);
        grid.add(donorNameField, 1, 0);
        grid.add(new Label("Follow-up Date:"), 0, 1);
        grid.add(followUpDatePicker, 1, 1);
        grid.add(new Label("Status:"), 0, 2);
        grid.add(statusCombo, 1, 2);
        grid.add(new Label("Notes:"), 0, 3);
        grid.add(notesArea, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return donorNameField.getText() + "|" +
                        followUpDatePicker.getValue().toString() + "|" +
                        statusCombo.getValue() + "|" +
                        notesArea.getText();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(followUp -> {
            donorFollowUps.add(followUp);
            currentDrive.addDonorFollowUp(followUp);
            followUpTable.setItems(donorFollowUps);
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void initData(User user) {
        this.user = user;
    }
}