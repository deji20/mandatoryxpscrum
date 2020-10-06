package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.services.ActivityService;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Pricing;
import com.group.mandatoryxpscrum.models.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ActivityService activityService;

    @GetMapping("")
    public String managerHome(Model model){
        model.addAttribute("activities", activityService.fetchAll());
        return "manager/managerIndex";
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

    /*------------------------------------------
    Responsible for editing of activity
     -------------------------------------------*/
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam int id){
        model.addAttribute("activity", activityService.fetchById(id));
        return "activity/editActivity";
    }
}
