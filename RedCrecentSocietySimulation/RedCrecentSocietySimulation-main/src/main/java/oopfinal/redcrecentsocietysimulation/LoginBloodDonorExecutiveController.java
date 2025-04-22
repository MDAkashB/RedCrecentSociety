package oopfinal.redcrecentsocietysimulation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class LoginBloodDonorExecutiveController implements Serializable
{
    @javafx.fxml.FXML
    private TextField nameField;
    @javafx.fxml.FXML
    private TextField passwordField;








    @javafx.fxml.FXML
    public void initialize() {
        BloodDonarExecutiveWrite();
    }



    public ObservableList<BloodDonarExecutive> BloodDonarExecutiveWrite() {
        // Hardcoded values
        String id1 = "rahman";
        String password1 = "1234";

        ObservableList<BloodDonarExecutive> BloodDonarExecutiveList = FXCollections.observableArrayList();

        // Adding FrontDeskStaff objects to the list
        BloodDonarExecutiveList.add(new BloodDonarExecutive(id1, password1));

        // Overwrite file instead of appending
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BloodDonarExecutive.bin", false))) {
            for (BloodDonarExecutive staff : BloodDonarExecutiveList) {
                oos.writeObject(staff); // Write each object to the file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return BloodDonarExecutiveList;

    }


    @javafx.fxml.FXML
    public void LoginOnAction(ActionEvent actionEvent) {
        String name = nameField.getText().trim();
        String password = passwordField.getText().trim();

        if (name.isEmpty() || password.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        ObservableList<BloodDonarExecutive> BloodDonarExecutiveList = AdminRead();

        boolean isAuthenticated = BloodDonarExecutiveList.stream()
                .anyMatch(admin -> admin.getName().equals(name) && admin.getPassword().equals(password));

        if (isAuthenticated) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("BloodDonorHeadDashboard.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
                stage.setTitle("blooddonar Dashboard");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid name or password.");
        }




    }



    // read FrontDeskStaff data
    public ObservableList<BloodDonarExecutive> AdminRead() {
        ObservableList<BloodDonarExecutive> BloodDonarExecutivelist = FXCollections.observableArrayList();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BloodDonarExecutive.bin"))) {
            while (true) {
                BloodDonarExecutivelist.add((BloodDonarExecutive) ois.readObject());
            }
        } catch (EOFException eof) {

        } catch (Exception e) {
            e.printStackTrace();
        }

        return BloodDonarExecutivelist;
    }




}