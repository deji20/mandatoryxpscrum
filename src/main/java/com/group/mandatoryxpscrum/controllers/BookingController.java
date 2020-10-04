package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.repositories.BookingRepository;
import com.group.mandatoryxpscrum.data.services.ActivityService;
import com.group.mandatoryxpscrum.data.services.BookingService;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Booking;
import com.group.mandatoryxpscrum.models.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Controller

@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;
    @Autowired
    ActivityService activityService;

    //returns create booking view and adds activities and ids
    @GetMapping("/book")
    public String book(Model model, @RequestParam int id){
        List<Activity> activities = activityService.fetchAll();

        HashMap<String, Integer> nameAndId= new HashMap<>();
        for (Activity activity:activities){ nameAndId.put(activity.getName(), activity.getId());}
        model.addAttribute("nameAndId", nameAndId);
        model.addAttribute("id", id);

        List<Booking> allBookings = bookingService.fetchAll();

        Booking booking = allBookings.get(0);
        booking.setTime(booking.getTime().minusHours(2).minusMinutes(40));
        allBookings.stream().forEach((book) ->
                {
                    System.out.println("Hour: " + (booking.getTime().getHour() - book.getTime().getHour()));
                    System.out.println("Minutes: " + (booking.getTime().getMinute() - book.getTime().getMinute()));
                }
        );

        return "/booking/createBooking";
    }

    //saves new booking to database adding activity and equipment
    @PostMapping("/create")
    public String create(@ModelAttribute Booking booking, @RequestParam int activityId){
        booking.setActivity(activityService.fetchById(activityId));
        System.out.println(booking);
        return " ";
    }


    @PostMapping("/delete/")
    public String deleteBooking(@RequestParam int bookingId, Model model) {
        Booking booking = bookingService.fetchById((bookingId));
        bookingService.delete(booking);
        model.addAttribute("booking", bookingService.fetchAll());
        return "/booking/bookingInfo";
    }

    @GetMapping("/editBookingInfo")
    public String editBookingInfo(Model model, @RequestParam ("bookingId") String bookingId){
        Booking booking = bookingService.fetchById(Integer.parseInt(bookingId));
        model.addAttribute("book", booking);
        return "/booking/editBookingInfo";
    }

    @PostMapping("/updateBooking")
    public String updateBooking(Model model, @ModelAttribute("book") Booking booking) {
        bookingService.save(booking);
        model.addAttribute("booking", bookingService.fetchAll());
        return "/booking/bookingInfo";
    }

//    @RequestMapping("/updateBooking/{id}")
//    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
//        ModelAndView mav = new ModelAndView("editBookingInfo");
//        Booking booking = bookingService.fetchById(id);
//        mav.addObject("book", booking);
//        return mav;
//    }

    @GetMapping("/bookinginfo")
    public String viewBooking(Model model) {
        model.addAttribute("booking", bookingService.fetchAll());
        return "/booking/bookingInfo";
    }

    //tells the method to seriallize the activity and return it in json
    @GetMapping(value="/returnActivity", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public Activity getActivity(@RequestParam int activityId){
        Activity activity = activityService.fetchById(activityId);
        //remove circular dependecies since they mess with JSON
        activity.setEquipment(null);
        activity.getPricing().setActivity(null);
        activity.getRules().setActivity(null);

        return activity;
    }

    //returns equipment based on booking
    @GetMapping(value="/returnEquipment", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public List<Equipment> returnEquipment(@RequestParam int activityId,
                                @RequestParam LocalDate date,
                                @RequestParam LocalTime time,
                                @RequestParam int duration){
        List<Booking> bookings = bookingService.fetchAllByDay(date);
        //get all equipment
        List<Equipment> equipmentList = activityService.fetchById(activityId).getEquipment();
        //filter out broken equipment
        equipmentList = equipmentList.stream().filter((equipment) -> equipment.isAvailable()).collect(Collectors.toList());
        //filter equipment so that no equipment overlaps in time
        for (Booking booking:bookings) {
            if(time.compareTo(booking.getTime()) > duration){
                //removes Equipment from list if bookings overlap
                equipmentList.removeAll(booking.getBookedEquipment());
            }
        }
        return equipmentList;
    }

    private List<Equipment> getAvailEquipment(LocalDate date, LocalTime time, int activityId, int duration){
        List<Booking> bookings = bookingService.fetchAllByDay(date);
        //get all equipment
        List<Equipment> equipmentList = activityService.fetchById(activityId).getEquipment();
        //filter out broken equipment
        equipmentList = equipmentList.stream().filter((equipment) -> equipment.isAvailable()).collect(Collectors.toList());
        //filter equipment so that no equipment overlaps in time
        int timeInMin = (time.getHour() * 60) + (time.getMinute());
        for (Booking booking:bookings) {
            int bookingTimeInMin = (booking.getTime().getHour() * 60) + (booking.getTime().getMinute());
            //NOT WORKING need to filter based +/- on duration
            if(timeInMin + bookingTimeInMin  > duration)
                //removes Equipment from list if bookings overlap
                equipmentList.removeAll(booking.getBookedEquipment());
        };
        return equipmentList;
    }
}
