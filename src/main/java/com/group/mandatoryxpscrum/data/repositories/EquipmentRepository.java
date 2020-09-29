package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
}
