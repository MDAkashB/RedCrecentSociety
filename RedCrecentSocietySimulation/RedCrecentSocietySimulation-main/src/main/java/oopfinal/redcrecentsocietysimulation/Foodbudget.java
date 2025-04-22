package oopfinal.redcrecentsocietysimulation;

import java.io.Serializable;
import java.time.LocalDate;

public class Foodbudget implements Serializable {



    //     String name = nameField.getText();
    //        String foodamount = foodamountCombobox.getValue();
    //        String description = descriptionField.getText();
    //        LocalDate date = datepicker.getValue();
    //
    //


    String name;
    String foodamount;
    String description;
    LocalDate date;


    public Foodbudget(String name, String foodamount, String description, LocalDate date) {
        this.name = name;
        this.foodamount = foodamount;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodamount() {
        return foodamount;
    }

    public void setFoodamount(String foodamount) {
        this.foodamount = foodamount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
