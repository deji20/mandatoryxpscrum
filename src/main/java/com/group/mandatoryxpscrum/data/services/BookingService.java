package com.group.mandatoryxpscrum.data.services;

import com.group.mandatoryxpscrum.data.repositories.BookingRepository;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public Booking save(Booking booking){
        return bookingRepository.save(booking);
    }
    public Booking fetchById(int id){
        Optional<Booking> optional = bookingRepository.findById(id);
        return optional.get();
    }
    public List<Booking> fetchAllByDay(LocalDate date){ return bookingRepository.findBookingsByDate(date.toString());}
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
    public List<Booking> listAll(String keyword){
        if (keyword != null){
            return bookingRepository.findAll(keyword);
        }
        return bookingRepository.findAll();
    }

}
