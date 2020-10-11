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
        LocalDate date = LocalDate.now();
        //get last monday of given date
        date = date.minusDays(date.getDayOfWeek().getValue()-1);

        List<Statistic> statistics = new ArrayList<>();
        for (int i = 7; i > 0; i--) {
            Statistic statistic = new Statistic();
            statistic.setDate(date.plusDays(i));
            statistic.setBookingsByActivity(getActivityBookingsByDate(statistic.getDate()));
            HashMap<Activity, Integer> equipment = new HashMap<>();
            for(Activity activity: activityService.fetchAll()) {
                equipment.put(activity, statistic.getBookingsByActivity().get(activity).stream().mapToInt(Booking::getAmount).sum());
            }
            statistic.setEquipmentUsed(equipment);
            statistics.add(statistic);
        }
        Statistic total = new Statistic();
        HashMap<Activity, Integer> equipment = new HashMap<>();
        HashMap<Activity, List<Booking>> bookings = new HashMap<>();
        for (Statistic s: statistics) {
            for(Activity activity: activityService.fetchAll()){
                bookings.putIfAbsent(activity, new ArrayList<Booking>());
                bookings.get(activity).addAll(s.getBookingsByActivity().get(activity));

                equipment.putIfAbsent(activity, 0);
                equipment.put(activity, bookings.get(activity).stream().mapToInt(Booking::getAmount).sum());
            }
        }
        total.setBookingsByActivity(bookings);
        total.setEquipmentUsed(equipment);

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
