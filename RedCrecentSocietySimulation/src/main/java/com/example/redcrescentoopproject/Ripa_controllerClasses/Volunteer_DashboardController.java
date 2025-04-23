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

public class Volunteer_DashboardController {
    @javafx.fxml.FXML
    private BorderPane borderpane;

    User user;

    public void initData(User user) {
        this.user = user;
    }

    @javafx.fxml.FXML
    public void initialize() {
    }


    @javafx.fxml.FXML
    public void handleVolunteerManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Volunteer_VolunteerManagement.fxml"));
        Parent root = loader.load();
        Volunteer_VolunteerManagementController controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleCommunityHealth(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Volunteer_CommunityHealth.fxml"));
        Parent root = loader.load();
        Volunteer_CommunityHealthController controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleBloodDonation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Volunteer_BloodDonation.fxml"));
        Parent root = loader.load();
        Volunteer_BloodDonationController controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleDisasterResponse(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Volunteer_DisasterResponse.fxml"));
        Parent root = loader.load();
        Volunteer_DisasterResponseController controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleFundraising(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Volunteer_Fundraising.fxml"));
        Parent root = loader.load();
        Volunteer_FundraisingController controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleShelterFood(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Volunteer_ShelterFood.fxml"));
        Parent root = loader.load();
        Volunteer_ShelterFoodController controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleRefugeeSupport(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Volunteer_RefugeeSupport.fxml"));
        Parent root = loader.load();
        Volunteer_RefugeeSupportController controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }

    @javafx.fxml.FXML
    public void handleLogout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent personViewParent = loader.load(getClass().getResource("Temporary_login.fxml"));
        Scene personViewScene = new Scene(personViewParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @javafx.fxml.FXML
    public void handleFirstAid(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Volunteer_FirstAid.fxml"));
        Parent root = loader.load();
        Volunteer_FirstAidController controller = loader.getController();
        controller.initData(user);
        borderpane.setCenter(root);
    }
}