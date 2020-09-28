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
    public String equipment(Model model, @PathVariable int id) {
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
        activity.getPricing().setStandard(standardPrice);
        activity.getPricing().setDiscount(discount);
        activity.getPricing().setExtraInfo(extraCharges);
        activity.setDescription(description);

        activityService.save(activity);
        return "redirect:/manager";
    }

 }
