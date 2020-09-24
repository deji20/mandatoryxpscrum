package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer>{

}
