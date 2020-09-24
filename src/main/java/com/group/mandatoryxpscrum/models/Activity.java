package com.group.mandatoryxpscrum.models;

import java.util.ArrayList;

public class Activity extends ModelEntity{

    private String name;
    private String image;
    private String description;
    private Pricing pricing;
    private Rules rules;
    private ArrayList<Equipment> equipment;

    public Activity(int id, String name, String image, String description, Pricing pricing, Rules rules, ArrayList<Equipment> equipment) {
        super(id);
        this.name = name;
        this.image = image;
        this.description = description;
        this.pricing = pricing;
        this.rules = rules;
        this.equipment = equipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pricing getPrice() {
        return pricing;
    }

    public void setPrice(Pricing pricing) {
        this.pricing = pricing;
    }

    public Rules getRules() {
        return rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<Equipment> equipment) {
        this.equipment = equipment;
    }


}
