package com.group.mandatoryxpscrum.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/booking")
public class BookingController {
    //Create booking
    @GetMapping("/create")
    public String create(){
        return "/booking/create";
    }
}
