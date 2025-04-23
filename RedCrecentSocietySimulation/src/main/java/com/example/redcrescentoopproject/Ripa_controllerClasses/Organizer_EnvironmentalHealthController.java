package com.example.bangladeshredcrescentsociety.Ripa_controllerClasses;

import com.example.bangladeshredcrescentsociety.Ripa_modelClasses.EnvironmentalHealth;
import com.example.bangladeshredcrescentsociety.Ripa_modelClasses.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Organizer_EnvironmentalHealthController {
    @FXML
    private TextField campaignNameField;
    @FXML
    private TextField locationField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextArea progressTextArea;
    @FXML
    private TextArea impactTextArea;

    @FXML
    private TableView<EnvironmentalHealth> campaignTable;
    @FXML
    private TableColumn<EnvironmentalHealth, String> campaignNameColumn;
    @FXML
    private TableColumn<EnvironmentalHealth, String> locationColumn;
    @FXML
    private TableColumn<EnvironmentalHealth, LocalDate> dateColumn;
    @FXML
    private TableColumn<EnvironmentalHealth, String> statusColumn;

    @FXML
    private TableView<EnvironmentalHealth.VolunteerAssignment> volunteerTable;
    @FXML
    private TableColumn<EnvironmentalHealth.VolunteerAssignment, String> volunteerNameColumn;
    @FXML
    private TableColumn<EnvironmentalHealth.VolunteerAssignment, String> roleColumn;
    @FXML
    private TableColumn<EnvironmentalHealth.VolunteerAssignment, String> volunteerStatusColumn;
    @FXML
    private TableColumn<EnvironmentalHealth.VolunteerAssignment, String> volunteerLocationColumn;

    private ObservableList<EnvironmentalHealth> campaigns;
    private ObservableList<EnvironmentalHealth.VolunteerAssignment> volunteerAssignments;
    private final String DATA_FILE = "environmental_health_data.dat";
    private User user;

    public void initData(User u) {
        this.user = u;
    }

    @FXML
    public void initialize() {
        campaigns = FXCollections.observableArrayList();
        volunteerAssignments = FXCollections.observableArrayList();

        // Set up campaign table columns
        campaignNameColumn.setCellValueFactory(new PropertyValueFactory<>("campaignName"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Set up volunteer table columns
        volunteerNameColumn.setCellValueFactory(new PropertyValueFactory<>("volunteerName"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        volunteerStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        volunteerLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        // Set the items for the tables
        campaignTable.setItems(campaigns);
        volunteerTable.setItems(volunteerAssignments);

        // Load existing data
        loadData();

        // Set up table selection listeners
        campaignTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateDetails(newSelection);
            }
        });
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            List<EnvironmentalHealth> loadedCampaigns = (List<EnvironmentalHealth>) ois.readObject();
            campaigns.clear(); // Clear existing items
            campaigns.addAll(loadedCampaigns); // Add all loaded items
            campaignTable.refresh(); // Refresh the table
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, that's okay
        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Failed to load data: " + e.getMessage());
        }
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(new ArrayList<>(campaigns));
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    private void updateDetails(EnvironmentalHealth campaign) {
        campaignNameField.setText(campaign.getCampaignName());
        locationField.setText(campaign.getLocation());
        datePicker.setValue(campaign.getDate());
        descriptionTextArea.setText(campaign.getDescription());
        progressTextArea.setText(campaign.getProgress());
        impactTextArea.setText(campaign.getImpactAssessment());

        volunteerAssignments.setAll(campaign.getVolunteers());
        volunteerTable.setItems(volunteerAssignments);
    }

    @FXML
    private void handleAddCampaign() {
        if (campaignNameField.getText().isEmpty() || locationField.getText().isEmpty()
                || datePicker.getValue() == null) {
            showAlert("Error", "Please fill in all required fields");
            return;
        }

        EnvironmentalHealth newCampaign = new EnvironmentalHealth(campaignNameField.getText());
        newCampaign.setLocation(locationField.getText());
        newCampaign.setDate(datePicker.getValue());
        newCampaign.setDescription(descriptionTextArea.getText());
        newCampaign.setProgress(progressTextArea.getText());
        newCampaign.setImpactAssessment(impactTextArea.getText());
        newCampaign.setStatus("Planned");

        campaigns.add(newCampaign);
        campaignTable.refresh(); // Refresh the table to show new item
        saveData();
        clearInputFields();
    }

    @FXML
    private void handleAddVolunteer() {
        EnvironmentalHealth selectedCampaign = campaignTable.getSelectionModel().getSelectedItem();
        if (selectedCampaign == null) {
            showAlert("Error", "Please select a campaign first");
            return;
        }

        Dialog<EnvironmentalHealth.VolunteerAssignment> dialog = new Dialog<>();
        dialog.setTitle("Add Volunteer");
        dialog.setHeaderText("Enter Volunteer Details");

        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        TextField nameField = new TextField();
        TextField roleField = new TextField();
        TextField locationField = new TextField();

        dialog.getDialogPane().setContent(new VBox(10,
                new Label("Volunteer Name:"), nameField,
                new Label("Role:"), roleField,
                new Label("Location:"), locationField));

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return new EnvironmentalHealth.VolunteerAssignment(
                        nameField.getText(),
                        roleField.getText(),
                        locationField.getText());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(volunteer -> {
            selectedCampaign.getVolunteers().add(volunteer);
            updateDetails(selectedCampaign);
            saveData();
        });
    }

    @FXML
    private void handleSaveChanges() {
        EnvironmentalHealth selectedCampaign = campaignTable.getSelectionModel().getSelectedItem();
        if (selectedCampaign != null) {
            selectedCampaign.setDescription(descriptionTextArea.getText());
            selectedCampaign.setProgress(progressTextArea.getText());
            selectedCampaign.setImpactAssessment(impactTextArea.getText());
            saveData();
        }
    }

    private void clearInputFields() {
        campaignNameField.clear();
        locationField.clear();
        datePicker.setValue(null);
        descriptionTextArea.clear();
        progressTextArea.clear();
        impactTextArea.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}