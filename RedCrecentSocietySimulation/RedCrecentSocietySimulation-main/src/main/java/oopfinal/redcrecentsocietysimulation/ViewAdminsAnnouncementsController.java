package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ViewAdminsAnnouncementsController implements Serializable
{
    @javafx.fxml.FXML
    private TableColumn<Announcement,String> toCol;
    @javafx.fxml.FXML
    private TableColumn<Announcement,String> AnnouncementCol;
    @javafx.fxml.FXML
    private TableColumn<Announcement,String> subjectCol;
    @javafx.fxml.FXML
    private TableView<Announcement> tableview;

    @javafx.fxml.FXML
    public void initialize() {


        //    private String Announcement;
        //    private String tofield;
        //    private String subjectField;

        toCol.setCellValueFactory(new PropertyValueFactory<>("tofield"));
        AnnouncementCol.setCellValueFactory(new PropertyValueFactory<>("Announcement"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subjectField"));


    }

    @javafx.fxml.FXML
    public void showOnAction(ActionEvent actionEvent) {



        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try{
            File f = new File("Announcement.bin");
            if(f.exists()) {
                fis = new FileInputStream(f);
            }
            else {

            }
            if (fis != null) ois =new ObjectInputStream(fis);

            tableview.getItems().clear();

            while (true) {
                tableview.getItems().add((Announcement) ois.readObject());

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