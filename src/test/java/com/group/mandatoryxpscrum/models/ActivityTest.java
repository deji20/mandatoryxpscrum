package com.group.mandatoryxpscrum.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    @Test
    public void inputAndGetterTest(){
        int id = 0;
        String name = "hello";
        String description = "this is a description";
        String image = "this is an image url";


        //initializes the model classes and sets them into a new activity;
        List<Equipment> equipment = new ArrayList<>();
        equipment.add(new Equipment("sled", true, null,null));
        Rules rules = new Rules();
        Pricing pricing = new Pricing();
        Activity activity = new Activity(id, name, image, description, rules, pricing, equipment);

        //checks if everything was inserted correctly
        assertEquals(id, activity.getId());
        assertEquals(name, activity.getName());
        assertEquals(description, activity.getDescription());
        assertEquals(rules, activity.getRules());
        assertEquals(pricing, activity.getPricing());
        assertEquals(equipment, activity.getEquipment());
    }

}