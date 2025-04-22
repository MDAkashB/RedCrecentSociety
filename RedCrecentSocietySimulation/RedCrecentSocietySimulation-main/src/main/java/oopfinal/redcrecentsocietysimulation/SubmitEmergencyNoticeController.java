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

public class SubmitEmergencyNoticeController
{
    @javafx.fxml.FXML
    private ComboBox<String> toComboBox;
    @javafx.fxml.FXML
    private TextArea Noticefield;
    @javafx.fxml.FXML
    private DatePicker datePicker;
    @javafx.fxml.FXML
    private TextField TitleField;

    @javafx.fxml.FXML
    public void initialize() {
        toComboBox.getItems().addAll("BloodDonarExecutive");


    }

    @javafx.fxml.FXML
    public void SubmitNoticeOnAction(ActionEvent actionEvent) {
        String to = toComboBox.getValue();
        String Notice = Noticefield.getText();
        LocalDate date = datePicker.getValue();
        String title = TitleField.getText();


        Emergencynotice d = new Emergencynotice(to,Notice,date,title);


        try{
            File f = new File("Emergencynotice.bin");
            FileOutputStream fos;
            ObjectOutputStream oos;

            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);

            } else  {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }



            oos.writeObject(d);
            oos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }





    }



}