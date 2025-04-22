package oopfinal.redcrecentsocietysimulation;

import java.io.Serializable;
import java.time.LocalDate;

public class Event  implements Serializable {


    //      String time = timefield.getText();
    //        String title = titlefield.getText();
    //        LocalDate date = datepicker.getValue();
    //        String noted = thingstobeNotedField.getText();


    String time;
    String title;
    LocalDate date;
    String noted;


    public Event(String time, String title, LocalDate date, String noted) {
        this.time = time;
        this.title = title;
        this.date = date;
        this.noted = noted;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNoted() {
        return noted;
    }

    public void setNoted(String noted) {
        this.noted = noted;
    }
}
