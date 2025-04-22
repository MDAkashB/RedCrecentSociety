package oopfinal.redcrecentsocietysimulation;

import java.io.Serializable;

public class volenteersreport implements Serializable {
    private  String name;
    private  Integer id;
    private  String  description;
    private  String bloodgroup;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public volenteersreport(String name, Integer id, String description, String bloodgroup) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.bloodgroup = bloodgroup;





    }
}
