package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class ViewVolenteersReportController implements Serializable
{
    @javafx.fxml.FXML
    private TableColumn<volenteersreport,String> desCriptionCol;
    @javafx.fxml.FXML
    private TableColumn<volenteersreport,Integer> idCol;
    @javafx.fxml.FXML
    private TableColumn<volenteersreport,String> nameCol;
    @javafx.fxml.FXML
    private TableColumn<volenteersreport,String> bgCol;
    @javafx.fxml.FXML
    private TableView<volenteersreport> tableview;

    @javafx.fxml.FXML
    public void initialize() {
        //    private  String name;
        //    private  Integer id;
        //    private  String  description;
        //    private  String bloodgroup;


        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        desCriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        bgCol.setCellValueFactory(new PropertyValueFactory<>("bloodgroup"));




    }

    @javafx.fxml.FXML
    public void showVolenteerReportOnAction(ActionEvent actionEvent) {


        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try{
            File f = new File("Volenteers.bin");
            if(f.exists()) {
                fis = new FileInputStream(f);
            }
            else {

            }
            if (fis != null) ois =new ObjectInputStream(fis);

            tableview.getItems().clear();

            while (true) {
                tableview.getItems().add((volenteersreport) ois.readObject());

            }


        }
        catch (Exception e) {
            try {
                if (ois != null) ois.close();
            }catch (Exception e2){

            }
        }






    }

    @Deprecated
    public void backOnAction(ActionEvent actionEvent) {
    }
}