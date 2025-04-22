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

public class SubmitReviewsBloodDonorDepartmentController implements Serializable
{
    @javafx.fxml.FXML
    private ComboBox<String> toComboBox;
    @javafx.fxml.FXML
    private TextArea reviewsField;
    @javafx.fxml.FXML
    private TextField nameField;
    @javafx.fxml.FXML
    private DatePicker datepicker;

    @javafx.fxml.FXML
    public void initialize() {
        toComboBox.getItems().addAll("Admin");

    }

    @javafx.fxml.FXML
    public void submitOnAction(ActionEvent actionEvent) {

        String to = toComboBox.getValue();
        String reviews = reviewsField.getText();
        String name = nameField.getText();
        LocalDate date = datepicker.getValue();

        Rivews r = new Rivews(to,reviews,name,date);


        try{
            File f = new File("Riviews.bin");
            FileOutputStream fos;
            ObjectOutputStream oos;

            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);

            } else  {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }


            oos.writeObject(r);
            oos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }




    }




}