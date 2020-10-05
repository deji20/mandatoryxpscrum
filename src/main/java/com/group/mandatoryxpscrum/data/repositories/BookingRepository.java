package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.models.Booking;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "SELECT * FROM Booking b WHERE date LIKE ?1", nativeQuery = true)
    List<Booking> findBookingsByDate(String date);

    @Query("SELECT b FROM Booking b WHERE b.phoneNumber LIKE %?1%")
    List<Booking> findAll(String keyword);
}


