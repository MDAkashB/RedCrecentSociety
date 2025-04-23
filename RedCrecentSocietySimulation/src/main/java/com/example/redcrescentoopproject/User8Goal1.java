package com.example.redcrescentoopproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class User8Goal1 {
    @FXML private TableView<PatientReg> patientsTableView;
    @FXML private TableColumn<PatientReg, String> patientIdTableCol;
    @FXML private TableColumn<PatientReg, String> fullNameTableCol;
    @FXML private TableColumn<PatientReg, Integer> ageTableCol;
    @FXML private TableColumn<PatientReg, String> genderTableCol;
    @FXML private TableColumn<PatientReg, String> bloodGroupTableCol;
    @FXML private TableColumn<PatientReg, String> contactTableCol;
    @FXML private TableColumn<PatientReg, LocalDate> regDateTableCol;

    @FXML private TextField patientIdTextField;
    @FXML private TextField fullNameTextField;
    @FXML private TextField ageTextField;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private ComboBox<String> bloodGroupComboBox;
    @FXML private TextField contactNumberTextField;
    @FXML private TextArea medicalConditionTextArea;
    @FXML private DatePicker registrationDatePicker;

    private ObservableList<PatientReg> patientsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        patientIdTableCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        fullNameTableCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        ageTableCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        genderTableCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        bloodGroupTableCol.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));
        contactTableCol.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        regDateTableCol.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        genderComboBox.getItems().addAll("Male", "Female", "Other");
        bloodGroupComboBox.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");

        try {
            patientsList.addAll(PatientReg.loadFromFile());
            patientsTableView.setItems(patientsList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading data: " + e.getMessage()).show();
        }
    }

    @FXML
    public void registerButtonOnClick(ActionEvent event) {
        try {
            PatientReg newPatient = new PatientReg(
                    patientIdTextField.getText(),
                    fullNameTextField.getText(),
                    Integer.parseInt(ageTextField.getText()),
                    genderComboBox.getValue(),
                    bloodGroupComboBox.getValue(),
                    contactNumberTextField.getText(),
                    medicalConditionTextArea.getText(),
                    registrationDatePicker.getValue()
            );

            PatientReg.saveToFile(newPatient);
            patientsList.add(newPatient);

            patientIdTextField.clear();
            fullNameTextField.clear();
            ageTextField.clear();
            genderComboBox.getSelectionModel().clearSelection();
            bloodGroupComboBox.getSelectionModel().clearSelection();
            contactNumberTextField.clear();
            medicalConditionTextArea.clear();
            registrationDatePicker.setValue(null);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}