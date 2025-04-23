package com.example.bangladeshredcrescentsociety.Ripa_controllerClasses;

import com.example.bangladeshredcrescentsociety.Ripa_modelClasses.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Organizer_CommunityTrainingController {
    private User user;
    private ObservableList<TrainingProgram> trainingPrograms;
    private ObservableList<String> feedbackList;
    private TrainingProgram currentTraining;

    @FXML
    private TextArea needsAssessmentTextArea;
    @FXML
    private TextArea materialsTextArea;
    @FXML
    private TableView<TrainingProgram> trainingTable;
    @FXML
    private TableColumn<TrainingProgram, String> topicColumn;
    @FXML
    private TableColumn<TrainingProgram, String> dateColumn;
    @FXML
    private TableColumn<TrainingProgram, String> trainerColumn;
    @FXML
    private TableColumn<TrainingProgram, String> locationColumn;
    @FXML
    private TableColumn<TrainingProgram, String> statusColumn;
    @FXML
    private TableView<String> feedbackTable;
    @FXML
    private TableColumn<String, String> trainingTopicColumn;
    @FXML
    private TableColumn<String, String> feedbackColumn;
    @FXML
    private TableColumn<String, String> feedbackDateColumn;

    @FXML
    public void initialize() {
        // Initialize lists
        trainingPrograms = FXCollections.observableArrayList();
        feedbackList = FXCollections.observableArrayList();

        // Set up training table columns
        topicColumn.setCellValueFactory(cellData -> {
            TrainingProgram program = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(program.getTopic());
        });

        dateColumn.setCellValueFactory(cellData -> {
            TrainingProgram program = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(
                    program.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        });

        trainerColumn.setCellValueFactory(cellData -> {
            TrainingProgram program = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(program.getTrainer());
        });

        locationColumn.setCellValueFactory(cellData -> {
            TrainingProgram program = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(program.getLocation());
        });

        statusColumn.setCellValueFactory(cellData -> {
            TrainingProgram program = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(program.getStatus());
        });

        // Set up feedback table columns
        trainingTopicColumn.setCellValueFactory(cellData -> {
            String feedback = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(feedback.split("\\|")[0]);
        });

        feedbackColumn.setCellValueFactory(cellData -> {
            String feedback = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(feedback.split("\\|")[1]);
        });

        feedbackDateColumn.setCellValueFactory(cellData -> {
            String feedback = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(feedback.split("\\|")[2]);
        });

        // Load existing data
        loadData();

        // Set up table selection listeners
        trainingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                currentTraining = newSelection;
                updateTrainingDetails();
            }
        });
    }

    private void loadData() {
        try {
            // Load training programs
            File trainingFile = new File("TrainingPrograms.bin");
            if (trainingFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(trainingFile));
                trainingPrograms.clear();
                while (true) {
                    try {
                        TrainingProgram program = (TrainingProgram) ois.readObject();
                        trainingPrograms.add(program);
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            }
            trainingTable.setItems(trainingPrograms);

            // Load needs assessment
            File needsFile = new File("TrainingNeeds.bin");
            if (needsFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(needsFile));
                needsAssessmentTextArea.setText((String) ois.readObject());
                ois.close();
            }

            // Load materials
            File materialsFile = new File("TrainingMaterials.bin");
            if (materialsFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(materialsFile));
                materialsTextArea.setText((String) ois.readObject());
                ois.close();
            }

            // Load feedback
            File feedbackFile = new File("TrainingFeedback.bin");
            if (feedbackFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(feedbackFile));
                feedbackList.clear();
                while (true) {
                    try {
                        feedbackList.add((String) ois.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
                ois.close();
            }
            feedbackTable.setItems(feedbackList);

        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Failed to load data: " + e.getMessage());
        }
    }

    private void updateTrainingDetails() {
        if (currentTraining != null) {
            materialsTextArea.setText(currentTraining.getMaterials());
        }
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        try {
            // Save training programs
            File trainingFile = new File("TrainingPrograms.bin");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(trainingFile));
            for (TrainingProgram program : trainingPrograms) {
                oos.writeObject(program);
            }
            oos.close();

            // Save needs assessment
            File needsFile = new File("TrainingNeeds.bin");
            oos = new ObjectOutputStream(new FileOutputStream(needsFile));
            oos.writeObject(needsAssessmentTextArea.getText());
            oos.close();

            // Save materials
            File materialsFile = new File("TrainingMaterials.bin");
            oos = new ObjectOutputStream(new FileOutputStream(materialsFile));
            oos.writeObject(materialsTextArea.getText());
            oos.close();

            // Save feedback
            File feedbackFile = new File("TrainingFeedback.bin");
            oos = new ObjectOutputStream(new FileOutputStream(feedbackFile));
            for (String feedback : feedbackList) {
                oos.writeObject(feedback);
            }
            oos.close();

            showAlert("Success", "Data saved successfully!");
        } catch (IOException e) {
            showAlert("Error", "Failed to save data: " + e.getMessage());
        }
    }

    @FXML
    public void handleAddTraining(ActionEvent actionEvent) {
        Dialog<TrainingProgram> dialog = new Dialog<>();
        dialog.setTitle("Add Training Program");
        dialog.setHeaderText("Enter training program details");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextField topicField = new TextField();
        topicField.setPromptText("Example: Basic First Aid Training");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select training date");

        TextField trainerField = new TextField();
        trainerField.setPromptText("Example: Dr. Sarah Johnson");

        TextField locationField = new TextField();
        locationField.setPromptText("Example: Community Center, Dhaka");

        grid.add(new Label("Topic:"), 0, 0);
        grid.add(topicField, 1, 0);
        grid.add(new Label("Date:"), 0, 1);
        grid.add(datePicker, 1, 1);
        grid.add(new Label("Trainer:"), 0, 2);
        grid.add(trainerField, 1, 2);
        grid.add(new Label("Location:"), 0, 3);
        grid.add(locationField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new TrainingProgram(
                        topicField.getText(),
                        datePicker.getValue(),
                        trainerField.getText(),
                        locationField.getText());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(program -> {
            trainingPrograms.add(program);
            trainingTable.setItems(trainingPrograms);
        });
    }

    @FXML
    public void handleAddFeedback(ActionEvent actionEvent) {
        if (currentTraining == null) {
            showAlert("Error", "Please select a training program first");
            return;
        }

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add Feedback");
        dialog.setHeaderText("Enter feedback for " + currentTraining.getTopic());

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextArea feedbackArea = new TextArea();
        feedbackArea.setPrefRowCount(3);
        feedbackArea.setPromptText(
                "Example:\n- Training content was very informative\n- Practical sessions were helpful\n- Would like more hands-on practice\n- Trainer was knowledgeable and engaging");

        grid.add(new Label("Feedback:"), 0, 0);
        grid.add(feedbackArea, 1, 0);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return currentTraining.getTopic() + "|" +
                        feedbackArea.getText() + "|" +
                        LocalDate.now().toString();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(feedback -> {
            feedbackList.add(feedback);
            currentTraining.addFeedback(feedback);
            feedbackTable.setItems(feedbackList);
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