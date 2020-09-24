package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.models.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Integer> {

}
