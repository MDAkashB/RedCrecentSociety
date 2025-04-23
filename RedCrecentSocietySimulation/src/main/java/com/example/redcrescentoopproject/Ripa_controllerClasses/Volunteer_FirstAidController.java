package com.example.bangladeshredcrescentsociety.Ripa_controllerClasses;

import com.example.bangladeshredcrescentsociety.Ripa_modelClasses.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class Volunteer_FirstAidController {
    @FXML
    private BorderPane borderpane;
    private User user;

    public void initData(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void handleSubmitTreatment(ActionEvent actionEvent) {
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