package oopfinal.redcrecentsocietysimulation;

import java.io.Serializable;
import java.time.LocalDate;

public class MonthlyReport implements Serializable {
    //    String to = toComboBox.getValue();
    //        LocalDate date = datepicker.getValue();
    //        String monthly = monthlyrepTextarea.getText();
    //
    //


    String to;
    LocalDate date;
    String Monthly;


    public MonthlyReport(String to, LocalDate date, String monthly) {
        this.to = to;
        this.date = date;
        Monthly = monthly;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMonthly() {
        return Monthly;
    }

    public void setMonthly(String monthly) {
        Monthly = monthly;
    }
}
