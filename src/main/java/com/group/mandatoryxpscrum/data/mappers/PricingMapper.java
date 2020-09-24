package com.group.mandatoryxpscrum.data.mappers;

import com.group.mandatoryxpscrum.data.DataMapper;
import com.group.mandatoryxpscrum.models.Activity;
import com.group.mandatoryxpscrum.models.ModelEntity;
import com.group.mandatoryxpscrum.models.Pricing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PricingMapper extends DataMapper {

    public PricingMapper(Connection connection) {
        super(connection);
    }

    @Override
    public String insertStatement() {
        return "INSERT INTO price (id, standard, discount, extra) VALUES (LAST_INSERT_ID(), ?, ?, ?) ";
    }

    //TODO
    @Override
    public String selectSingleStatement() {
        return null;
    }

    @Override
    public String selectAllStatement() {
        return "SELECT * FROM activity LEFT JOIN pricing p on activity.id = p.id;";
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
        Pricing pricing = activity.getPrice();
        try{
            ps.setInt(1, pricing.getStandard());
            ps.setInt(2, pricing.getDiscount());
            ps.setString(3, pricing.getExtra_info());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ModelEntity loadEntity(ResultSet rs){
        try {
            int id = rs.getInt("p.id");
            int standard = rs.getInt("standard");
            int discount = rs.getInt("discount");
            String extra_info = rs.getString("extra_info");
            return new Pricing(id, standard, discount, extra_info);
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
