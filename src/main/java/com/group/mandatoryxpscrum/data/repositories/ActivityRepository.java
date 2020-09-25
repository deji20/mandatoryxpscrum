package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** et interface der nedarver fra et Data Java Persistence API af typen Activity
 * den anden type (Integer) er til for API'et kan identificere hvilken type primary key er.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer>{

}