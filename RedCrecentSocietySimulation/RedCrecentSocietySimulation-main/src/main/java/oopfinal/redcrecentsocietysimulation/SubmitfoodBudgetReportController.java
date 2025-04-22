package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class SubmitfoodBudgetReportController
{
    @javafx.fxml.FXML
    private TextField nameField;
    @javafx.fxml.FXML
    private ComboBox<String> foodamountCombobox;
    @javafx.fxml.FXML
    private TextArea descriptionField;
    @javafx.fxml.FXML
    private DatePicker datepicker;





    @javafx.fxml.FXML
    public void initialize() {
        foodamountCombobox.getItems().addAll("10 kg ","20 kg","50 kg");
    }

    @javafx.fxml.FXML

    public void submitOnAction(ActionEvent actionEvent) {

        String name = nameField.getText();
        String foodamount = foodamountCombobox.getValue();
        String description = descriptionField.getText();
        LocalDate date = datepicker.getValue();


        Foodbudget s = new Foodbudget(name, foodamount,description,date);


        try{
            File f = new File("food.bin");
            FileOutputStream fos;
            ObjectOutputStream oos;

            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);

            } else  {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }


            oos.writeObject(s);
            oos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }








    }




}