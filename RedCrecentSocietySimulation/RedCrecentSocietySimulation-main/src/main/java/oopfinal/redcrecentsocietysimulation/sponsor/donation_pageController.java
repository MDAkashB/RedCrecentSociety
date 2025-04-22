package oopfinal.redcrecentsocietysimulation.sponsor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class donation_pageController {

    @FXML
    private Label d_titlee;
    @FXML
    private Label Your_Donation_Amount_text;
    @FXML
    private TextField donationAmount_feild;
    @FXML
    private TextField donor_name_field;
    @FXML
    private ComboBox<String> payment_method_comboBox;
    @FXML
    private Label total_donation_amount;
    @FXML
    private Label my_donation_amount;
    @FXML
    private ComboBox<String> totaldonnerlist_combobox;
    @FXML
    private Label total_donner_list_label;
    @FXML
    private Label ta;
    @FXML
    private Label donationAmountInvested;
    @FXML
    private AnchorPane donnerNameInvest;
    @FXML
    private Label donationIinvestedAmount;

    private List<Donation> donationList = new ArrayList<>();
    @FXML
    private Button c;
    @FXML
    private Label selectedDonorLabel;

    @FXML
    public void initialize() {
        // Initialize payment method combo box
        payment_method_comboBox.getItems().addAll("Credit Card", "Bank Transfer", "Mobile Payment");

        // Set initial totals
        total_donation_amount.setText("৳0.0");
        my_donation_amount.setText("৳0.0");

        // Add listener for donor selection
        totaldonnerlist_combobox.setOnAction(event -> handleDonorSelection());
    }

    private void handleDonorSelection() {
        String selectedDonor = totaldonnerlist_combobox.getValue();
        if (selectedDonor != null) {
            for (Donation d : donationList) {
                if (d.getDonorName().equals(selectedDonor)) {
                    // Update donor name and amount invested
                    ta.setText(d.getDonorName());
                    donationAmountInvested.setText("৳" + d.getAmount());
                    return;
                }
            }
            // Clear labels if donor not found
            ta.setText("");
            donationAmountInvested.setText("");
        }
    }

    @FXML
    public void confirm_donation_button(ActionEvent actionEvent) {
        String donorName = donor_name_field.getText();
        String amountText = donationAmount_feild.getText();
        String paymentMethod = payment_method_comboBox.getValue();

        if (donorName.isEmpty() || amountText.isEmpty() || paymentMethod == null) {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Missing Information");
            warning.setHeaderText(null);
            warning.setContentText("Please fill in donor name, donation amount, and payment method.");
            warning.showAndWait();
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            Donation donation = new Donation(donorName, amount, paymentMethod);
            donationList.add(donation);

            // Calculate total donation amount
            double total = 0;
            for (Donation d : donationList) {
                total += d.getAmount();
            }

            // Show confirmation alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Donation Confirmed");
            alert.setHeaderText(null);
            alert.setContentText("✅ Thank you " + donorName + "! Your donation of ৳" + amount + " via " + paymentMethod + " has been submitted.");
            alert.showAndWait();

            // Update UI
            total_donation_amount.setText("৳" + total);
            my_donation_amount.setText(donorName + ": ৳" + amount + " via " + paymentMethod);

            if (!totaldonnerlist_combobox.getItems().contains(donorName)) {
                totaldonnerlist_combobox.getItems().add(donorName);
            }

            // Clear input fields
            donor_name_field.clear();
            donationAmount_feild.clear();
            payment_method_comboBox.getSelectionModel().clearSelection();

        } catch (NumberFormatException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid Input");
            error.setHeaderText(null);
            error.setContentText("Please enter a valid donation amount.");
            error.showAndWait();
        }
    }

    public List<Donation> getDonationList() {
        return donationList;
    }

    @FXML
    public void backBButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/user_3_4/sponsor/sponsor.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sponsor Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
