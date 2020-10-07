package com.group.mandatoryxpscrum.data.repositories;


import com.group.mandatoryxpscrum.models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>  {

}
