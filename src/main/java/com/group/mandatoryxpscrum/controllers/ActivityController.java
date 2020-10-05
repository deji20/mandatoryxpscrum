package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.services.ActivityService;
import com.group.mandatoryxpscrum.data.services.EquipmentService;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;
    @Autowired
    EquipmentService equipmentService;

    //
    @GetMapping("/createActivity")
    public String createActivity(Model model) {
        return "equipment/equipment";
    }


    /*-----------------------------------------------
Mapping for viewing equipment of chosen activity
@PathVariable used for getting id from url
-----------------------------------------------*/
    @GetMapping("/{id}/equipment")
    public String viewEquipment(Model model, @PathVariable int id) {
        Activity activity = activityService.fetchById(id);
        model.addAttribute("equipment", activity.getEquipment());
        model.addAttribute("activity", activity);
        model.addAttribute("newEquipment", new Equipment());
        return "equipment/equipment";
    }

    @GetMapping("/equipment/delete")
    public String deleteEquipment(@RequestParam("id") String equipmentId) {
        Equipment equipment = equipmentService.fetchById(Integer.parseInt(equipmentId));
        equipmentService.delete(equipment);
        return "redirect:/";
    }

    @GetMapping("/{id}/equipment/new")
    public String addEquipment(@PathVariable String id, Model model) {
        model.addAttribute("equipment", new Equipment());
        model.addAttribute("activityId", id);
        return "equipment/equipment";
    }

    @PostMapping("/{id}/equipment/new")
    public String addEquipment(@ModelAttribute("equipment") Equipment equipment, @PathVariable("id") int id) {
        Activity activity = activityService.fetchById(id);
        equipment.setActivity(activity);
        activity.addEquipment(equipment);
        activityService.save(activity);
        return "redirect:/activity/{id}/equipment";
    }

    @GetMapping("/equipment/updateStatus")
    public String equipmentChange(Model model, @RequestParam ("activityId") String activityId, @RequestParam ("equipmentId") int equipmentId) {
        Activity activity = activityService.fetchById(Integer.parseInt(activityId));
        Equipment equipment = activity.getSpecificEquipment(equipmentId);
        model.addAttribute("specificEquipment", equipment);
        return "equipment/changeStatus";
    }

    /*------------------------------------------
    Post mapping responsible for saving changed equipment status.
    In order to use ModelAttribute, one has to pass all attributes through the html form
     -------------------------------------------*/
    @PostMapping("/equipment/updateStatus")
    public String changeEquipmentStatus(@ModelAttribute("specificEquipment") Equipment equipment) {
        equipmentService.save(equipment);
        return "redirect:/";
    }
}
