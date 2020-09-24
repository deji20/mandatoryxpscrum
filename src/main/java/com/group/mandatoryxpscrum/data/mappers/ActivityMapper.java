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

public class ActivityMapper extends DataMapper {

    public ActivityMapper(Connection connection){
        super(connection);
    }

    @Override
    public String insertStatement() {
        return "INSERT INTO activity (name, image_url, descr) VALUES (?, ?, ?)";
    }

    //TODO
    @Override
    public String selectSingleStatement() {
        return null;
    }

    @Override
    public String selectAllStatement() { return "SELECT * FROM activity LEFT JOIN pricing p ON activity.id = p.id LEFT JOIN rules r on activity.id = r.id"; }

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

    //sets ?'s in the PreparedStatement to the chosen values
    @Override
    public void setInsertValues(ModelEntity modelEntity, PreparedStatement ps) {
        Activity activity = (Activity) modelEntity;
        try{
            ps.setString(1, activity.getName());
            ps.setString(2, activity.getImage());
            ps.setString(3, activity.getDescription());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ModelEntity loadEntity(ResultSet rs) throws SQLException {
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String image_url = rs.getString("image_url");
            String descr = rs.getString("descr");
            Pricing pricing = extractPricing(rs);
            Rules rules = extractRules(rs);
            return new Activity(id, name, image_url, descr, pricing, rules, null);
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

    public Pricing extractPricing(ResultSet rs) throws SQLException {
        Pricing pricing = new Pricing(
                    rs.getInt("p.id"),
                    rs.getInt("standard"),
                    rs.getInt("discount"),
                    rs.getString("extra_info")
            );
        return pricing;
    }

    public Rules extractRules(ResultSet rs) throws SQLException {
        Rules rules = new Rules(
                    rs.getInt("r.id"),
                    rs.getInt("max_capacity"),
                    rs.getInt("duration"),
                    rs.getInt("min_age"),
                    rs.getInt("min_height")
            );
        return rules;
    }

}
