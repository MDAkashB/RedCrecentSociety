package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class ViewAnnualBudgeReportController
{
    @javafx.fxml.FXML
    private TableColumn<AnnualBudgetReport,String> toCol;
    @javafx.fxml.FXML
    private TableColumn<AnnualBudgetReport,Integer> amountCol;
    @javafx.fxml.FXML
    private TableColumn<AnnualBudgetReport, LocalDate> dateCol;
    @javafx.fxml.FXML
    private TableView<AnnualBudgetReport> tableview;
    @javafx.fxml.FXML
    private TableColumn<AnnualBudgetReport,String> remarksCol;


    @javafx.fxml.FXML
    public void initialize() {
        //    LocalDate date;
        //    String tocombobox;
        //    String remarks;
        //    Integer amountfield;
        toCol.setCellValueFactory(new PropertyValueFactory<>("tocombobox"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        remarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amountfield"));


    }




    @javafx.fxml.FXML
    public void showOnAction(ActionEvent actionEvent) {


        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try{
            File f = new File("AnnualBudgetReport.bin");
            if(f.exists()) {
                fis = new FileInputStream(f);
            }
            else {

            }
            if (fis != null) ois =new ObjectInputStream(fis);

            tableview.getItems().clear();

            while (true) {
                tableview.getItems().add((AnnualBudgetReport) ois.readObject());

            }


        }
        catch (Exception e) {
            try {
                if (ois != null) ois.close();
            }catch (Exception e2){

            }
        }



    }


}