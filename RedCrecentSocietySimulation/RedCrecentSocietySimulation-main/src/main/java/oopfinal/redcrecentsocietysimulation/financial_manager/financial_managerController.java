package oopfinal.redcrecentsocietysimulation.financial_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class financial_managerController {

    @javafx.fxml.FXML
    public void backButtonFinancialManagerPage(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/user_3_4/sponsor/sponsor.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sponsor Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
