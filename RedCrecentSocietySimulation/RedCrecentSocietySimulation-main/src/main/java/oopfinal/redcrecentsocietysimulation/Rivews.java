package oopfinal.redcrecentsocietysimulation;

import java.io.Serializable;
import java.time.LocalDate;

public class Rivews implements Serializable {
    //    String to = toComboBox.getValue();
    //        String reviews = reviewsField.getText();
    //        String name = nameField.getText();
    //        LocalDate date = datepicker.getValue();
    //
    //

    String to;
    String reviews;
    String name;
    LocalDate date ;


    public Rivews(String to, String reviews, String name, LocalDate date) {
        this.to = to;
        this.reviews = reviews;
        this.name = name;
        this.date = date;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
