package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.models.Booking;
import com.group.mandatoryxpscrum.models.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, Integer> {

    @Query("SELECT b FROM Booking b WHERE b.activity.name NOT LIKE %?1%")
    List<Booking> findAll(String keyword);
}
