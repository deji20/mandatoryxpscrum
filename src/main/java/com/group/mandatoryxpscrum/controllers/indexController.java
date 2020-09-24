package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.handlers.ActivityHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    private ActivityHandler activityHandler = new ActivityHandler();

    // Get mapping for startpage. Adds list of activities to model object.
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("activities", activityHandler.readAll());
        return "index";
    }

 }
