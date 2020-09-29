package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.data.services.ActivityService;
import com.group.mandatoryxpscrum.data.services.BookingService;
import com.group.mandatoryxpscrum.models.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
public class BookingRepositoryTests {

    @Autowired
    ActivityService activityService;
    @Autowired
    BookingService bookingService;

    @Test
    public void returnsTrueIfBookingIsAddedToDatabase(){
        Booking booking = new Booking();
        booking.setAmount(10);
        booking.setCustomerName("irene");
        booking.setDate(LocalDate.now());
        booking.setTime(LocalTime.now());
        booking.setInstructor("Jan Sumo");
        booking.setPhoneNumber("91492089");
        booking.setActivity(activityService.fetchAll().get(0));

        System.out.println(bookingService.save(booking).getId());


    }
}
