package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.data.services.ActivityService;
import com.group.mandatoryxpscrum.data.services.BookingService;
import com.group.mandatoryxpscrum.models.Booking;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookingRepositoryTests {

    @Autowired
    ActivityService activityService;
    @Autowired
    BookingService bookingService;

    @Test
    public void successIfSingleBookingIsRetrived(){
        Assert.assertEquals(Booking.class, bookingService.fetchById(1).getClass());
    }

    @Test
    public void successIfAllBookingsAreRetrived(){
        int highestId = 0;
        for(Booking booking : bookingService.fetchAll()){
            if(booking.getId() > highestId){highestId = booking.getId();}
        }
        Assert.assertNull(bookingService.fetchById(highestId+1));
    }

    @Test
    public void successIfBookingIsAddedAndDeletedFromDatabase(){
        Booking booking = new Booking();
        booking.setAmount(10);
        booking.setCustomerName("irene");
        booking.setDate(LocalDate.now());
        booking.setTime(LocalTime.now());
//        booking.setInstructor("Jan Sumo");
        booking.setPhoneNumber("91492089");
        booking.setActivity(activityService.fetchAll().get(0));

        //saves the new booking returning a booking with an id
        int id = bookingService.save(booking).getId();
        //check if it was saved
        Assert.assertNotNull(bookingService.fetchById(id));

        //delete the new Booking from the database
        //bookingService.delete(id);
        //check if it was deleted
        Assert.assertNull(bookingService.fetchById(id));
    }
}
