package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.services.ActivityService;
import com.group.mandatoryxpscrum.data.services.BookingService;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Booking;
import com.group.mandatoryxpscrum.models.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    BookingService bookingService;
    @Autowired
    ActivityService activityService;

    @GetMapping("")
    public String viewStatistics(Model model) {
        model.addAttribute("book",bookingService.fetchAll());
        //set random date change to parse() to now() to get current bookings
        LocalDate date = LocalDate.parse("2020-04-11");
        //get last monday of given date
        date = date.minusDays(date.getDayOfWeek().getValue()-1);

        List<Statistic> statistics = new ArrayList<>();
        for (int i = 7; i > 0; i--) {
            Statistic statistic = new Statistic();
            statistic.setDate(date.plusDays(i));
            statistic.setBookingsByActivity(getActivityBookingsByDate(statistic.getDate()));
            statistic.setEquipmentUsed(bookingService.fetchAllByDay(statistic.getDate()).stream().mapToInt((b) -> b.getBookedEquipment().size()).sum());
            statistics.add(statistic);
        }
        Statistic total = new Statistic();
        total.setBookingsByActivity(new HashMap<>());
        for (Statistic s: statistics) {
            total.setEquipmentUsed(s.getEquipmentUsed());
            total.getBookingsByActivity().putAll(s.getBookingsByActivity());
        }
        System.out.println(total.getBookingsByActivity());
        model.addAttribute("total", total);
        model.addAttribute("statistics", statistics);
        return "/statistics";
    }

    private HashMap<Activity, List<Booking>> getActivityBookingsByDate(LocalDate date){
        HashMap<Activity, List<Booking>> bookingByActivity = new HashMap<>();
        for(Activity activity : activityService.fetchAll()){
            bookingByActivity.put(activity, bookingService.findBookingByDateAndActivity(date, activity.getId()));
        }
        return bookingByActivity;
    }
}
