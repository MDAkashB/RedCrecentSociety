package oopfinal.redcrecentsocietysimulation.sponsor;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SponsorController {

    @javafx.fxml.FXML
    public void donationButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/user_3_4/sponsor/donation_page.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Donation Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void financial_manager_button(ActionEvent event) {
        System.out.println("Financial Manager button clicked!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/user_3_4/sponsor/financial_manager.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Financial Manager Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
