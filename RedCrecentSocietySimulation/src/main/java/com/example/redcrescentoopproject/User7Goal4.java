package com.example.redcrescentoopproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class User7Goal4 {
    @FXML private TableView<Performance> performanceTableView;
    @FXML private TableColumn<Performance, String> volunteerIdTableCol;
    @FXML private TableColumn<Performance, String> moduleIdTableCol;
    @FXML private TableColumn<Performance, LocalDate> assessmentDateTableCol;
    @FXML private TableColumn<Performance, Integer> performanceScoreTableCol;
    @FXML private TableColumn<Performance, Integer> attendanceRateTableCol;
    @FXML private TableColumn<Performance, String> feedbackTableCol;

    @FXML private TextField volunteerIdTextField;
    @FXML private TextField moduleIdTextField;
    @FXML private DatePicker assessmentDatePicker;
    @FXML private TextField performanceScoreTextField;
    @FXML private TextField attendanceRateTextField;
    @FXML private TextArea feedbackTextArea;

    private ObservableList<Performance> performanceList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        volunteerIdTableCol.setCellValueFactory(new PropertyValueFactory<>("volunteerId"));
        moduleIdTableCol.setCellValueFactory(new PropertyValueFactory<>("moduleId"));
        assessmentDateTableCol.setCellValueFactory(new PropertyValueFactory<>("assessmentDate"));
        performanceScoreTableCol.setCellValueFactory(new PropertyValueFactory<>("performanceScore"));
        attendanceRateTableCol.setCellValueFactory(new PropertyValueFactory<>("attendanceRate"));
        feedbackTableCol.setCellValueFactory(new PropertyValueFactory<>("feedback"));

        try {
            performanceList.addAll(Performance.loadFromFile());
            performanceTableView.setItems(performanceList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading data: " + e.getMessage()).show();
        }
    }

    @FXML
    public void recordButtonOnClick(ActionEvent event) {
        try {
            Performance newPerformance = new Performance(
                    volunteerIdTextField.getText(),
                    moduleIdTextField.getText(),
                    assessmentDatePicker.getValue(),
                    Integer.parseInt(performanceScoreTextField.getText()),
                    Integer.parseInt(attendanceRateTextField.getText()),
                    feedbackTextArea.getText()
            );

            Performance.saveToFile(newPerformance);
            performanceList.add(newPerformance);

            volunteerIdTextField.clear();
            moduleIdTextField.clear();
            assessmentDatePicker.setValue(null);
            performanceScoreTextField.clear();
            attendanceRateTextField.clear();
            feedbackTextArea.clear();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}