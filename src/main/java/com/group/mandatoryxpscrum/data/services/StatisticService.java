package com.group.mandatoryxpscrum.data.services;

import com.group.mandatoryxpscrum.data.repositories.StatisticRepository;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {

    @Autowired
    StatisticRepository statisticRepository;

    public List<Statistic> findAll(){
        return statisticRepository.findAll();
    }

}
