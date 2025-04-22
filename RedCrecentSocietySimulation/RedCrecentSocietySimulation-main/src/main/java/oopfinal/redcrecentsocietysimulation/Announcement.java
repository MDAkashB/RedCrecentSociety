package oopfinal.redcrecentsocietysimulation;

import java.io.Serializable;

public class Announcement implements Serializable {
    private String Announcement;
    private String tofield;
    private String subjectField;

    public Announcement(String announcement, String tofield, String subjectField) {
        Announcement = announcement;
        this.tofield = tofield;
        this.subjectField = subjectField;
    }

    public String getAnnouncement() {
        return Announcement;
    }

    public void setAnnouncement(String announcement) {
        Announcement = announcement;
    }

    public String getTofield() {
        return tofield;
    }

    public void setTofield(String tofield) {
        this.tofield = tofield;
    }

    public String getSubjectField() {
        return subjectField;
    }

    public void setSubjectField(String subjectField) {
        this.subjectField = subjectField;
    }
}
