package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.repositories.BookingRepository;
import com.group.mandatoryxpscrum.data.services.ActivityService;
import com.group.mandatoryxpscrum.data.services.BookingService;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Booking;
import com.group.mandatoryxpscrum.models.Equipment;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Book;
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

        return "/booking/createBooking";
    }

    //saves new booking to database adding activity and equipment
    @PostMapping("/create")
    public String create(Model model, @ModelAttribute Booking booking, @RequestParam int activityId){
        booking.setActivity(activityService.fetchById(activityId));
        List<Equipment> availableEquipment = getAvailEquipment(booking);
        booking.setBookedEquipment(availableEquipment.subList(0, booking.getAmount()));

        bookingService.save(booking);
        return "redirect:/booking/bookinginfo";
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
    public String viewBooking(Model model, @Param("keyword") String keyword) {
        model.addAttribute("booking", bookingService.listAll(keyword));
        return "/booking/bookingInfo";
    }

    @GetMapping("/statistics")
    public String viewStatistics(Model model) {
        LocalDate date = LocalDate.parse("2020-04-11");
        System.out.println();
        date = date.minusDays(date.getDayOfWeek().getValue()-1);
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (int i = 0; i < days.length; i++) {
            model.addAttribute(days[i], getActivityBookingsByDate(date.plusDays(i)));
        }
        return "/statistics";
    }

    private HashMap<Activity, List<Booking>> getActivityBookingsByDate(LocalDate date){
        HashMap<Activity, List<Booking>> bookingByActivity = new HashMap<>();
        for(Activity activity : activityService.fetchAll()){
            bookingByActivity.put(activity, bookingService.findBookingByDateAndActivity(date, activity.getId()));
        }
        return bookingByActivity;
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
    @PostMapping(value="/returnEquipment", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public List<Equipment> returnEquipment(@RequestParam int activityId, @ModelAttribute Booking booking){
        System.out.println(booking);
        booking.setActivity(activityService.fetchById(activityId));
        List<Equipment> list = getAvailEquipment(booking);

        //again removing circular dependency
        for(Equipment equip: list){
            equip.setActivity(null);
        }
        return list;
        }

    private List<Equipment> getAvailEquipment(Booking booking){
        System.out.println(booking.getTime());
        List<Booking> bookings = bookingService.fetchAllByDay(booking.getDate());
        //get all equipment for this activity
        List<Equipment> equipmentList = booking.getActivity().getEquipment();
        //filter out broken equipment
        equipmentList = equipmentList.stream().filter(Equipment::isAvailable).collect(Collectors.toList());

        //filter equipment so that no equipment overlaps in time
        int bookingTime = (booking.getTime().getHour() * 60) + (booking.getTime().getMinute());
        int duration = booking.getActivity().getRules().getDuration();
        for (Booking b:bookings) {
            int bTime = (b.getTime().getHour() * 60) + (b.getTime().getMinute());

            //find time difference between parameter event and iterator event
            int diff = 0;
            if(bTime > bookingTime){ diff = bTime - bookingTime;}
            else if(bTime < bookingTime){ diff = bookingTime - bTime;}
            System.out.println("diff: " +diff);
            System.out.println("duration: " + duration);

            //removes available equipment from list if bookings overlap
            if(diff < duration){
                for(Equipment equip : b.getBookedEquipment()) {
                    if(equipmentList.contains(equip)){
                        equipmentList.remove(equip);
                    }
                }
            }
        };
        return equipmentList;
    }
}
