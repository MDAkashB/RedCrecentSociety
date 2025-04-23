package com.example.redcrescentoopproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class User7Goal3 {
    @FXML private TableView<Session> sessionsTableView;
    @FXML private TableColumn<Session, String> sessionNameTableCol;
    @FXML private TableColumn<Session, String> moduleIdTableCol;
    @FXML private TableColumn<Session, LocalDate> sessionDateTableCol;
    @FXML private TableColumn<Session, String> startTimeTableCol;
    @FXML private TableColumn<Session, String> endTimeTableCol;
    @FXML private TableColumn<Session, String> locationTableCol;

    @FXML private TextField sessionNameTextField;
    @FXML private TextField moduleIdTextField;
    @FXML private DatePicker sessionDatePicker;
    @FXML private TextField startTimeTextField;
    @FXML private TextField endTimeTextField;
    @FXML private TextField locationTextField;
    @FXML private TextArea notesTextArea;

    private ObservableList<Session> sessionsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        sessionNameTableCol.setCellValueFactory(new PropertyValueFactory<>("sessionName"));
        moduleIdTableCol.setCellValueFactory(new PropertyValueFactory<>("moduleId"));
        sessionDateTableCol.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        startTimeTableCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeTableCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        locationTableCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        try {
            sessionsList.addAll(Session.loadFromFile());
            sessionsTableView.setItems(sessionsList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading data: " + e.getMessage()).show();
        }
    }

    @FXML
    public void scheduleButtonOnClick(ActionEvent event) {
        try {
            Session newSession = new Session(
                    sessionNameTextField.getText(),
                    moduleIdTextField.getText(),
                    sessionDatePicker.getValue(),
                    startTimeTextField.getText(),
                    endTimeTextField.getText(),
                    locationTextField.getText(),
                    notesTextArea.getText()
            );

            Session.saveToFile(newSession);
            sessionsList.add(newSession);

            sessionNameTextField.clear();
            moduleIdTextField.clear();
            sessionDatePicker.setValue(null);
            startTimeTextField.clear();
            endTimeTextField.clear();
            locationTextField.clear();
            notesTextArea.clear();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}