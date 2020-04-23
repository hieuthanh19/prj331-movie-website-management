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
public class DirectorModel {

    //attributes
    private static Connection con;
    private static GetConnection getCon;
    private static PreparedStatement pst;
    private static ResultSet rs;
    //table info
    private static String tblName = "Director";
    private static String[] tblCols = {"d_name", "d_age"};

    /**
     * Create new Director Model
     */
    public DirectorModel() {
        getCon = new GetConnection();
    }

    /**
     * Check if Director ID exist
     *
     * @param d_id director ID
     * @return true if exist, false if not
     * @throws SQLException
     */
    public boolean isIdExist(int d_id) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select top 1 d_id from " + tblName + " where d_id = ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, d_id);
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
     * Check if director name exist
     *
     * @param directorName
     * @return true if exist, false if not
     * @throws SQLException
     */
    public boolean isNameExist(String directorName) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select * from " + tblName + " where " + tblCols[0] + " like ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, directorName);
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
     * Insert new Director in DB
     *
     * @param d_name director name
     * @param d_age director age
     * @return director ID if success, -1 if not
     * @throws SQLException
     */
    public int insert(String d_name, int d_age) throws SQLException {
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
        pst.setString(1, d_name);
        pst.setInt(2, d_age);
        //execute query
        pst.executeUpdate();
        //get Director ID
        rs = pst.getGeneratedKeys();
        rs.next();
        int d_id = rs.getInt(1);
        pst.close();
        return d_id;
    }

    /**
     * Update values of Director
     *
     * @param d_id director ID
     * @param d_name director name
     * @param d_age director age
     * @throws SQLException
     * @throws DirectorException
     */
    public void update(int d_id, String d_name, int d_age) throws SQLException, DirectorException {
        if (isIdExist(d_id)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "UPDATE " + tblName + " SET " + tblCols[0] + " =?,"
                    + tblCols[1] + " =?, "
                    + " WHERE d_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setString(1, d_name);
            pst.setInt(2, d_age);
            pst.setInt(3, d_id);
            //execute query
            pst.executeUpdate();
            pst.close();
        } else {
            throw new DirectorException("Director ID is not exist!");
        }
    }

    /**
     * Get all director
     *
     * @return ArrayList<Director>
     * @throws SQLException
     */
    public ArrayList<Director> getAllDirector() throws SQLException {
        ArrayList<Director> resultList = new ArrayList<>();
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "Select * from " + tblName;
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //get results
        rs = pst.executeQuery();
        while (rs.next() != false) {
            int a_id = rs.getInt("d_id");
            String a_name = rs.getString(tblCols[0]);
            int a_age = rs.getInt(tblCols[1]);
            //add to result list
            resultList.add(new Director(a_id, a_name, a_age));
        }
        pst.close();
        return resultList;
    }

    /**
     * Get Director from director ID
     *
     * @param dId director ID
     * @return
     * @throws SQLException
     */
    public Director getDirector(int dId) throws SQLException {
        //check input
        if (isIdExist(dId)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "Select * from " + tblName + " where d_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, dId);
            //get results
            rs = pst.executeQuery();
            while (rs.next() != false) {
                int d_id = rs.getInt("d_id");
                String d_name = rs.getString(tblCols[0]);
                int d_age = rs.getInt(tblCols[1]);
                //add to result list
                Director d = new Director(d_id, d_name, d_age);
                pst.close();
                return d;
            }

        }
        return null;
    }

    /**
     * Get director with a name
     *
     * @param dName director name
     * @return Director if success, null if not
     * @throws SQLException
     */
    public Director getDirector(String dName) throws SQLException {
        //check input
        if (isNameExist(dName)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "Select * from " + tblName + " where " + tblCols[0] + " like ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, dName);
            //get results
            rs = pst.executeQuery();
            while (rs.next() != false) {
                int d_id = rs.getInt("d_id");
                String d_name = rs.getString(tblCols[0]);
                int d_age = rs.getInt(tblCols[1]);
                //add to result list
                Director d = new Director(d_id, d_name, d_age);
                pst.close();
                return d;
            }

        }
        return null;
    }

    /**
     * Sort Director list
     *
     * @param list
     */
    public void sort(ArrayList<Director> list) {
        Collections.sort(list, new Comparator<Director>() {
            @Override
            public int compare(Director t, Director t1) {
                return t.getD_name().compareTo(t1.getD_name());
            }
        });
    }
}
