package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;

public class SubmitVolenteersReportController implements Serializable
{
    @javafx.fxml.FXML
    private TextField namefield;
    @javafx.fxml.FXML
    private TextArea descriptionField;
    @javafx.fxml.FXML
    private TextField idfield;
    @javafx.fxml.FXML
    private TextField bloodgroupfield;
    @javafx.fxml.FXML
    private Label resultLabel;

    @javafx.fxml.FXML
    public void initialize() {

    }

    @javafx.fxml.FXML
    public void submitOnAction(ActionEvent actionEvent) {
        String name = namefield.getText();
        Integer id = Integer.valueOf(idfield.getText());
        String  description = descriptionField.getText();
        String bloodgroup = bloodgroupfield.getText();


        if(name.isEmpty() || id == null || description.isEmpty() || bloodgroup.isEmpty()){
            resultLabel.setText("YOUR FIELDS ARE EMPTY STUPID");
        }

        volenteersreport v = new volenteersreport(name,id,description,bloodgroup);

        // write a file when we are submiting

        try{
            File f = new File("Volenteers.bin");
            FileOutputStream fos;
            ObjectOutputStream oos;

            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);

            } else  {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }


            resultLabel.setText("Successfully SUBMITTED");
            oos.writeObject(v);
            oos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Deprecated
    public void backOnAction(ActionEvent actionEvent) {
    }
}