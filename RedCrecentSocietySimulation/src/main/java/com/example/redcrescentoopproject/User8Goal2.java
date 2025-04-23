package com.example.redcrescentoopproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class User8Goal2 {
    @FXML private TableView<DoctorAssign> assignmentsTableView;
    @FXML private TableColumn<DoctorAssign, String> patientIdTableCol;
    @FXML private TableColumn<DoctorAssign, String> patientNameTableCol;
    @FXML private TableColumn<DoctorAssign, String> doctorTableCol;
    @FXML private TableColumn<DoctorAssign, String> specialtyTableCol;
    @FXML private TableColumn<DoctorAssign, String> priorityTableCol;
    @FXML private TableColumn<DoctorAssign, LocalDate> dateTableCol;

    @FXML private TextField patientIdTextField;
    @FXML private TextField patientNameTextField;
    @FXML private TextArea medicalConditionTextArea;
    @FXML private ComboBox<String> doctorComboBox;
    @FXML private TextField specialtyTextField;
    @FXML private DatePicker assignmentDatePicker;
    @FXML private ComboBox<String> priorityComboBox;
    @FXML private TextArea notesTextArea;

    private ObservableList<DoctorAssign> assignmentsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        patientIdTableCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        patientNameTableCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        doctorTableCol.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        specialtyTableCol.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        priorityTableCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        dateTableCol.setCellValueFactory(new PropertyValueFactory<>("assignmentDate"));

        doctorComboBox.getItems().addAll(
                "Dr. Ahmed Khan",
                "Dr. Fatima Rahman",
                "Dr. Mohammad Ali",
                "Dr. Ayesha Chowdhury",
                "Dr. Jamal Uddin"
        );

        priorityComboBox.getItems().addAll("Critical", "High", "Medium", "Low");

        doctorComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                specialtyTextField.setText(getDoctorSpecialty(newVal));
            }
        });

        try {
            assignmentsList.addAll(DoctorAssign.loadFromFile());
            assignmentsTableView.setItems(assignmentsList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading data: " + e.getMessage()).show();
        }
    }

    private String getDoctorSpecialty(String doctorName) {
        switch(doctorName) {
            case "Dr. Ahmed Khan": return "Cardiology";
            case "Dr. Fatima Rahman": return "Neurology";
            case "Dr. Mohammad Ali": return "Orthopedics";
            case "Dr. Ayesha Chowdhury": return "Pediatrics";
            case "Dr. Jamal Uddin": return "General Surgery";
            default: return "";
        }
    }

    @FXML
    public void assignButtonOnClick(ActionEvent event) {
        try {
            DoctorAssign newAssignment = new DoctorAssign(
                    patientIdTextField.getText(),
                    patientNameTextField.getText(),
                    medicalConditionTextArea.getText(),
                    doctorComboBox.getValue(),
                    specialtyTextField.getText(),
                    priorityComboBox.getValue(),
                    assignmentDatePicker.getValue(),
                    notesTextArea.getText()
            );

            DoctorAssign.saveToFile(newAssignment);
            assignmentsList.add(newAssignment);

            patientIdTextField.clear();
            patientNameTextField.clear();
            medicalConditionTextArea.clear();
            doctorComboBox.getSelectionModel().clearSelection();
            specialtyTextField.clear();
            priorityComboBox.getSelectionModel().clearSelection();
            assignmentDatePicker.setValue(null);
            notesTextArea.clear();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}