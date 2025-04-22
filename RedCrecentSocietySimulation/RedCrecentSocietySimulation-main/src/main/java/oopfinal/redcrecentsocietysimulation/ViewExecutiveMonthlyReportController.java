package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class ViewExecutiveMonthlyReportController
{
    @javafx.fxml.FXML
    private TableColumn<MonthlyReport,String> toCol;
    @javafx.fxml.FXML
    private TableColumn<MonthlyReport,String> monthlyrepCol;
    @javafx.fxml.FXML
    private TableColumn<MonthlyReport, LocalDate> DateCol;
    @javafx.fxml.FXML
    private TableView<MonthlyReport> tableview;

    @javafx.fxml.FXML
    public void initialize() {

//        String to;
//        LocalDate date;
//        String Monthly;

        toCol.setCellValueFactory(new PropertyValueFactory<>("to"));
        monthlyrepCol.setCellValueFactory(new PropertyValueFactory<>("monthly"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date"));



    }

    @javafx.fxml.FXML
    public void showOnAction(ActionEvent actionEvent) {


        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try{
            File f = new File("monthly.bin");
            if(f.exists()) {
                fis = new FileInputStream(f);
            }
            else {

            }
            if (fis != null) ois =new ObjectInputStream(fis);

            tableview.getItems().clear();

            while (true) {
                tableview.getItems().add((MonthlyReport) ois.readObject());

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