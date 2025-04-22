package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BloodDonorHeadDashboardController
{
    @javafx.fxml.FXML
    private BorderPane dashBoardBorderPane;

    @javafx.fxml.FXML
    public void initialize() {
    }




    @javafx.fxml.FXML
    public void LogoutOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void ViewAdminsAnnouncementOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewAdminsAnnouncements.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }

    @javafx.fxml.FXML
    public void SubmitFoodBudgetReportOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SubmitfoodBudgetReport.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }

    @javafx.fxml.FXML
    public void SubmitReviewsOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SubmitReviewsBloodDonorDepartment.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }

    @javafx.fxml.FXML
    public void SubmitVolenteersReportOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("submitVolenteersReport.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }

    @javafx.fxml.FXML
    public void SubmitEventOrganizeOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SubmiteventOrganizeReport.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }

    @javafx.fxml.FXML
    public void viewAnnualBudgetReportOnActon(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewAnnualBudgeReport.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }

    @javafx.fxml.FXML
    public void SubmitBloodDonorMonthlyReportOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("submitExecutiveMonthlyReport.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }

    @javafx.fxml.FXML
    public void SubmitEmergencyNoticeOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SubmitEmergencyNotice.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }
}