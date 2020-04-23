/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieWebsiteManager;

import connection.GetConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author ThanhKH
 */
public class StudioModel {

    //attributes
    private static Connection con;
    private static GetConnection getCon;
    private static PreparedStatement pst;
    private static ResultSet rs;
    //table info
    private static String tblName = "Studio";
    private static String[] tblCols = {"s_name"};

    /**
     * Create new Studio Model
     */
    public StudioModel() {
        getCon = new GetConnection();
    }

    /**
     * Check if studio ID exist
     *
     * @param s_id Studio ID
     * @return true if exist, false if not
     * @throws SQLException
     */
    public boolean isIdExist(int s_id) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select top 1 s_id from " + tblName + " where s_id = ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, s_id);
        //eecute query
        rs = pst.executeQuery();
        //if result found
        if (rs.next() != false) {
            pst.close();
            return true;
        }
        pst.close();
        return false;
    }

    /**
     * Check if a studio name exist
     *
     * @param studioName
     * @return true if exist, false if not
     * @throws SQLException
     */
    public boolean isNameExist(String studioName) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select * from " + tblName + " where " + tblCols[0] + " like ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, studioName);
        //eecute query
        rs = pst.executeQuery();
        //if result found
        if (rs.next() != false) {
            pst.close();
            return true;
        }
        pst.close();
        return false;
    }

    /**
     * Insert new studio in DB
     *
     * @param s_name Studio name
     * @return studio ID if success, -1 if not
     * @throws SQLException
     */
    public int insert(String s_name) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "INSERT INTO " + tblName + " (s_name) VALUES (?)";
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //set values
        pst.setString(1, s_name);
        //execute query
        pst.executeUpdate();
        //get studio ID
        rs = pst.getGeneratedKeys();
        rs.next();
        int s_id = rs.getInt(1);
        pst.close();
        return s_id;
    }

    /**
     * Update values of Studio
     *
     * @param s_id Studio ID
     * @param s_name Studio name
     * @throws SQLException
     * @throws StudioException
     */
    public void update(int s_id, String s_name) throws SQLException, StudioException {
        if (isIdExist(s_id)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "UPDATE " + tblName + " SET " + tblCols[0] + " =? WHERE s_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setString(1, s_name);
            pst.setInt(2, s_id);
            //execute query
            pst.executeUpdate();
            pst.close();
        } else {
            throw new StudioException("Studio ID is not exist!");
        }
    }

    /**
     * Get Studio from Studio ID
     *
     * @param sId studio ID
     * @return Studio if success, null of not
     * @throws SQLException
     * @throws StudioException
     */
    public Studio getStudio(int sId) throws SQLException, StudioException {
        //check whether Studio ID is exist
        if (isIdExist(sId)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "SELECT * FROM " + tblName + " WHERE s_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setInt(1, sId);
            //excute query
            rs = pst.executeQuery();
            //get data
            while (rs.next() != false) {
                int s_id = rs.getInt("s_id");
                String s_name = rs.getString("s_name");
                //create new Studio
                Studio result = new Studio(s_id, s_name);
                pst.close();
                return result;
            }
        }
        return null;
    }

    /**
     * Get studio with given name
     *
     * @param sName studio name
     * @return Studio if success, -1 if not
     * @throws SQLException
     * @throws StudioException
     */
    public Studio getStudio(String sName) throws SQLException, StudioException {
        //check input
        if (isNameExist(sName)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "Select * from " + tblName + " where " + tblCols[0] + " like ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, sName);
            //get results
            rs = pst.executeQuery();
            while (rs.next() != false) {
                int s_id = rs.getInt("s_id");
                String s_name = rs.getString(tblCols[0]);
                //add to result list
                pst.close();
                Studio s = new Studio(s_id, s_name);
                return s;
            }
            pst.close();
        }
        return null;
    }

    /**
     * Get all studio
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Studio> getAllStudio() throws SQLException {
        ArrayList<Studio> resultList = new ArrayList<>();
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "Select * from " + tblName;
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //get results
        rs = pst.executeQuery();
        while (rs.next() != false) {
            int s_id = rs.getInt("s_id");
            String s_name = rs.getString(tblCols[0]);
            //add to result list
            resultList.add(new Studio(s_id, s_name));
        }
        pst.close();
        return resultList;
    }

    /**
     * Sort Studio list
     *
     * @param list ArrayList to be sorted
     */
    public void sort(ArrayList<Studio> list) {
        Collections.sort(list, new Comparator<Studio>() {
            @Override
            public int compare(Studio t, Studio t1) {
                return t.getS_name().compareTo(t1.getS_name());
            }
        });
    }

}
