package com.group.mandatoryxpscrum.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    public void bookingInputOutput(){
        int amount = 100;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Instructor instructor = new Instructor();
        instructor.setId(1);
        instructor.setName("Johnson");
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
        booking.setActivity(activity);

        assertEquals(amount, booking.getAmount());
        assertEquals(date, booking.getDate());
        assertEquals(time, booking.getTime());
        assertEquals(instructor, booking.getInstructor());
        assertEquals(phoneNumber, booking.getPhoneNumber());
        assertEquals(customerName, booking.getCustomerName());
        assertEquals(activity, booking.getActivity());
    }

}