package com.example.bangladeshredcrescentsociety.Ripa_controllerClasses;

import com.example.bangladeshredcrescentsociety.Ripa_modelClasses.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Organizer_DisasterResponseController {
    private User user;
    private ObservableList<VolunteerAssignment> volunteerAssignments;
    private List<DisasterAlert> disasterAlerts;
    private List<ReliefDistribution> reliefDistributions;
    private List<ResponseAssessment> responseAssessments;
    private String coordinationNotes;

    @FXML
    private TextArea weatherAlertsTextArea;
    @FXML
    private TableView<VolunteerAssignment> volunteerTable;
    @FXML
    private TableColumn<VolunteerAssignment, String> volunteerNameColumn;
    @FXML
    private TableColumn<VolunteerAssignment, String> assignedRoleColumn;
    @FXML
    private TableColumn<VolunteerAssignment, String> statusColumn;
    @FXML
    private TextArea coordinationNotesTextArea;

    @FXML
    public void initialize() {
        // Initialize lists
        volunteerAssignments = FXCollections.observableArrayList();
        disasterAlerts = new ArrayList<>();
        reliefDistributions = new ArrayList<>();
        responseAssessments = new ArrayList<>();
        coordinationNotes = "";

        // Set up table columns
        volunteerNameColumn.setCellValueFactory(cellData -> {
            VolunteerAssignment assignment = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(assignment.getVolunteer().getName());
        });
        assignedRoleColumn.setCellValueFactory(new PropertyValueFactory<>("assignedRole"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load existing data
        loadData();
    }

    private void loadData() {
        try {
            // Load disaster alerts
            File alertFile = new File("DisasterAlerts.bin");
            if (alertFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(alertFile));
                while (true) {
                    try {
                        disasterAlerts.add((DisasterAlert) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            }

            // Load volunteer assignments
            File assignmentFile = new File("VolunteerAssignments.bin");
            if (assignmentFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(assignmentFile));
                while (true) {
                    try {
                        volunteerAssignments.add((VolunteerAssignment) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            }
            volunteerTable.setItems(volunteerAssignments);

            // Load coordination notes
            File notesFile = new File("CoordinationNotes.bin");
            if (notesFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(notesFile));
                coordinationNotes = (String) ois.readObject();
                coordinationNotesTextArea.setText(coordinationNotes);
                ois.close();
            }

            // Update weather alerts display
            updateWeatherAlertsDisplay();

        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Failed to load data: " + e.getMessage());
        }
    }

    private void updateWeatherAlertsDisplay() {
        StringBuilder alertsText = new StringBuilder();
        for (DisasterAlert alert : disasterAlerts) {
            alertsText.append(alert.toString()).append("\n\n");
        }
        weatherAlertsTextArea.setText(alertsText.toString());
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        try {
            // Save disaster alerts
            File alertFile = new File("DisasterAlerts.bin");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(alertFile));
            for (DisasterAlert alert : disasterAlerts) {
                oos.writeObject(alert);
            }
            oos.close();

            // Save volunteer assignments
            File assignmentFile = new File("VolunteerAssignments.bin");
            oos = new ObjectOutputStream(new FileOutputStream(assignmentFile));
            for (VolunteerAssignment assignment : volunteerAssignments) {
                oos.writeObject(assignment);
            }
            oos.close();

            // Save coordination notes
            File notesFile = new File("CoordinationNotes.bin");
            oos = new ObjectOutputStream(new FileOutputStream(notesFile));
            coordinationNotes = coordinationNotesTextArea.getText();
            oos.writeObject(coordinationNotes);
            oos.close();

            showAlert("Success", "Data saved successfully!");
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    @FXML
    public void handleAddAlert(ActionEvent actionEvent) {
        Dialog<DisasterAlert> dialog = new Dialog<>();
        dialog.setTitle("Add Disaster Alert");
        dialog.setHeaderText("Enter alert details");

        // Set the button types
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create the form
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextField alertTypeField = new TextField();
        TextArea descriptionField = new TextArea();
        descriptionField.setPrefRowCount(3);
        ComboBox<String> severityCombo = new ComboBox<>();
        severityCombo.getItems().addAll("Low", "Medium", "High", "Critical");
        TextField locationField = new TextField();

        grid.add(new Label("Alert Type:"), 0, 0);
        grid.add(alertTypeField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(new Label("Severity:"), 0, 2);
        grid.add(severityCombo, 1, 2);
        grid.add(new Label("Location:"), 0, 3);
        grid.add(locationField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a DisasterAlert when the save button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new DisasterAlert(
                        alertTypeField.getText(),
                        descriptionField.getText(),
                        severityCombo.getValue(),
                        locationField.getText());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(alert -> {
            disasterAlerts.add(alert);
            updateWeatherAlertsDisplay();
        });
    }

    @FXML
    public void handleAddAssignment(ActionEvent actionEvent) {
        Dialog<VolunteerAssignment> dialog = new Dialog<>();
        dialog.setTitle("Add Volunteer Assignment");
        dialog.setHeaderText("Assign a volunteer to a role");

        // Set the button types
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create the form
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        ComboBox<Volunteer> volunteerCombo = new ComboBox<>();
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

        ComboBox<String> roleCombo = new ComboBox<>();
        roleCombo.getItems().addAll("First Aid", "Logistics", "Food Distribution", "Medical Support",
                "Shelter Management");

        TextField locationField = new TextField();

        grid.add(new Label("Volunteer:"), 0, 0);
        grid.add(volunteerCombo, 1, 0);
        grid.add(new Label("Role:"), 0, 1);
        grid.add(roleCombo, 1, 1);
        grid.add(new Label("Location:"), 0, 2);
        grid.add(locationField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a VolunteerAssignment when the save button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new VolunteerAssignment(
                        volunteerCombo.getValue(),
                        roleCombo.getValue(),
                        locationField.getText());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(assignment -> {
            volunteerAssignments.add(assignment);
            volunteerTable.setItems(volunteerAssignments);
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

class AppendableObjectOutputStream extends ObjectOutputStream {
    public AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // Do not write a header
    }
}