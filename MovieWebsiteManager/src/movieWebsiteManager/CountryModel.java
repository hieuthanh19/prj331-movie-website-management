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
public class CountryModel {
    //attributes

    private static Connection con;
    private static GetConnection getCon;
    private static PreparedStatement pst;
    private static ResultSet rs;
    //table info
    private static String tblName = "Country";
    private static String[] tblCols = {"country_name"};

    /**
     * Create new country model
     */
    public CountryModel() {
        getCon = new GetConnection();
    }

    /**
     * Check whether given ID is exist
     *
     * @param country_id
     * @return true if exist, false if not
     * @throws SQLException
     */
    public boolean isIdExist(int country_id) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select top 1 country_id from " + tblName + " where country_id = ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, country_id);
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
     * check if country name already exist
     *
     * @param country_name
     * @return true if exist, false if not
     * @throws SQLException
     */
    public boolean isNameExist(String country_name) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select * from " + tblName + " where country_name like ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, country_name);
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
     * Insert new country to DB
     *
     * @param country_name
     * @return Country ID if success, -1 if not
     * @throws SQLException
     */
    public int insert(String country_name) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "INSERT INTO " + tblName + " (country_name) VALUES (?)";
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //set values
        pst.setString(1, country_name);
        //execute query
        pst.executeUpdate();
        //get studio ID
        rs = pst.getGeneratedKeys();
        rs.next();
        int country_id = rs.getInt(1);
        pst.close();
        return country_id;
    }

    /**
     * Update values of country
     *
     * @param country_id
     * @param country_name
     * @throws SQLException
     * @throws CountryException
     */
    public void update(int country_id, String country_name) throws SQLException, CountryException {
        //valid
        if (isIdExist(country_id)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "UPDATE " + tblName + " SET " + tblCols[0] + " =? WHERE country_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setString(1, country_name);
            pst.setInt(2, country_id);
            //execute query
            pst.executeUpdate();
            pst.close();
        } else {  //invalid
            throw new CountryException("Country ID is not exist!");
        }
    }

    /**
     * Get Country from Country ID
     *
     * @param countryId
     * @return Country if success, null if not
     * @throws SQLException
     * @throws CountryException
     */
    public Country getCountry(int countryId) throws SQLException, CountryException {
        //check whether Country ID is exist
        if (isIdExist(countryId)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "SELECT * FROM " + tblName + " WHERE country_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setInt(1, countryId);
            //excute query
            rs = pst.executeQuery();
            //get data
            while (rs.next() != false) {
                int country_id = rs.getInt("country_id");
                String country_name = rs.getString("country_name");
                //create new Country
                Country result = new Country(country_id, country_name);
                pst.close();
                return result;
            }

        }
        return null;
    }

    /**
     * Get Country with a country name
     *
     * @param conutryName country name
     * @return Country if success, null if not
     * @throws SQLException
     * @throws CountryException
     */
    public Country getCountry(String conutryName) throws SQLException, CountryException {
        //check whether Country ID is exist
        if (isNameExist(conutryName)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "SELECT * FROM " + tblName + " where " + tblCols[0] + " like ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setString(1, conutryName);
            //excute query
            rs = pst.executeQuery();
            //get data
            while (rs.next() != false) {
                int country_id = rs.getInt("country_id");
                String country_name = rs.getString("country_name");
                //create new Country
                Country result = new Country(country_id, country_name);
                pst.close();
                return result;
            }
        }
        return null;
    }

    /**
     * Get all country
     *
     * @return list whose size > 0 if success, size = 0 if not
     * @throws SQLException
     */
    public ArrayList<Country> getAllCountry() throws SQLException {
        ArrayList<Country> resultList = new ArrayList<>();
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "Select * from " + tblName;
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //get results
        rs = pst.executeQuery();
        while (rs.next() != false) {
            int c_id = rs.getInt("country_id");
            String c_name = rs.getString(tblCols[0]);
            //add to result list
            resultList.add(new Country(c_id, c_name));
        }
        pst.close();
        return resultList;
    }

    /**
     * Sort country list
     *
     * @param list country list
     */
    public void sort(ArrayList<Country> list) {
        Collections.sort(list, new Comparator<Country>() {
            @Override
            public int compare(Country t, Country t1) {
                return t.getCountry_name().compareTo(t1.getCountry_name());
            }
        });
    }
}
