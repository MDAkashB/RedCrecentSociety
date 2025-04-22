package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public class SubmitAnnualBudgetReportController implements Serializable
{
    @javafx.fxml.FXML
    private DatePicker datepicker;
    @javafx.fxml.FXML
    private ComboBox<String> toComboBox;
    @javafx.fxml.FXML
    private TextArea remarksTextArea;
    @javafx.fxml.FXML
    private TextField amountField;

    @javafx.fxml.FXML
    public void initialize() {


        toComboBox.getItems().addAll("BloodDonarExecutive");
    }

    @javafx.fxml.FXML
    public void submitOnAction(ActionEvent actionEvent) {
        LocalDate date = datepicker.getValue();
        String tocom = toComboBox.getValue();
        String remarks = remarksTextArea.getText();
        int  amount = Integer.parseInt(amountField.getText());


        AnnualBudgetReport b = new AnnualBudgetReport(date,tocom,remarks,amount);


        try{
            File f = new File("AnnualBudgetReport.bin");
            FileOutputStream fos;
            ObjectOutputStream oos;

            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);

            } else  {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }



            oos.writeObject(b);
            oos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }







    }






}