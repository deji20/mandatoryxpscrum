package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.models.Activity;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
class ActivityRepositoryTest {

    @Autowired
    ActivityRepository repository;

    MockDatabasePopulator populator;

    @BeforeEach
    public void init(){
        populator= new MockDatabasePopulator();
        repository.save(populator.getNewTestActivity());
    }

//    @Test
//    void testNewActivityIsSaved() throws NoSuchElementException{
//        Activity a2 = repository.findById(1).get();
//        assertNotNull(a2);
//    }

//    @Test
//    void testActivityNameChangeIsSaved(){
//        List repository.findAll();
//    }
//
    @Test
    void testFetchByNonExistingId(){
        assertThrows(NoSuchElementException.class, () -> repository.findById(1).get());
    }
//
//    @Test
//    void testAllActivitiesChangesAreSaved() {
//        List<Activity> populationData = populator.getNewTestActivityList();
//        repository.saveAll(populationData);
//
//        List<Activity> a1 = repository.findAll();
//        Activity activity = a1.get(0);
//        activity.setName("LOL");
//        repository.saveAll(a1);
//
//        List<Activity> a2 =repository.findAll();
//
//        assertEquals(a1.get(0).getName(), a2.get(0).getName());
//    }

    @Test
    void fetchById() {
    }

    @Test
    void fetchAll() {
    }

    @Test
    void delete() {
    }

}