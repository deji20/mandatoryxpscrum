package com.group.mandatoryxpscrum.data.services;

import com.group.mandatoryxpscrum.data.repositories.ActivityRepository;
import com.group.mandatoryxpscrum.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    public void add(Activity activity){
        activityRepository.save(activity);
    }

    //TODO
    public Activity selectById(int id){
        Optional<Activity> optional = activityRepository.findById(id);
        return optional.get();
    }

    public List<Activity> selectAll(){
        return activityRepository.findAll();
    }

    //TODO
    public void update(){ }

    //TODO
    public void delete(int id){
        activityRepository.deleteById(id);
    }

}
