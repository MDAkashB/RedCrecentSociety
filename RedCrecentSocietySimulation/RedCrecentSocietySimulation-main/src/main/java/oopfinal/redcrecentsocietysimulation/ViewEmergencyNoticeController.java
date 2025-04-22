package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class ViewEmergencyNoticeController
{
    @javafx.fxml.FXML
    private TableColumn<Emergencynotice,String> titleCol;
    @javafx.fxml.FXML
    private TableColumn<Emergencynotice,String> ToCol;
    @javafx.fxml.FXML
    private TableColumn<Emergencynotice,String> NoticeCol;
    @javafx.fxml.FXML
    private TableView<Emergencynotice> tableview;
    @javafx.fxml.FXML
    private TableColumn<Emergencynotice, LocalDate> DateCol;

    @javafx.fxml.FXML
    public void initialize() {

        //   String to;
        //    String notice;
        //    LocalDate date;
        //    String title;

        ToCol.setCellValueFactory(new PropertyValueFactory<>("to"));
        NoticeCol.setCellValueFactory(new PropertyValueFactory<>("notice"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));


    }




    @javafx.fxml.FXML
    public void showOnAction(ActionEvent actionEvent) {

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try{
            File f = new File("Emergencynotice.bin");
            if(f.exists()) {
                fis = new FileInputStream(f);
            }
            else {

            }
            if (fis != null) ois =new ObjectInputStream(fis);

            tableview.getItems().clear();

            while (true) {
                tableview.getItems().add((Emergencynotice) ois.readObject());

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