package com.example.bangladeshredcrescentsociety.Ripa_controllerClasses;

import com.example.bangladeshredcrescentsociety.Ripa_modelClasses.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class Volunteer_VolunteerManagementController {
    @FXML
    private BorderPane borderpane;
    private User user;

    public void initData(User user) {
        this.user = user;
    }

    @FXML
    private void handleSubmitRegistration() {
        // Implementation for submitting volunteer registration details
    }

    @FXML
    private void handleBackToDashboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Ripa/Volunteer_Dashboard.fxml"));
        Parent root = loader.load();
        Volunteer_DashboardController controller = loader.getController();
        controller.initData(user);
        borderpane.getScene().setRoot(root);
    }
}