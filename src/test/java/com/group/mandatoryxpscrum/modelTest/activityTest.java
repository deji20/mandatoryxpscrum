package com.group.mandatoryxpscrum.modelTest;

import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Pricing;
import com.group.mandatoryxpscrum.models.Rules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class activityTest {
    int id = 0;
    String name = "hello";
    String description = "this is a description";
    String image = "this is an image url";
    Rules rules = new Rules(10, 0, 0, 0);
    Pricing pricing = new Pricing(100, 20, "");
    Activity activity = new Activity(id, name, image, description, pricing, rules, null);

    //getter and setter test to see if we can pull information from this model
   @Test
   public void setterTest(){
       activity.setId(id);
       activity.setName(name);
       activity.setImage(image);
       activity.setDescription(description);
       activity.setRules(rules);
       activity.setPrice(pricing);

       assertEquals(id, activity.getId());
       assertEquals(name, activity.getName());
       assertEquals(rules, activity.getRules());
       assertEquals(description, activity.getDescription());
       assertEquals(pricing, activity.getPrice());
   }
}
