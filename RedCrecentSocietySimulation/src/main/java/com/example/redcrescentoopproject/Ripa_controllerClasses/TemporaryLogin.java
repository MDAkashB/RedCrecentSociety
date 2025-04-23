package com.example.bangladeshredcrescentsociety.Ripa_controllerClasses;

import com.example.bangladeshredcrescentsociety.Ripa_modelClasses.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TemporaryLogin {
    @javafx.fxml.FXML
    private PasswordField password;
    @javafx.fxml.FXML
    private TextField userid;


    User user;

    public void initData(User user) {
        this.user = user;
    }

    @javafx.fxml.FXML
    public void initialize() {
    }

    @Deprecated
    public void handleVolunteerLogin(ActionEvent actionEvent) throws IOException {
        User u = new User(Integer.parseInt(userid.getText()), "Ripa Sarkar", "ripa@gmail.com", "0125478521", password.getText());

        FXMLLoader loader = new FXMLLoader();
        Parent personViewParent = null;
        Scene personViewScene = null;
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        loader.setLocation(getClass().getResource("Volunteer_Dashboard.fxml"));
        personViewParent = loader.load();
        personViewScene = new Scene(personViewParent);
        Volunteer_DashboardController controller = loader.getController();
        controller.initData(u);

        window.setScene(personViewScene);
        window.show();
    }


    @javafx.fxml.FXML
    public void handleOrganizerLogin(ActionEvent actionEvent) throws IOException {
        User u = new User(Integer.parseInt(userid.getText()), "Ripa Sarkar", "ripa@gmail.com", "0125478521", password.getText());

        FXMLLoader loader = new FXMLLoader();
        Parent personViewParent = null;
        Scene personViewScene = null;
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        loader.setLocation(getClass().getResource("Organizer_Dashboard.fxml"));
        personViewParent = loader.load();
        personViewScene = new Scene(personViewParent);
        Organizer_DashboardController controller = loader.getController();
        controller.initData(u);

        window.setScene(personViewScene);
        window.show();
    }

}