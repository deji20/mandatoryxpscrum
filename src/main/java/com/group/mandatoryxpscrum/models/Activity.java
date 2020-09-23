package com.group.mandatoryxpscrum.models;

import java.util.ArrayList;

public class Activity extends ModelEntity{
    private String image;
    private String name;
    private String price;
    private String rules;
    private String description;
    private ArrayList<Equipment> equipment;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRules() {
        return rules;
    }
    public void setRules(String rules) {
        this.rules = rules;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }
    public void setEquipment(ArrayList<Equipment> equipment) {
        this.equipment = equipment;
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
