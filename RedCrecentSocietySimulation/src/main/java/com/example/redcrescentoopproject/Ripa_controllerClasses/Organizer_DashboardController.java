package com.example.bangladeshredcrescentsociety.Ripa_controllerClasses;

import com.example.bangladeshredcrescentsociety.Ripa_modelClasses.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Organizer_DashboardController {

    User user;
    @javafx.fxml.FXML
    private BorderPane borderPane;

    @javafx.fxml.FXML
    public void initialize() {
    }

    public void initData(User user) {
        this.user = user;
    }

    @javafx.fxml.FXML
    public void openBloodDonation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Organizer_BloodDonation.fxml"));
        Parent root = loader.load();
        Organizer_BloodDonationController controller = loader.getController();
        controller.initData(user);
        borderPane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void openRefugeeAssistance(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Organizer_RefugeeAssistance.fxml"));
        Parent root = loader.load();
        Organizer_RefugeeAssistanceController controller = loader.getController();
        controller.initData(user);
        borderPane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void openVolunteerManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Organizer_VolunteerManagement.fxml"));
        Parent root = loader.load();
        Organizer_VolunteerManagementController controller = loader.getController();
        controller.initData(user);
        borderPane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void openDisasterResponse(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Organizer_DisasterResponse.fxml"));
        Parent root = loader.load();
        Organizer_DisasterResponseController controller = loader.getController();
        controller.initData(user);
        borderPane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void openFundraising(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Organizer_Fundraising.fxml"));
        Parent root = loader.load();
        Organizer_FundraisingController controller = loader.getController();
        controller.initData(user);
        borderPane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void openMedicalAssistance(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Organizer_MedicalAssistance.fxml"));
        Parent root = loader.load();
        Organizer_MedicalAssistanceController controller = loader.getController();
        controller.initData(user);
        borderPane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void openEnvironmentalHealth(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Organizer_EnvironmentalHealth.fxml"));
        Parent root = loader.load();
        Organizer_EnvironmentalHealthController controller = loader.getController();
        controller.initData(user);
        borderPane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void openCommunityTraining(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Organizer_CommunityTraining.fxml"));
        Parent root = loader.load();
        Organizer_CommunityTrainingController controller = loader.getController();
        controller.initData(user);
        borderPane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleLogOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent personViewParent = loader.load(getClass().getResource("Temporary_login.fxml"));
        Scene personViewScene = new Scene(personViewParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }
}