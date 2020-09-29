package com.group.mandatoryxpscrum.data.services;

import com.group.mandatoryxpscrum.data.repositories.BookingRepository;
import com.group.mandatoryxpscrum.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    public Booking save(Booking booking){
        return bookingRepository.save(booking);
   }
}
