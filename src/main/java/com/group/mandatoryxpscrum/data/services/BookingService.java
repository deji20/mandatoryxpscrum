package com.group.mandatoryxpscrum.data.services;

import com.group.mandatoryxpscrum.data.repositories.BookingRepository;
import com.group.mandatoryxpscrum.models.Booking;
import com.group.mandatoryxpscrum.models.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    public Booking save(Booking booking){
        return bookingRepository.save(booking);
   }


    /** Giver et Booking object tilbage med alt data udfyldt (int id = id_booking i database) */
    public Booking fetchById(int id){
        Optional<Booking> optional = bookingRepository.findById(id);
        return optional.get();
    }

    /** Giver en liste af alle booking fra databasen */
    public List<Booking> fetchAll(){
        return bookingRepository.findAll();
    }
}