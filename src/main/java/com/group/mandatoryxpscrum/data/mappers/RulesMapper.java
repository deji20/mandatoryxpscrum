package com.group.mandatoryxpscrum.data.mappers;

import com.group.mandatoryxpscrum.data.DataMapper;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.ModelEntity;
import com.group.mandatoryxpscrum.models.Pricing;
import com.group.mandatoryxpscrum.models.Rules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RulesMapper extends DataMapper {

    public RulesMapper(Connection connection) {
        super(connection);
    }

    @Override
    public String insertStatement() {
        return "INSERT INTO rule (id, max_capacity, duration, min_age, min_height) VALUES (LAST_INSERT_ID(), ?, ?, ?, ?)";
    }

    //TODO
    @Override
    public String selectSingleStatement() {
        return null;
    }

    @Override
    public String selectAllStatement() {
        return "SELECT * FROM activity LEFT JOIN rules r on activity.id = r.id";
    }

    //TODO
    @Override
    public String updateStatement() {
        return null;
    }

    //TODO
    @Override
    public String deleteStatement() {
        return null;
    }

    //TODO
    @Override
    public String selectLastInsertID() {
        return null;
    }

    @Override
    public void setInsertValues(ModelEntity entity, PreparedStatement ps) {
        Activity activity = (Activity) entity;
        Rules rules = activity.getRules();
        try{
            ps.setInt(1, rules.getMax_capacity());
            ps.setInt(2, rules.getDuration());
            ps.setInt(3, rules.getMin_age());
            ps.setInt(4, rules.getMin_height());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ModelEntity loadEntity(ResultSet rs) {
        try {
            int id = rs.getInt("r.id");
            int max_capacity = rs.getInt("max_capacity");
            int duration = rs.getInt("duration");
            int min_age = rs.getInt("min_age");
            int min_height = rs.getInt("min_height");
            return new Rules(id, max_capacity, duration, min_age, min_height);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //TODO
    @Override
    public int loadLastInsertID(ResultSet rs) {
        return 0;
    }

    //TODO
    @Override
    public void setUpdateValues(ModelEntity entity, PreparedStatement ps) {

    }
}
