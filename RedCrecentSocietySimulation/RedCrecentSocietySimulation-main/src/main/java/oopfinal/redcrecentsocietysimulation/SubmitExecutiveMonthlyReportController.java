package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public class SubmitExecutiveMonthlyReportController  implements Serializable
{
    @javafx.fxml.FXML
    private ComboBox<String> toComboBox;
    @javafx.fxml.FXML
    private DatePicker datepicker;
    @javafx.fxml.FXML
    private TextArea monthlyrepTextarea;

    @javafx.fxml.FXML
    public void initialize() {
        toComboBox.getItems().addAll("BlooddonarExecutive");
    }

    @javafx.fxml.FXML
    public void submitOnAction(ActionEvent actionEvent) {
        String to = toComboBox.getValue();
        LocalDate date = datepicker.getValue();
        String monthly = monthlyrepTextarea.getText();


        MonthlyReport m = new MonthlyReport(to,date,monthly);



        try{
            File f = new File("monthly.bin");
            FileOutputStream fos;
            ObjectOutputStream oos;

            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);

            } else  {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }


            oos.writeObject(m);
            oos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }








    }
}