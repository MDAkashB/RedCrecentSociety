package com.example.bangladeshredcrescentsociety.Ripa_modelClasses;

import java.io.Serializable;

public class Skill implements Serializable {
    private int skillId;
    private String name;
    private String description;

    public Skill(int skillId, String name, String description) {
        this.skillId = skillId;
        this.name = name;
        this.description = description;
    }

    public void updateDescription(String newDesc) {
        this.description = newDesc;
    }

    // Getters and Setters
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}