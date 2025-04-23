package com.example.redcrescentoopproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class User7Goal1 {
    @FXML private TableView<VolunteersReg> volunteersTableView;
    @FXML private TableColumn<VolunteersReg, String> nameTableCol;
    @FXML private TableColumn<VolunteersReg, String> idTableCol;
    @FXML private TableColumn<VolunteersReg, String> bloodGroupTableCol;
    @FXML private TableColumn<VolunteersReg, LocalDate> joinDateTableCol;
    @FXML private TableColumn<VolunteersReg, String> contactTableCol;
    @FXML private TableColumn<VolunteersReg, String> skillsTableCol;

    @FXML private TextField nameTextField;
    @FXML private TextField idTextField;
    @FXML private ComboBox<String> bloodGroupComboBox;
    @FXML private DatePicker joinDatePicker;
    @FXML private TextField contactTextField;
    @FXML private TextArea skillsTextArea;

    private ObservableList<VolunteersReg> volunteersList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameTableCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idTableCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bloodGroupTableCol.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));
        joinDateTableCol.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        contactTableCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        skillsTableCol.setCellValueFactory(new PropertyValueFactory<>("skills"));

        bloodGroupComboBox.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");

        try {
            volunteersList.addAll(VolunteersReg.loadFromFile());
            volunteersTableView.setItems(volunteersList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading data: " + e.getMessage()).show();
        }
    }

    @FXML
    public void registerButtonOnClick(ActionEvent event) {
        try {
            VolunteersReg newVolunteer = new VolunteersReg(
                    nameTextField.getText(),
                    idTextField.getText(),
                    bloodGroupComboBox.getValue(),
                    joinDatePicker.getValue(),
                    contactTextField.getText(),
                    skillsTextArea.getText()
            );

            VolunteersReg.saveToFile(newVolunteer);
            volunteersList.add(newVolunteer);

            nameTextField.clear();
            idTextField.clear();
            bloodGroupComboBox.getSelectionModel().clearSelection();
            joinDatePicker.setValue(null);
            contactTextField.clear();
            skillsTextArea.clear();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}