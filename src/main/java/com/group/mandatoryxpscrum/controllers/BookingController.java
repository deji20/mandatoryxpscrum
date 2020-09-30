package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.repositories.BookingRepository;
import com.group.mandatoryxpscrum.data.services.BookingService;
import com.group.mandatoryxpscrum.models.Booking;
import com.group.mandatoryxpscrum.models.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;


@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    //Create booking
    @GetMapping("/create")
    public String create(){
        return "/booking/create";
    }

    @PostMapping("/delete/")
    public String deleteBooking(@RequestParam int bookingId, Model model) {
        Booking booking = bookingService.fetchById((bookingId));
        bookingService.delete(booking);
        model.addAttribute("booking", bookingService.fetchAll());
        return "/booking/bookingInfo";

    }

    @PostMapping("/updateBooking")
    public String updateBooking() {
        return "/booking/bookingInfo";
    }

    @GetMapping("/bookinginfo")
    public String viewBooking(Model model) {
        model.addAttribute("booking", bookingService.fetchAll());
        return "/booking/bookingInfo";
    }



}
