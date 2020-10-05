package com.group.mandatoryxpscrum.controllerTests;

import com.group.mandatoryxpscrum.controllers.ActivityController;
import com.group.mandatoryxpscrum.controllers.ManagerController;
import com.group.mandatoryxpscrum.controllers.indexController;
import com.group.mandatoryxpscrum.data.services.ActivityService;
import com.group.mandatoryxpscrum.models.Activity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class ManagerTest {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ManagerController controller;

    //edit activity rules prices and description and updates database
    @ParameterizedTest
    @ValueSource(ints = {1000, 1, -1, 160})
    public void returnsTrueIfCommitMethodEditsDatabase(int height){
        //finds a random activity
        Activity activity = activityService.fetchAll().get(0);
        //changes Height
        activity.getRules().setHeightLimit(height);

        //calls commiting method
        controller.commitEdit(activity.getId(), activity.getRules().getAgeLimit(),
                activity.getRules().getHeightLimit(), activity.getRules().getMaxCapacity(),
                activity.getRules().getDuration(), activity.getPricing().getStandard(),
                activity.getPricing().getDiscount(), activity.getPricing().getExtraInfo(),
                activity.getDescription());

        //tests the expected height against the one saved in the db
        int actualHeight = activityService.fetchById(activity.getId()).getRules().getHeightLimit();
        assertEquals(height, actualHeight, "height was not saved");
    }
}
