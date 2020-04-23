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
public class GenreModel {

    //attributes
    private static Connection con;
    private static GetConnection getCon;
    private static PreparedStatement pst;
    private static ResultSet rs;
    //table info
    private static String tblName = "Genre";
    private static String[] tblCols = {"g_name"};

    /**
     * Create new Genre Model
     */
    public GenreModel() {
        getCon = new GetConnection();
    }

    /**
     * Check if Genre ID exist
     *
     * @param g_id genre ID
     * @return true if exist, false if not
     * @throws SQLException
     */
    public boolean isIdExist(int g_id) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select top 1 g_id from " + tblName + " where g_id = ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, g_id);
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
     * Check if a genre name exist
     *
     * @param genreName
     * @return true if exist, false if not
     * @throws SQLException
     */
    public boolean isNameExist(String genreName) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select * from " + tblName + " where " + tblCols[0] + " like ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, genreName);
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
     * Insert new Genre in DB
     *
     * @param g_name genre name
     * @return g_id if success, -1 if not
     * @throws SQLException
     */
    public int insert(String g_name) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "INSERT INTO " + tblName + " (g_name) VALUES (?)";
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //set values
        pst.setString(1, g_name);
        //execute query
        pst.executeUpdate();
        //get Genre ID
        rs = pst.getGeneratedKeys();
        rs.next();
        int g_id = rs.getInt(1);
        
        pst.close();
        return g_id;
    }

    /**
     * Update values of Genre
     *
     * @param g_id genre ID
     * @param g_name genre name
     * @throws SQLException
     * @throws GenreException
     */
    public void update(int g_id, String g_name) throws SQLException, GenreException {
        if (isIdExist(g_id)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "UPDATE " + tblName + " SET " + tblCols[0] + " =? WHERE g_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setString(1, g_name);
            pst.setInt(2, g_id);
            //execute query
            pst.executeUpdate();
            
            pst.close();
        } else {
            throw new GenreException("Genre ID is not exist!");
        }
    }

    /**
     * Get all genre
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Genre> getAllGenre() throws SQLException {
        ArrayList<Genre> resultList = new ArrayList<>();
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "Select * from " + tblName;
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //get results
        rs = pst.executeQuery();
        while (rs.next() != false) {
            int g_id = rs.getInt("g_id");
            String g_name = rs.getString(tblCols[0]);
            //add to result list
            resultList.add(new Genre(g_id, g_name));
        }
        pst.close();
        return resultList;
    }

    /**
     * Get genre from genre ID
     *
     * @param gId genre ID
     * @return Genre if success, null if not
     * @throws SQLException
     */
    public Genre getGenre(int gId) throws SQLException {
        //check input
        if (isIdExist(gId)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "Select * from " + tblName + " where g_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, gId);
            //get results
            rs = pst.executeQuery();
            while (rs.next() != false) {
                int g_id = rs.getInt("g_id");
                String g_name = rs.getString(tblCols[0]);
                //add to result list
                pst.close();
                Genre g = new Genre(g_id, g_name);
                return g;
            }

        }
        return null;
    }

    /**
     * Get Genre with given name
     *
     * @param gName genre name
     * @return Genre if success, -1 if not
     * @throws SQLException
     */
    public Genre getGenre(String gName) throws SQLException {
        //check input
        if (isNameExist(gName)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "Select * from " + tblName + " where " + tblCols[0] + " like ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, gName);
            //get results
            rs = pst.executeQuery();
            while (rs.next() != false) {
                int g_id = rs.getInt("g_id");
                String g_name = rs.getString(tblCols[0]);
                //add to result list
                pst.close();
                Genre g = new Genre(g_id, g_name);
                return g;
            }

        }
        return null;
    }

    /**
     * Sort genre list
     *
     * @param list ArrayList that need to be sorted
     */
    public void sort(ArrayList<Genre> list) {
        Collections.sort(list, new Comparator<Genre>() {
            @Override
            public int compare(Genre t, Genre t1) {
                return t.getG_name().compareTo(t1.getG_name());
            }
        });
    }
}
