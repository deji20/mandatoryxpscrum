package com.group.mandatoryxpscrum.data.services;

import com.group.mandatoryxpscrum.data.repositories.ActivityRepository;
import com.group.mandatoryxpscrum.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Service klasse til brug i controllers når der skal arbejdes med activities, pricing, rules og equipment */
@Service
public class ActivityService {

    /** Spring bygger automatisk et ActivityRepository op på baggrund af annotationer i Activity i models */
    @Autowired
    ActivityRepository activityRepository;

    /** Gemmer ændringer lavet til Activity object i databasen,
    * gemmer også ændringer lavet child objekter i activity (Pricing, Rules og liste af Equipment)
    * hvis du fjerner et Equipment objekt fra listen af Equipment i Activity og derefter kalder på denne metode
    * så vil det Equipment du har fjernet, blive slettet i databasen */
    public void save(Activity activity){
        activityRepository.save(activity);
    }

    /** Gør det samme som ovenstående metode men for en liste af Activity */
    public void saveAll(List<Activity> activities){
        activityRepository.saveAll(activities);
    }

    /** Giver et Activity object tilbage med alt data udfyldt (int id = id_activity i database) */
    public Activity fetchById(int id){
        Optional<Activity> optional = activityRepository.findById(id);
        return optional.get();
    }

    /** Giver en liste af alle activites fra databasen */
    public List<Activity> fetchAll(){
        return activityRepository.findAll();
    }

    /** Sletter en activity og den tilhørende pricing, rules og equipment */
    public void delete(Activity activity){
        activityRepository.deleteById(activity.getId());
    }

}
