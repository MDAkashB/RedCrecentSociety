package oopfinal.redcrecentsocietysimulation;

import java.io.Serializable;
import java.time.LocalDate;

public class Emergencynotice implements Serializable {


    // String to = toComboBox.getValue();
    //        String Notice = Noticefield.getText();
    //        LocalDate date = datePicker.getValue();
    //        String title = TitleField.getText();
    //
    //

    String to;
    String notice;
    LocalDate date;
    String title;


    public Emergencynotice(String to, String notice, LocalDate date, String title) {
        this.to = to;
        this.notice = notice;
        this.date = date;
        this.title = title;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
