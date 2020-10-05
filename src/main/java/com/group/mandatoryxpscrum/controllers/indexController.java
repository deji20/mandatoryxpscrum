package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.services.ActivityService;
import com.group.mandatoryxpscrum.data.services.BookingService;
import com.group.mandatoryxpscrum.data.services.EquipmentService;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Equipment;
import com.group.mandatoryxpscrum.models.Pricing;
import com.group.mandatoryxpscrum.models.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class indexController {

    @Autowired
    ActivityService activityService;
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    BookingService bookingService;

    // Get mapping for startpage. Adds list of activities to model object.
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("activities", activityService.fetchAll());
        return "index";
    }



    /*------------------------------------------
    Responsible for editing of activity
     -------------------------------------------*/
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam int id){
        model.addAttribute("activity", activityService.fetchById(id));
        return "activity/editActivity";
    }

 }
