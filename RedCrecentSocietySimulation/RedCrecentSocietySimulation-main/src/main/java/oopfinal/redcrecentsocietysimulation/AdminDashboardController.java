package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController
{
    @javafx.fxml.FXML
    private BorderPane dashBoardBorderPane;




    @javafx.fxml.FXML
    public void initialize() {
    }


    @javafx.fxml.FXML
    public void ViewEmergencyNoticeOnAction(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewEmergencyNotice.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}



    }

    @javafx.fxml.FXML
    public void ViewRivewsOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewReviewsOfBloodDonarDep.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }

    @javafx.fxml.FXML
    public void submitAnnualBudgetReportOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("submitAnnualBudgetReport.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


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
    public void SubmitAnnouncementsOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SubmitAnnouncements.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }

    @javafx.fxml.FXML
    public void ViewVolenteersReportOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewVolenteersReport.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }

    @javafx.fxml.FXML
    public void ViewFoodBudgetReportOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("VIewFoodBudgetReport.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }

    @javafx.fxml.FXML
    public void ViewEventOrganizeReportOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewEventOrganizeReport.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }

    @javafx.fxml.FXML
    public void viewExecutiveMontrhlyReportOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewExecutiveMonthlyReport.fxml"));
            dashBoardBorderPane.setCenter(fxmlLoader.load());
        }
        catch(Exception e){}


    }
}