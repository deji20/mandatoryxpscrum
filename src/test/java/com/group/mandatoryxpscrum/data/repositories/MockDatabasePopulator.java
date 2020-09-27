package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Equipment;
import com.group.mandatoryxpscrum.models.Pricing;
import com.group.mandatoryxpscrum.models.Rules;

import java.util.*;

public class MockDatabasePopulator {

    public Activity getNewTestActivity(){
        Activity a = new Activity();
        a.setName("TEST_NAME");
        a.setImage("TestImage.jpg");
        a.setDescription("Test_Description");

        Pricing p = new Pricing();
        p.setStandard(200);
        p.setDiscount(150);
        p.setExtraInfo("TestExtraInfo");

        Rules r = new Rules();
        r.setMaxCapacity(25);
        r.setDuration(60);
        r.setAgeLimit(16);
        r.setHeightLimit(175);

        Equipment e1 = new Equipment();
        e1.setName("Test_equipment_name");
        e1.setAvailable(true);
        e1.setComment("Test Comment");


        Equipment e2 = new Equipment();
        e2.setName("Test_equipment_name");
        e2.setAvailable(true);
        e2.setComment("Test Comment");

        Equipment e3 = new Equipment();
        e3.setName("Test_equipment_name");
        e3.setAvailable(true);
        e3.setComment("Test Comment");

        Set<Equipment> equipment = new HashSet<>();
        equipment.add(e1);
        equipment.add(e2);
        equipment.add(e3);

        a.setPricing(p);
        a.setRules(r);
        a.setEquipment(equipment);
        p.setActivity(a);
        r.setActivity(a);
        e1.setActivity(a);
        e2.setActivity(a);
        e3.setActivity(a);
        return a;
    }

    public List<Activity> getNewTestActivityList(){
        Activity a1 = getNewTestActivity();
        Activity a2 = getNewTestActivity();
        Activity a3 = getNewTestActivity();
        Activity a4 = getNewTestActivity();
        Activity a5 = getNewTestActivity();

        List<Activity> activities = new ArrayList<>();

        activities.add(a1);
        activities.add(a2);
        activities.add(a3);
        activities.add(a4);
        activities.add(a5);

        return activities;
    }

}
