package com.group.mandatoryxpscrum.data.services;

import com.group.mandatoryxpscrum.data.repositories.ActivityRepository;
import com.group.mandatoryxpscrum.data.repositories.PricingRepository;
import com.group.mandatoryxpscrum.data.repositories.RulesRepository;
import com.group.mandatoryxpscrum.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    PricingRepository pricingRepository;

    @Autowired
    RulesRepository rulesRepository;

    public void add(Activity activity){
        activityRepository.save(activity);
    }

    //TODO
    public Activity select(int id){ return null; }

    public List<Activity> selectAll(){
        return activityRepository.findAll();
    }

    //TODO
    public void update(){ }

    //TODO
    public void delete(int id){ }

}
