package com.example.bangladeshredcrescentsociety.Ripa_controllerClasses;

import com.example.bangladeshredcrescentsociety.Ripa_modelClasses.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Organizer_MedicalAssistanceController {
    private User user;
    private ObservableList<VolunteerAssignment> medicalTeam;
    private ObservableList<String> patientRecords;
    private MedicalAssistance currentAssistance;

    @FXML
    private TextArea needsAssessmentTextArea;
    @FXML
    private TextArea suppliesTextArea;
    @FXML
    private TableView<VolunteerAssignment> teamTable;
    @FXML
    private TableColumn<VolunteerAssignment, String> nameColumn;
    @FXML
    private TableColumn<VolunteerAssignment, String> specializationColumn;
    @FXML
    private TableColumn<VolunteerAssignment, String> statusColumn;
    @FXML
    private TableColumn<VolunteerAssignment, String> locationColumn;
    @FXML
    private TableView<String> recordsTable;
    @FXML
    private TableColumn<String, String> patientIdColumn;
    @FXML
    private TableColumn<String, String> conditionColumn;
    @FXML
    private TableColumn<String, String> treatmentColumn;
    @FXML
    private TableColumn<String, String> dateColumn;

    @FXML
    public void initialize() {
        // Initialize lists
        medicalTeam = FXCollections.observableArrayList();
        patientRecords = FXCollections.observableArrayList();

        // Set up team table columns
        nameColumn.setCellValueFactory(cellData -> {
            VolunteerAssignment assignment = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(assignment.getVolunteer().getName());
        });

        specializationColumn.setCellValueFactory(cellData -> {
            VolunteerAssignment assignment = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(assignment.getAssignedRole());
        });

        statusColumn.setCellValueFactory(cellData -> {
            VolunteerAssignment assignment = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(assignment.getStatus());
        });

        locationColumn.setCellValueFactory(cellData -> {
            VolunteerAssignment assignment = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(assignment.getLocation());
        });

        // Set up records table columns
        patientIdColumn.setCellValueFactory(cellData -> {
            String record = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(record.split("\\|")[0]);
        });

        conditionColumn.setCellValueFactory(cellData -> {
            String record = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(record.split("\\|")[1]);
        });

        treatmentColumn.setCellValueFactory(cellData -> {
            String record = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(record.split("\\|")[2]);
        });

        dateColumn.setCellValueFactory(cellData -> {
            String record = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(record.split("\\|")[3]);
        });

        // Load existing data
        loadData();
    }

    private void loadData() {
        try {
            // Load medical assistance
            File assistanceFile = new File("MedicalAssistance.bin");
            if (assistanceFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(assistanceFile));
                currentAssistance = (MedicalAssistance) ois.readObject();
                ois.close();

                needsAssessmentTextArea.setText(currentAssistance.getMedicalNeeds());
                suppliesTextArea.setText(currentAssistance.getMedicalSupplies());
                medicalTeam.setAll(currentAssistance.getMedicalTeam());
                patientRecords.setAll(currentAssistance.getPatientRecords());
            } else {
                currentAssistance = new MedicalAssistance("Main Hospital");
            }

            teamTable.setItems(medicalTeam);
            recordsTable.setItems(patientRecords);

        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Failed to load data: " + e.getMessage());
        }
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        try {
            currentAssistance.setMedicalNeeds(needsAssessmentTextArea.getText());
            currentAssistance.setMedicalSupplies(suppliesTextArea.getText());

            File assistanceFile = new File("MedicalAssistance.bin");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(assistanceFile));
            oos.writeObject(currentAssistance);
            oos.close();

            showAlert("Success", "Data saved successfully!");
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    @FXML
    public void handleAddTeamMember(ActionEvent actionEvent) {
        Dialog<VolunteerAssignment> dialog = new Dialog<>();
        dialog.setTitle("Add Medical Team Member");
        dialog.setHeaderText("Enter team member details");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        ComboBox<Volunteer> volunteerCombo = new ComboBox<>();
        volunteerCombo.setPromptText("Select a medical professional");
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

        ComboBox<String> specializationCombo = new ComboBox<>();
        specializationCombo.setPromptText("Select specialization");
        specializationCombo.getItems().addAll(
                "General Physician",
                "Surgeon",
                "Nurse",
                "Paramedic",
                "Pharmacist",
                "Medical Technician");

        grid.add(new Label("Medical Professional:"), 0, 0);
        grid.add(volunteerCombo, 1, 0);
        grid.add(new Label("Specialization:"), 0, 1);
        grid.add(specializationCombo, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new VolunteerAssignment(
                        volunteerCombo.getValue(),
                        specializationCombo.getValue(),
                        currentAssistance.getLocation());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(assignment -> {
            medicalTeam.add(assignment);
            currentAssistance.addMedicalTeamMember(assignment);
            teamTable.setItems(medicalTeam);
        });
    }

    @FXML
    public void handleAddRecord(ActionEvent actionEvent) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add Patient Record");
        dialog.setHeaderText("Enter patient details");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextField patientIdField = new TextField();
        patientIdField.setPromptText("Example: PAT-001");

        TextField conditionField = new TextField();
        conditionField.setPromptText("Example: Fractured arm");

        TextArea treatmentArea = new TextArea();
        treatmentArea.setPrefRowCount(3);
        treatmentArea.setPromptText("Example:\n- X-ray taken\n- Cast applied\n- Pain medication prescribed");

        grid.add(new Label("Patient ID:"), 0, 0);
        grid.add(patientIdField, 1, 0);
        grid.add(new Label("Condition:"), 0, 1);
        grid.add(conditionField, 1, 1);
        grid.add(new Label("Treatment:"), 0, 2);
        grid.add(treatmentArea, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return patientIdField.getText() + "|" +
                        conditionField.getText() + "|" +
                        treatmentArea.getText() + "|" +
                        LocalDate.now().toString();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(record -> {
            patientRecords.add(record);
            currentAssistance.addPatientRecord(record);
            recordsTable.setItems(patientRecords);
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