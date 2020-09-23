package com.group.mandatoryxpscrum.modelTest;

import com.group.mandatoryxpscrum.models.Activity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class activityTest {
    Activity activity = new Activity();
    int id = 0;
    String name = "hello";
    String rules = "max: 10 \n other: rule";
    String description = "this is a description";
    String price = "120, pr Person\n100, pr person if over 10";

    //getter and setter test to see if we can pull information from this model
   @Test
   public void setterTest(){
       activity.setId(id);
       activity.setName(name);
       activity.setRules(rules);
       activity.setDescription(description);
       activity.setPrice(price);

       assertEquals(id, activity.getId());
       assertEquals(name, activity.getName());
       assertEquals(rules, activity.getRules());
       assertEquals(description, activity.getDescription());
       assertEquals(price, activity.getPrice());
   }
}
