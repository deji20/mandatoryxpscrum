package com.group.mandatoryxpscrum.modelTest;

import com.group.mandatoryxpscrum.models.*;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModelTest {
    //getter and initializing test to see if we can pull information from; and insert information into, the given models
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

   @Test
   public void bookingInputOutput(){
       int amount = 100;
       LocalDate date = LocalDate.now();
       LocalTime time = LocalTime.now();
       String instructor = "johnson";
       String phoneNumber = "91429089";
       String customerName = "irene";
       Activity activity = new Activity();

       Booking booking = new Booking();
       booking.setAmount(amount);
       booking.setDate(date);
       booking.setTime(time);
       booking.setInstructor(instructor);
       booking.setPhoneNumber(phoneNumber);
       booking.setCustomerName(customerName);
       //booking.setActivity(activity);

       assertEquals(amount, booking.getAmount());
       assertEquals(date, booking.getDate());
       assertEquals(time, booking.getTime());
       assertEquals(instructor, booking.getInstructor());
       assertEquals(phoneNumber, booking.getPhoneNumber());
       assertEquals(customerName, booking.getCustomerName());
      // assertEquals(activity, booking.getActivity());
   }
}
