package com.group.mandatoryxpscrum.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_booking")
    private Integer id;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Column(name="time")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    @Column(name="instructor")
    private String instructor;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="customer_name")
    private String customerName;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Equipment> bookedEquipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_activity_id", referencedColumnName = "id_activity")
    private Activity activity;

    public Booking(){}

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List<Equipment> getBookedEquipment() {
        return bookedEquipment;
    }

    public void setBookedEquipment(List<Equipment> bookedEquipment) {
        this.bookedEquipment = bookedEquipment;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


}
