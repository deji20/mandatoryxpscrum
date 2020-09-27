package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.services.ActivityService;
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

    @GetMapping("/updateStatus")
    public String equipmentChange(Model model, @RequestParam ("id") int id) {
        Equipment equipment = equipmentService.fetchById(id);
        model.addAttribute("specificEquipment", equipment);
        return "changeStatus";
    }

    @GetMapping("/manager")
    public String managerHome(Model model){
        model.addAttribute("activities", activityService.fetchAll());
        return "managerIndex";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam int id){
        model.addAttribute("activity", activityService.fetchById(id));
        return "editActivity";
    }

    @PostMapping("/update")
    public String commitEdit(@RequestParam Integer id,
                             @RequestParam Integer age, @RequestParam Integer height,
                             @RequestParam Integer capacity, @RequestParam Integer duration,
                             @RequestParam Integer standardPrice, @RequestParam Integer discount,
                             @RequestParam String extraCharges, @RequestParam String description){
        Activity activity = activityService.fetchById(id);

        activity.setRules(new Rules(age, height, capacity, duration, activity));
        activity.setPricing(new Pricing(standardPrice, discount, extraCharges, activity));
        activity.setDescription(description);

        activityService.save(activity);
        return "index";
    }

 }
