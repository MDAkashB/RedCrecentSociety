package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public class SubmiteventOrganizeReportController implements Serializable
{
    @javafx.fxml.FXML
    private TextField timefield;
    @javafx.fxml.FXML
    private TextField titlefield;
    @javafx.fxml.FXML
    private DatePicker datepicker;
    @javafx.fxml.FXML
    private TextArea thingstobeNotedField;

    @javafx.fxml.FXML
    public void initialize() {
    }




    @javafx.fxml.FXML
    public void submitOnAction(ActionEvent actionEvent) {
        String time = timefield.getText();
        String title = titlefield.getText();
        LocalDate date = datepicker.getValue();
        String noted = thingstobeNotedField.getText();


        Event g = new Event(time,title,date,noted);



        try{
            File f = new File("Event.bin");
            FileOutputStream fos;
            ObjectOutputStream oos;

            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);

            } else  {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }


            oos.writeObject(g);
            oos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }






    }


}