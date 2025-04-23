package com.example.redcrescentoopproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;

public class User8Goal3 {
    @FXML private TableView<Supply> supplyTableView;
    @FXML private TableColumn<Supply, String> requestIdTableCol;
    @FXML private TableColumn<Supply, String> itemNameTableCol;
    @FXML private TableColumn<Supply, String> categoryTableCol;
    @FXML private TableColumn<Supply, Integer> quantityTableCol;
    @FXML private TableColumn<Supply, String> urgencyTableCol;
    @FXML private TableColumn<Supply, LocalDate> requestDateTableCol;
    @FXML private TableColumn<Supply, String> statusTableCol;

    @FXML private TextField requestIdTextField;
    @FXML private TextField itemNameTextField;
    @FXML private ComboBox<String> categoryComboBox;
    @FXML private TextField quantityTextField;
    @FXML private ComboBox<String> urgencyComboBox;
    @FXML private DatePicker requestDatePicker;
    @FXML private TextField requestedByTextField;
    @FXML private TextArea purposeTextArea;

    private ObservableList<Supply> supplyList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        requestIdTableCol.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        itemNameTableCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryTableCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        quantityTableCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        urgencyTableCol.setCellValueFactory(new PropertyValueFactory<>("urgency"));
        requestDateTableCol.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
        statusTableCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        categoryComboBox.getItems().addAll(
                "Medicines",
                "Surgical Items",
                "First Aid Kits",
                "Diagnostic Equipment",
                "PPE Kits",
                "Others"
        );

        urgencyComboBox.getItems().addAll("Emergency", "High", "Normal");
        requestDatePicker.setValue(LocalDate.now());

        try {
            supplyList.addAll(Supply.loadFromFile());
            supplyTableView.setItems(supplyList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading data: " + e.getMessage()).show();
        }
    }

    @FXML
    public void submitRequestButtonOnClick(ActionEvent event) {
        try {
            Supply newSupply = new Supply(
                    requestIdTextField.getText(),
                    itemNameTextField.getText(),
                    categoryComboBox.getValue(),
                    Integer.parseInt(quantityTextField.getText()),
                    urgencyComboBox.getValue(),
                    requestDatePicker.getValue(),
                    requestedByTextField.getText(),
                    purposeTextArea.getText(),
                    "Pending"
            );

            Supply.saveToFile(newSupply);
            supplyList.add(newSupply);

            clearFields();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    public void updateStatusButtonOnClick(ActionEvent event) {
        Supply selected = supplyTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Approved", "Approved", "Rejected", "In Process");
            dialog.setTitle("Update Status");
            dialog.setHeaderText("Change request status for: " + selected.getItemName());
            dialog.setContentText("Select new status:");

            dialog.showAndWait().ifPresent(newStatus -> {
                selected.setStatus(newStatus);
                try {
                    Supply.saveAllToFile(supplyList);
                    supplyTableView.refresh();
                } catch (IOException e) {
                    new Alert(Alert.AlertType.ERROR, "Error saving changes: " + e.getMessage()).show();
                }
            });
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a request to update").show();
        }
    }

    private void clearFields() {
        requestIdTextField.clear();
        itemNameTextField.clear();
        categoryComboBox.getSelectionModel().clearSelection();
        quantityTextField.clear();
        urgencyComboBox.getSelectionModel().clearSelection();
        requestedByTextField.clear();
        purposeTextArea.clear();
    }
}