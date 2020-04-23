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

/**
 *
 * @author ThanhKH
 */
public class FavoriteMovieModel {
    //attributes

    private static Connection con;
    private static GetConnection getCon;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static MovieModel movieM;
    private static ClientModel clientM;
    //table info
    private static String tblName = "FavoriteMovie";
    private static String[] tblCols = {"c_id", "m_id"};

    /**
     * Create new FavoriteMovie Model
     */
    public FavoriteMovieModel() {
        getCon = new GetConnection();
        movieM = new MovieModel();
        clientM = new ClientModel();
    }

    /**
     * Insert new FavoriteMovie in DB
     *
     * @param c_id client id
     * @param m_id movie ID
     * @return c_id if success, -1 if not
     * @throws SQLException
     */
    public int insert(int c_id, int m_id) throws SQLException {
        //check if Client ID and Movie ID valid
        if (movieM.isIdExist(m_id) && clientM.isIdExist(c_id)) {
            //connect to DB
            con = getCon.getConnection();
            //Create sql string
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
            pst.setInt(1, c_id);
            pst.setInt(2, m_id);

            //execute query
            pst.executeUpdate();
            //get account ID
            rs = pst.getGeneratedKeys();
            rs.next();
            pst.close();
            return c_id;
        } else {
            return -1;
        }
    }

    /**
     * Update values of FavoriteMovie
     *
     * @param m_id movie ID
     * @param c_id client ID
     * @throws SQLException
     * @throws movieWebsiteManager.FavoriteMovieException
     */
    public void update(int m_id, int c_id) throws SQLException, FavoriteMovieException {
        if (clientM.isIdExist(c_id) && movieM.isIdExist(m_id)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "UPDATE " + tblName + " SET " + tblCols[0];
            for (int i = 1; i < tblCols.length; i++) {
                sqlStr += " =?, " + tblCols[i];
                if (i == tblCols.length - 1) {
                    sqlStr += " =?";
                }
            }
            sqlStr += " WHERE c_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setInt(1, c_id);
            pst.setInt(2, m_id);
            //execute query
            pst.executeUpdate();
            pst.close();
        }
    }

    /**
     * Get favorite movies from client ID
     *
     * @param cId client ID
     * @return
     * @throws SQLException
     */
    public ArrayList<FavoriteMovie> getFavortieMovie(int cId) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "SELECT * FROM " + tblName + " WHERE " + tblCols[0] + "=?";
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //set values
        pst.setInt(1, cId);
        //excute query
        rs = pst.executeQuery();
        //get return data
        ArrayList<FavoriteMovie> resultList = new ArrayList<>();
        while (rs.next() != false) {
            int c_id = rs.getInt("c_id");
            int m_id = rs.getInt("m_id");
            //add to result list
            resultList.add(new FavoriteMovie(c_id, m_id));
        }
        return resultList;

    }

    /**
     * Check if a movie is in favorite movie list
     *
     * @param cId client ID
     * @param mId movie ID
     * @return true if exist, false if not
     * @throws SQLException
     */
    public boolean isInFavoriteList(int cId, int mId) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "SELECT * FROM " + tblName + " WHERE " + tblCols[0] + "=? AND " + tblCols[1] + "=?";
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //set values
        pst.setInt(1, cId);
        pst.setInt(2, mId);
        //excute query
        rs = pst.executeQuery();
        //get return data        
        if (rs.next() != false) {
            return true;
        }
        return false;
    }
    /**
     * Delete a client's favorite movie
     * @param cId Client ID
     * @param mId Movie ID
     * @throws SQLException 
     */
    public void deleteFavMovie(int cId, int mId) throws SQLException{
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "DELETE FROM " + tblName + " WHERE " + tblCols[0] + "=? AND " + tblCols[1] + "=?";
        //create query
        pst = con.prepareStatement(sqlStr);
        //set values
        pst.setInt(1, cId);
        pst.setInt(2, mId);
        //excute query
        pst.executeUpdate();
    }
}
