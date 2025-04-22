package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class ViewEventOrganizeReportController
{
    @javafx.fxml.FXML
    private TableColumn<Event,String> titleCol;
    @javafx.fxml.FXML
    private TableColumn<Event, LocalDate> dateCol;
    @javafx.fxml.FXML
    private TableColumn<Event,String> thingsCol;
    @javafx.fxml.FXML
    private TableColumn<Event,String> timeCol;
    @javafx.fxml.FXML
    private TableView<Event> tableview;

    @javafx.fxml.FXML
    public void initialize() {

        // String time;
        //    String title;
        //    LocalDate date;
        //    String noted;

        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        thingsCol.setCellValueFactory(new PropertyValueFactory<>("noted"));
    }

    @javafx.fxml.FXML
    public void submitOnAction(ActionEvent actionEvent) {

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try{
            File f = new File("Event.bin");
            if(f.exists()) {
                fis = new FileInputStream(f);
            }
            else {

            }
            if (fis != null) ois =new ObjectInputStream(fis);

            tableview.getItems().clear();

            while (true) {
                tableview.getItems().add((Event) ois.readObject());

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