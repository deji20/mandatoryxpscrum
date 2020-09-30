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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String viewEquipment(Model model, @PathVariable int id) {
        Activity activity = activityService.fetchById(id);
        model.addAttribute("equipment", activity.getEquipment());
        return "equipment";
    }

    @GetMapping("/activity/equipment/delete")
    public String deleteEquipment(@RequestParam("id") String id) {
        Equipment equipment = equipmentService.fetchById(Integer.parseInt(id));
        equipmentService.delete(equipment);
        return "redirect:/";
    }

    @GetMapping("/activity/{id}/equipment/new")
    public String addEquipment(@PathVariable String id, Model model) {
        model.addAttribute("equipment", new Equipment());
        model.addAttribute("activityId", id);
        return "newActivity";
    }

    @PostMapping("/activity/{id}/equipment/new")
    public String addEquipment(@ModelAttribute("equipment") Equipment equipment) {

        return "redirect:/activity/{id}/equipment";
    }

    @GetMapping("/bookinginfo")
    public String equipment() {
        return "bookingInfo";
    }

    @GetMapping("/activity/equipment/updateStatus")
    public String equipmentChange(Model model, @RequestParam ("activityId") String activityId, @RequestParam ("equipmentId") int equipmentId) {
        Activity activity = activityService.fetchById(Integer.parseInt(activityId));
        Equipment equipment = activity.getSpecificEquipment(equipmentId);
        model.addAttribute("specificEquipment", equipment);
        //model.addAttribute("activity", activity);
        return "changeStatus";
    }

    /*------------------------------------------
    Post mapping responsible for saving changed equipment status.
    In order to use ModelAttribute, one has to pass all attributes through the html form
     -------------------------------------------*/
    @PostMapping("/activity/equipment/updateStatus")
    public String changeEquipmentStatus(@ModelAttribute("specificEquipment") Equipment equipment) {
        equipmentService.save(equipment);
        return "redirect:/";
    }

    @GetMapping("/manager")
    public String managerHome(Model model){
        model.addAttribute("activities", activityService.fetchAll());
        return "managerIndex";
    }

    @GetMapping("/create")
    public String createActivty(Model model){
        //instantierer tomme activity, rules, pricing objekter
        Activity activity = new Activity();
        //setter billede til at være default billede
        activity.setImage("default.jpg");
        Pricing pricing = new Pricing();
        Rules rules = new Rules();
        //tilføjer objekter til model
        model.addAttribute("activity", activity);
        model.addAttribute("pricing", pricing);
        model.addAttribute("rules", rules);
        return "createActivity";
    }

    @PostMapping("/create")
    public String createActivty(@ModelAttribute("activity") Activity activity,
                                @ModelAttribute("rules") Rules rules,
                                @ModelAttribute("pricing") Pricing pricing){
        activityService.save(activity);
        rules.setActivity(activity);
        pricing.setActivity(activity);
        activity.setPricing(pricing);
        activity.setRules(rules);
        activityService.save(activity);
        return "redirect:/";
    }


    /*------------------------------------------
    Responsible for editing of activity
     -------------------------------------------*/
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

        //changes all the rules
        activity.getRules().setAgeLimit(age);
        activity.getRules().setHeightLimit(height);
        activity.getRules().setMaxCapacity(capacity);
        activity.getRules().setDuration(duration);
        //changes the Pricing
        activity.getPricing().setStandard(standardPrice);
        activity.getPricing().setDiscount(discount);
        activity.getPricing().setExtraInfo(extraCharges);
        activity.setDescription(description);

        activityService.save(activity);
        return "redirect:/manager";
    }

 }
