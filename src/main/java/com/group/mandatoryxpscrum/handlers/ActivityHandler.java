package com.group.mandatoryxpscrum.handlers;

import com.group.mandatoryxpscrum.data.DBManager;
import com.group.mandatoryxpscrum.data.DataMapper;
import com.group.mandatoryxpscrum.data.mappers.ActivityMapper;
import com.group.mandatoryxpscrum.data.mappers.PricingMapper;
import com.group.mandatoryxpscrum.data.mappers.RulesMapper;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.ModelEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityHandler {

    Connection connection = DBManager.getConnection();
    DataMapper activityMapper = new ActivityMapper(connection);
    DataMapper pricingMapper = new PricingMapper(connection);
    DataMapper rulesMapper = new RulesMapper(connection);

    //inserts the fields of the activity into its appropriate tables
    public void create(ModelEntity entity){
        try{
            //starts a transaction with database (makes sure changes aren't automatically saved. Instead the commit() method needs to be evoked)
            connection.setAutoCommit(false);

            //inserts rows in the database for the activity, then its pricing and lastly its rules
            activityMapper.insert(entity);
            pricingMapper.insert(entity);
            rulesMapper.insert(entity);

            //commits the changes to database
            connection.commit();
        } catch (SQLException e) {
            try {
                //rolls back the changes made if and SQLException gets thrown aka. something goes wrong
                connection.rollback();
            } catch (SQLException r) {
                r.printStackTrace();
            }
        } finally {
            try {
                //ends the transaction
                connection.setAutoCommit(true);
            } catch (SQLException r) {
                r.printStackTrace();
            }
        }
    }

    //TODO
    public ModelEntity read(int id){
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Activity> readAll(){
        List<? super Activity> temp = activityMapper.selectAll();
        return (List<Activity>) temp;
    }

    //TODO
    public void update(ModelEntity entity){}

    //TODO
    public void delete(int id){}

}
