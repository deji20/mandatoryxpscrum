package com.group.mandatoryxpscrum.data.services;

import com.group.mandatoryxpscrum.data.repositories.BookingRepository;
import com.group.mandatoryxpscrum.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;
    public Booking save(Booking booking){
        return bookingRepository.save(booking);
    }
    public Booking fetchById(int id){
        return bookingRepository.getOne(id);
    }

    public List<Booking> fetchAll(){
        return bookingRepository.findAll();
    }

    public void delete(int id){
        bookingRepository.deleteById(id);
    }

    /** Sletter en booking */
   public void delete(Booking booking){
        bookingRepository.delete(booking);
   }
}
