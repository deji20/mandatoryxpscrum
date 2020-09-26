package com.group.mandatoryxpscrum.controllerTests;

import com.group.mandatoryxpscrum.controllers.IndexController;
import com.group.mandatoryxpscrum.data.services.ActivityService;
import com.group.mandatoryxpscrum.models.Activity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestParam;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class IndexTest {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private IndexController controller;

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
