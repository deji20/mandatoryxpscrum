package com.group.mandatoryxpscrum.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statistic")
    private Integer Id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statistic_booking_id", referencedColumnName = "id_booking")
    private Booking booking;

    public Statistic(){}

    public Statistic(Booking booking) {
        this.booking = booking;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}