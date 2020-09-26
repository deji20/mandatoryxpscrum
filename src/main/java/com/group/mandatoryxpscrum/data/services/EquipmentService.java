package com.group.mandatoryxpscrum.data.services;

import com.group.mandatoryxpscrum.data.repositories.EquipmentRepository;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;

    // Gemmer ændringer lavet til Equipment object i databasen

    public void save(Equipment equipment){
        equipmentRepository.save(equipment);
    }

    /** Gør det samme som ovenstående metode men for en liste af Equipment */
    public void saveAll(List<Equipment> equipment){
        equipmentRepository.saveAll(equipment);
    }

    /** Giver et Equipment object tilbage med alt data udfyldt (int id = id_equipment i database) */
    public Equipment fetchById(int id){
        Optional<Equipment> optional = equipmentRepository.findById(id);
        return optional.get();
    }

    /** Giver en liste af alle equipment fra databasen */
    public List<Equipment> fetchAll(){
        return equipmentRepository.findAll();
    }

    /** Sletter en equipment */
    public void delete(Equipment equipment){
        equipmentRepository.deleteById(equipment.getId());
    }

}


