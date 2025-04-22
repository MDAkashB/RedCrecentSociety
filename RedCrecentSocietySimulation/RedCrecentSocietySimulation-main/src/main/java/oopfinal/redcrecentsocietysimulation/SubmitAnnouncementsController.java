package oopfinal.redcrecentsocietysimulation;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SubmitAnnouncementsController implements Serializable
{
    @javafx.fxml.FXML
    private TextField AnnouncementField;
    @javafx.fxml.FXML
    private TextField toField;
    @javafx.fxml.FXML
    private TextField subjectField;






    @javafx.fxml.FXML
    public void initialize() {
    }



    @javafx.fxml.FXML
    public void submitAnnouncementOnAction(ActionEvent actionEvent) {

        //String
        String announcement = AnnouncementField.getText();
        String tofield = toField.getText();
        String sub = subjectField.getText();


        Announcement a = new Announcement(announcement,tofield,sub);

        try{
            File f = new File("Announcement.bin");
            FileOutputStream fos;
            ObjectOutputStream oos;

            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);

            } else  {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }


            oos.writeObject(a);
            oos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }




    }


}