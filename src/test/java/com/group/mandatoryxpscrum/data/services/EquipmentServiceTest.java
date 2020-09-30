package com.group.mandatoryxpscrum.data.services;

import com.group.mandatoryxpscrum.models.Equipment;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
class EquipmentServiceTest {

    @Autowired
    EquipmentService equipmentService;

    @Test
    void createEquipment() {

    }

    @Test
    void findByAvailable() {
//        int expected = 28;
//
//        List<Equipment> availableEquipment = equipmentService.findByAvailable(5, true);
//        int actual = availableEquipment.size();
//
//        assertEquals(expected, actual);
    }

}