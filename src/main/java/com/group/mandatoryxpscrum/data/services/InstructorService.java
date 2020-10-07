package com.group.mandatoryxpscrum.data.services;

import com.group.mandatoryxpscrum.data.repositories.ActivityRepository;
import com.group.mandatoryxpscrum.data.repositories.InstructorRepository;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    /** Spring bygger automatisk et ActivityRepository op på baggrund af annotationer i Activity i models */
    @Autowired
    InstructorRepository instructorRepository;

    /** Gemmer ændringer lavet til Activity object i databasen,
     * gemmer også ændringer lavet child objekter i activity (Pricing, Rules og liste af Equipment)
     * hvis du fjerner et Equipment objekt fra listen af Equipment i Activity og derefter kalder på denne metode
     * så vil det Equipment du har fjernet, blive slettet i databasen */
    public void save(Instructor instructor){
        instructorRepository.save(instructor);
    }

    /** Gør det samme som ovenstående metode men for en liste af Activity */
    public void saveAll(List<Instructor> instructor){
        instructorRepository.saveAll(instructor);
    }

    /** Giver et Activity object tilbage med alt data udfyldt (int id = id_activity i database) */
    public Instructor fetchById(int id){
        Optional<Instructor> optional = instructorRepository.findById(id);
        return optional.get();
    }

    /** Giver en liste af alle activites fra databasen */
    public List<Instructor> fetchAll(){
        return instructorRepository.findAll();
    }

    /** Sletter en activity og den tilhørende pricing, rules og equipment */
    public void delete(Instructor instructor){
        instructorRepository.deleteById(instructor.getId());
    }
}
