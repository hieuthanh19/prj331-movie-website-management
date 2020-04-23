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
public class ActorModel {

    //attributes
    private static Connection con;
    private static GetConnection getCon;
    private static PreparedStatement pst;
    private static ResultSet rs;
    //table info
    private static String tblName = "Actor";
    private static String[] tblCols = {"a_name", "a_age"};

    /**
     * Create new Actor Model
     */
    public ActorModel() {
        getCon = new GetConnection();
    }

    /**
     * Check if Actor ID exist
     *
     * @param a_id actor ID
     * @return true if ID exist, false if not
     * @throws SQLException
     */
    public boolean isIdExist(int a_id) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select top 1 a_id from " + tblName + " where a_id = ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, a_id);
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
     * Check if an actor name exist
     *
     * @param actorName
     * @return true if name exist, false if not
     * @throws SQLException
     */
    public boolean isNameExist(String actorName) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select * from " + tblName + " where " + tblCols[0] + " like ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, actorName);
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
     * Insert new Actor in DB
     *
     * @param a_name actor name
     * @param a_age actor ID
     * @return Actor ID if success, -1 if fail
     * @throws SQLException
     */
    public int insert(String a_name, int a_age) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "insert into " + tblName + " (" + tblCols[0];
        //set cols in sqlStr
        for (int i = 1; i < tblCols.length; i++) {
            sqlStr += ", " + tblCols[i];
            if (i == tblCols.length - 1) {
                sqlStr += ") ";
            }
        }
        //set insert values
        sqlStr += "values (?";
        //set cols in sqlStr
        for (int i = 1; i < tblCols.length; i++) {
            sqlStr += ", ?";
            if (i == tblCols.length - 1) {
                sqlStr += ") ";
            }
        }
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //set values
        pst.setString(1, a_name);
        pst.setInt(2, a_age);
        //execute query
        pst.executeUpdate();
        //get Actor ID
        rs = pst.getGeneratedKeys();
        rs.next();
        int d_id = rs.getInt(1);
        pst.close();
        return d_id;
    }

    /**
     * Update values of Actor
     *
     * @param a_id actor ID
     * @param a_name actor name
     * @param a_age actor age
     * @throws SQLException
     * @throws ActorException
     */
    public void update(int a_id, String a_name, int a_age) throws SQLException, ActorException {
        if (isIdExist(a_id)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "UPDATE " + tblName + " SET " + tblCols[0] + " =?,"
                    + tblCols[1] + " =? "
                    + " WHERE a_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setString(1, a_name);
            pst.setInt(2, a_age);
            pst.setInt(3, a_id);
            //execute query
            pst.executeUpdate();
            pst.close();
        } else {
            throw new ActorException("Actor ID is not exist!");
        }
    }

    /**
     * Get all actor
     *
     * @return An ArrayList<Actor> whose size > 0 if success, size = 0 if fail
     * @throws SQLException
     */
    public ArrayList<Actor> getAllActor() throws SQLException {
        ArrayList<Actor> resultList = new ArrayList<>();
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "Select * from " + tblName;
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //get results
        rs = pst.executeQuery();
        while (rs.next() != false) {
            int a_id = rs.getInt("a_id");
            String a_name = rs.getString(tblCols[0]);
            int a_age = rs.getInt(tblCols[1]);
            //add to result list
            resultList.add(new Actor(a_id, a_name, a_age));
        }
        return resultList;
    }

    /**
     * Get actor from actor ID
     *
     * @param aId actor ID
     * @return Actor if success, null if fail
     * @throws SQLException
     */
    public Actor getActor(int aId) throws SQLException {
        //check input
        if (isIdExist(aId)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "Select * from " + tblName + " where a_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, aId);
            //get results
            rs = pst.executeQuery();
            while (rs.next() != false) {
                int a_id = rs.getInt("a_id");
                String a_name = rs.getString(tblCols[0]);
                int a_age = rs.getInt(tblCols[1]);
                //add to result list
                Actor a = new Actor(a_id, a_name, a_age);
                return a;
            }
        }
        return null;
    }

    /**
     * Get actor with given name
     *
     * @param aName actor name
     * @return Actor if success, null if fail
     * @throws SQLException
     */
    public Actor getActor(String aName) throws SQLException {
        //check input
        if (isNameExist(aName)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "Select * from " + tblName + " where " + tblCols[0] + " like ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, aName);
            //get results
            rs = pst.executeQuery();
            while (rs.next() != false) {
                int a_id = rs.getInt("a_id");
                String a_name = rs.getString(tblCols[0]);
                int a_age = rs.getInt(tblCols[1]);
                //add to result list
                pst.close();
                Actor a = new Actor(a_id, a_name, a_age);
                return a;
            }

        }
        return null;
    }

    /**
     * Sort actor list
     *
     * @param aList actor list
     */
    public void sort(ArrayList<Actor> aList) {
        Collections.sort(aList, new Comparator<Actor>() {
            @Override
            public int compare(Actor t, Actor t1) {
                return t.getA_name().compareTo(t1.getA_name());
            }
        });
    }

}
