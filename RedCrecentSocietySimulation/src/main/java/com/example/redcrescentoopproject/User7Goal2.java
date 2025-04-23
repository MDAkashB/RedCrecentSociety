package com.example.redcrescentoopproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class User7Goal2 {
    @FXML private TableView<Module> modulesTableView;
    @FXML private TableColumn<Module, String> volunteerIdTableCol;
    @FXML private TableColumn<Module, String> moduleNameTableCol;
    @FXML private TableColumn<Module, LocalDate> startDateTableCol;
    @FXML private TableColumn<Module, LocalDate> endDateTableCol;
    @FXML private TableColumn<Module, String> trainerNameTableCol;
    @FXML private TableColumn<Module, String> statusTableCol;

    @FXML private TextField volunteerIdTextField;
    @FXML private TextField moduleNameTextField;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private TextField trainerNameTextField;
    @FXML private ComboBox<String> statusComboBox;

    private ObservableList<Module> modulesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        volunteerIdTableCol.setCellValueFactory(new PropertyValueFactory<>("volunteerId"));
        moduleNameTableCol.setCellValueFactory(new PropertyValueFactory<>("moduleName"));
        startDateTableCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateTableCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        trainerNameTableCol.setCellValueFactory(new PropertyValueFactory<>("trainerName"));
        statusTableCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusComboBox.getItems().addAll("Pending", "In Progress", "Completed", "Cancelled");

        try {
            modulesList.addAll(Module.loadFromFile());
            modulesTableView.setItems(modulesList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading data: " + e.getMessage()).show();
        }
    }

    @FXML
    public void assignButtonOnClick(ActionEvent event) {
        try {
            Module newModule = new Module(
                    volunteerIdTextField.getText(),
                    moduleNameTextField.getText(),
                    startDatePicker.getValue(),
                    endDatePicker.getValue(),
                    trainerNameTextField.getText(),
                    statusComboBox.getValue()
            );

            Module.saveToFile(newModule);
            modulesList.add(newModule);

            volunteerIdTextField.clear();
            moduleNameTextField.clear();
            startDatePicker.setValue(null);
            endDatePicker.setValue(null);
            trainerNameTextField.clear();
            statusComboBox.getSelectionModel().clearSelection();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}