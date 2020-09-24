package com.group.mandatoryxpscrum.modelTest;

import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Equipment;
import com.group.mandatoryxpscrum.models.Pricing;
import com.group.mandatoryxpscrum.models.Rules;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {
    int id = 0;
    String name = "hello";
    String description = "this is a description";
    String image = "this is an image url";

    //getter and initializing test to see if we can pull information from; and insert information into, the given models
   @Test
   public void inputAndGetterTest(){
       //initializes the model classes and sets them into a new activity;
       ArrayList<Equipment> equipment = new ArrayList<>();
       equipment.add(new Equipment(0, "equip this", true, null));
       Rules rules = new Rules(10, 0, 0, 0);
       Pricing pricing = new Pricing(100, 20, "");
       Activity activity = new Activity(id, name, image, description, pricing, rules, equipment);

       //checks if everything was inserted correctly
       assertEquals(id, activity.getId());
       assertEquals(name, activity.getName());
       assertEquals(rules, activity.getRules());
       assertEquals(description, activity.getDescription());
       assertEquals(pricing, activity.getPrice());
       assertEquals(equipment, activity.getEquipment());
   }
}
