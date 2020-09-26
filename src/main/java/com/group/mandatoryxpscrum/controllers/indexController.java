package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.services.ActivityService;
import com.group.mandatoryxpscrum.data.services.EquipmentService;
import com.group.mandatoryxpscrum.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class indexController {

    @Autowired
    ActivityService activityService;
    @Autowired
    EquipmentService equipmentService;

    // Get mapping for startpage. Adds list of activities to model object.
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("activities", activityService.fetchAll());
        return "index";
    }

    /*-----------------------------------------------
    Mapping for viewing equipment of chosen activity
    @PathVariable used for getting id from url
    -----------------------------------------------*/
    @GetMapping("/activity/{id}/equipment")
    public String equipment(Model model, @PathVariable String id) {
        Activity activity = activityService.fetchById(Integer.parseInt(id));
        model.addAttribute("equipment", activity.getEquipment());
        return "equipment";
    }

 }
