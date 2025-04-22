package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class ViewReviewsOfBloodDonarDepController
{
    @javafx.fxml.FXML
    private TableColumn<Rivews,String> volenteernameCol;
    @javafx.fxml.FXML
    private TableColumn<Rivews,String> toCol;
    @javafx.fxml.FXML
    private TableColumn<Rivews, LocalDate> dateCol;
    @javafx.fxml.FXML
    private TableView<Rivews> tableview;
    @javafx.fxml.FXML
    private TableColumn<Rivews,String> reviewsCol;

    @javafx.fxml.FXML
    public void initialize() {
        //
//        String to;
//        String reviews;
//        String name;
//        LocalDate date ;

        toCol.setCellValueFactory(new PropertyValueFactory<>("to"));
        reviewsCol.setCellValueFactory(new PropertyValueFactory<>("reviews"));
        volenteernameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));


    }

    @javafx.fxml.FXML
    public void submitOnAction(ActionEvent actionEvent) {


        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try{
            File f = new File("Riviews.bin");
            if(f.exists()) {
                fis = new FileInputStream(f);
            }
            else {

            }
            if (fis != null) ois =new ObjectInputStream(fis);

            tableview.getItems().clear();

            while (true) {
                tableview.getItems().add((Rivews) ois.readObject());

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