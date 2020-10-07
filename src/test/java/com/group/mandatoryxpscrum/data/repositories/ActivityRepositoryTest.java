package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.models.Activity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ActivityRepositoryTest {

    @Autowired
    ActivityRepository repository;

    @Test
    void injectedComponentsAreNotNull(){
        assertNotNull(repository);
    }

    @Test
    void testFetchByNonExistingId(){
        assertThrows(NoSuchElementException.class, () -> repository.findById(1).get());
    }

    @Test
    void findAllActivitiesIsNullTest(){
        int expected = 0;

        List<Activity> activities = repository.findAll();
        int actual = activities.size();

        assertEquals(expected, actual);
    }

    @Test
    void activityIsSavedTest(){
        int expected = 1;

        Activity activity = new Activity();

        repository.save(activity);

        int actual = repository.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void activityIsDeletedTest(){
        int expected = 0;

        Activity activity = new Activity();
        repository.save(activity);

        Activity activity1 = repository.findAll().get(0);
        repository.delete(activity1);
        int actual = repository.findAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void activityNameIsUpdatedTest(){
        String expected = "Bowling";

        //insert new Activity to be updated later
        Activity activity = new Activity();
        activity.setName("New Activity");
        repository.save(activity);

        Activity activity1 = repository.findAll().get(0);
        activity1.setName("Bowling");
        repository.save(activity1);

        String actual = repository.findAll().get(0).getName();

        assertEquals(expected, actual);

    }

    @Test
    void activityImageIsUpdatedTest(){
        String expected = "UpdatedImage";

        //insert new Activity to be updated later
        Activity activity = new Activity();
        activity.setName("OldImage");
        repository.save(activity);

        Activity activity1 = repository.findAll().get(0);
        activity1.setName("UpdatedImage");
        repository.save(activity1);

        String actual = repository.findAll().get(0).getName();

        assertEquals(expected, actual);

    }

}