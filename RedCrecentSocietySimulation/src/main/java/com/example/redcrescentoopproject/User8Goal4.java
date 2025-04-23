package com.example.redcrescentoopproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;

public class User8Goal4 {
    @FXML private TableView<HealthCamps> campsTableView;
    @FXML private TableColumn<HealthCamps, String> campIdTableCol;
    @FXML private TableColumn<HealthCamps, String> campNameTableCol;
    @FXML private TableColumn<HealthCamps, String> locationTableCol;
    @FXML private TableColumn<HealthCamps, LocalDate> startDateTableCol;
    @FXML private TableColumn<HealthCamps, LocalDate> endDateTableCol;
    @FXML private TableColumn<HealthCamps, String> servicesTableCol;
    @FXML private TableColumn<HealthCamps, String> statusTableCol;

    @FXML private TextField campIdTextField;
    @FXML private TextField campNameTextField;
    @FXML private TextField locationTextField;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private ComboBox<String> servicesComboBox;
    @FXML private ComboBox<String> teamLeaderComboBox;
    @FXML private TextField participantsTextField;

    private ObservableList<HealthCamps> campsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        campIdTableCol.setCellValueFactory(new PropertyValueFactory<>("campId"));
        campNameTableCol.setCellValueFactory(new PropertyValueFactory<>("campName"));
        locationTableCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        startDateTableCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateTableCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        servicesTableCol.setCellValueFactory(new PropertyValueFactory<>("services"));
        statusTableCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        servicesComboBox.getItems().addAll(
                "General Checkup",
                "Vaccination",
                "Blood Test",
                "Eye Checkup",
                "Dental Care",
                "Mother & Child Care",
                "First Aid Training"
        );

        teamLeaderComboBox.getItems().addAll(
                "Dr. Rahman",
                "Dr. Khan",
                "Dr. Chowdhury",
                "Dr. Ahmed",
                "Dr. Fatima"
        );

        try {
            campsList.addAll(HealthCamps.loadFromFile());
            campsTableView.setItems(campsList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading data: " + e.getMessage()).show();
        }
    }

    @FXML
    public void scheduleCampButtonOnClick(ActionEvent event) {
        try {
            HealthCamps newCamp = new HealthCamps(
                    campIdTextField.getText(),
                    campNameTextField.getText(),
                    locationTextField.getText(),
                    startDatePicker.getValue(),
                    endDatePicker.getValue(),
                    servicesComboBox.getValue(),
                    teamLeaderComboBox.getValue(),
                    Integer.parseInt(participantsTextField.getText()),
                    "Scheduled"
            );

            HealthCamps.saveToFile(newCamp);
            campsList.add(newCamp);

            clearFields();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    public void cancelCampButtonOnClick(ActionEvent event) {
        HealthCamps selected = campsTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus("Cancelled");
            try {
                HealthCamps.saveAllToFile(campsList);
                campsTableView.refresh();
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Error saving changes: " + e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a camp to cancel").show();
        }
    }

    private void clearFields() {
        campIdTextField.clear();
        campNameTextField.clear();
        locationTextField.clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        servicesComboBox.getSelectionModel().clearSelection();
        teamLeaderComboBox.getSelectionModel().clearSelection();
        participantsTextField.clear();
    }
}