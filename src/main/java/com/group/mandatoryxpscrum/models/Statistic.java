package com.group.mandatoryxpscrum.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

public class Statistic {
    private LocalDate date;
    private HashMap<Activity, List<Booking>> bookingsByActivity;
    private HashMap<Activity, Integer> equipmentUsed;
    private int TotalEquipment;
    private int brokenEquipment;

    public Statistic(){}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public HashMap<Activity, List<Booking>> getBookingsByActivity() {
        return bookingsByActivity;
    }

    public void setBookingsByActivity(HashMap<Activity, List<Booking>> bookingsByActivity) {
        this.bookingsByActivity = bookingsByActivity;
    }

    public HashMap<Activity, Integer> getEquipmentUsed() {
        return equipmentUsed;
    }

    public void setEquipmentUsed(HashMap<Activity, Integer> equipmentUsed) {
        this.equipmentUsed = equipmentUsed;
    }

    public int getTotalEquipment() {
        return TotalEquipment;
    }

    public void setTotalEquipment(int totalEquipment) {
        TotalEquipment = totalEquipment;
    }

    public int getBrokenEquipment() {
        return brokenEquipment;
    }

    public void setBrokenEquipment(int brokenEquipment) {
        this.brokenEquipment = brokenEquipment;
    }
}