package com.group.mandatoryxpscrum.modelTest;

import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Equipment;
import com.group.mandatoryxpscrum.models.Pricing;
import com.group.mandatoryxpscrum.models.Rules;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {
    //getter and initializing test to see if we can pull information from; and insert information into, the given models
   @Test
   public void inputAndGetterTest(){
       int id = 0;
       String name = "hello";
       String description = "this is a description";
       String image = "this is an image url";


       //initializes the model classes and sets them into a new activity;
       ArrayList<Equipment> equipment = new ArrayList<>();
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
