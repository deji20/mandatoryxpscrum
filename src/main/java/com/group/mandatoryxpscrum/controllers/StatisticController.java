package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.repositories.StatisticRepository;
import com.group.mandatoryxpscrum.data.services.StatisticService;
import com.group.mandatoryxpscrum.models.Booking;
import com.group.mandatoryxpscrum.models.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @GetMapping("/statistics")
    public String statistics(Model model){

//        List<Statistic> all = statisticService.findAll();
//        Statistic statistic = all.get(0);
//        Booking booking = statistic.getBooking();
//        System.out.println(booking.getDate());




        model.addAttribute("statistics", statisticService.findAll());





        return "statistics";
    }

}
