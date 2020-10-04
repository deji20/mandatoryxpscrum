package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findBookingsByDate(LocalDate date);

    @Query("SELECT b FROM Booking b WHERE b.phoneNumber LIKE %?1%")
    List<Booking> findAll(String keyword);
}


