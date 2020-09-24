package com.group.mandatoryxpscrum.data;

import com.group.mandatoryxpscrum.models.ModelEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DataMapper {

    private Connection connection;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public DataMapper(Connection connection) {
        this.connection = connection;
    }

    //methods to be implemented by child
    public abstract String insertStatement();
    public abstract String selectSingleStatement();
    public abstract String selectAllStatement();
    public abstract String updateStatement();
    public abstract String deleteStatement();
    public abstract String selectLastInsertID();

    public abstract void setInsertValues(ModelEntity entity, PreparedStatement ps);
    public abstract ModelEntity loadEntity(ResultSet rs) throws SQLException;
    public abstract int loadLastInsertID(ResultSet rs);
    public abstract void setUpdateValues(ModelEntity entity, PreparedStatement ps);

    //prepares and executes INSERT query with values specified by implementation of abstract method: setInsertValues()
    public final void insert(ModelEntity entity) throws SQLException {
        ps = connection.prepareStatement(insertStatement());
        setInsertValues(entity, ps);
        ps.execute();
        closeSetOrStatement();
    }

    public final ModelEntity select(int id){
        try {
            ps = connection.prepareStatement(selectSingleStatement());
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                return loadEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSetOrStatement();
        }
        return null;
    }

    public final ArrayList<ModelEntity> selectAll(){
        ArrayList<ModelEntity> all = new ArrayList<>();
        try {
            ps = connection.prepareStatement(selectAllStatement());
            rs = ps.executeQuery();
            while (rs.next()) {
                ModelEntity entity = loadEntity(rs);
                all.add(entity);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeSetOrStatement();
        }
        return all;
    }

    public final void update(ModelEntity entity){
        try {
            ps = connection.prepareStatement(updateStatement());
            setUpdateValues(entity, ps);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSetOrStatement();
        }
    }

    public final void delete(int id){
        try {
            ps = connection.prepareStatement(deleteStatement());
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSetOrStatement();
        }
    }

    public final int readLastInsertID(){
        try {
            ps = connection.prepareStatement(selectLastInsertID());
            rs = ps.executeQuery();
            if(rs.next()) {
                return loadLastInsertID(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void closeSetOrStatement(){
        try {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
