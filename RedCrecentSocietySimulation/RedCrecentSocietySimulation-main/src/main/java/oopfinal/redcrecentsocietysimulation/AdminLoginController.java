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

public class AdminLoginController implements Serializable
{
    @javafx.fxml.FXML
    private TextField nameField;
    @javafx.fxml.FXML
    private TextField passwordField;

    @javafx.fxml.FXML
    public void initialize() {
        AdminWrite();
    }


    public ObservableList<Admin> AdminWrite() {
        // Hardcoded values
        String id1 = "sadman";
        String password1 = "1234";

        ObservableList<Admin> AdminList = FXCollections.observableArrayList();

        // Adding FrontDeskStaff objects to the list
        AdminList.add(new Admin(id1, password1));

        // Overwrite file instead of appending
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Admin.bin", false))) {
            for (Admin staff : AdminList) {
                oos.writeObject(staff); // Write each object to the file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return AdminList;

    }








    @javafx.fxml.FXML
    public void loginOnAction(ActionEvent actionEvent) {
        String name = nameField.getText().trim();
        String password = passwordField.getText().trim();

        if (name.isEmpty() || password.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        ObservableList<Admin> adminList = AdminRead();

        boolean isAuthenticated = adminList.stream()
                .anyMatch(admin -> admin.getName().equals(name) && admin.getPassword().equals(password));

        if (isAuthenticated) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminDashboard.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
                stage.setTitle("Admin Dashboard");
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
    public ObservableList<Admin> AdminRead() {
        ObservableList<Admin> Adminlist = FXCollections.observableArrayList();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Admin.bin"))) {
            while (true) {
                Adminlist.add((Admin) ois.readObject());
            }
        } catch (EOFException eof) {

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Adminlist;
    }



}