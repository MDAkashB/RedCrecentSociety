package oopfinal.redcrecentsocietysimulation;

import java.io.Serializable;
import java.time.LocalDate;

public class AnnualBudgetReport implements Serializable {
    LocalDate date;
    String tocombobox;
    String remarks;
    Integer amountfield;

    public AnnualBudgetReport(LocalDate date, String tocombobox, String remarks, Integer amountfield) {
        this.date = date;
        this.tocombobox = tocombobox;
        this.remarks = remarks;
        this.amountfield = amountfield;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTocombobox() {
        return tocombobox;
    }

    public void setTocombobox(String tocombobox) {
        this.tocombobox = tocombobox;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getAmountfield() {
        return amountfield;
    }

    public void setAmountfield(Integer amountfield) {
        this.amountfield = amountfield;
    }
}
