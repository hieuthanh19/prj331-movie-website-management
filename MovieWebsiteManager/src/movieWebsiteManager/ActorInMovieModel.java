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
 * @author nhan
 */
public class ActorInMovieModel {

    //attributes
    private static Connection con;
    private static GetConnection getCon;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static MovieModel movieM;
    private static ActorModel actorM;
    //table info
    private static String tblName = "ActorInMovie";
    private static String[] tblCols = {"m_id", "a_id"};

    /**
     * Create new ActorInMovie Model
     */
    public ActorInMovieModel() {
        getCon = new GetConnection();
        movieM = new MovieModel();
        actorM = new ActorModel();
    }

    /**
     * Insert new ActorInMovie in DB
     *
     * @param m_id movie ID
     * @param a_id actor ID
     * @return m_id if success. -1 if an error occurred
     * @throws SQLException
     */
    public int insert(int m_id, int a_id) throws SQLException {
        //check if Stadio ID and Country ID valid
        if (movieM.isIdExist(m_id) && actorM.isIdExist(a_id)) {
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
            pst.setInt(1, m_id);
            pst.setInt(2, a_id);

            //execute query
            pst.executeUpdate();
            //get account ID
            rs = pst.getGeneratedKeys();
            rs.next();
            pst.close();
            return m_id;
        } else {
            return -1;
        }
    }

    /**
     * Update values of ActorInMovie
     *
     * @param m_id movie ID
     * @param a_id actor ID
     * @throws SQLException
     * @throws movieWebsiteManager.ActorInMovieException
     */
    public void update(int m_id, int a_id) throws SQLException, ActorInMovieException {
        if (movieM.isIdExist(m_id) && actorM.isIdExist(a_id)) {
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
            sqlStr += " WHERE m_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setInt(1, a_id);
            pst.setInt(2, m_id);
            //execute query
            pst.executeUpdate();
            pst.close();
        }
    }

    /**
     * Delete all actor references to a Movie with given movie ID
     *
     * @param m_id movie ID
     * @throws SQLException
     */
    public void delete(int m_id) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlString = "DELETE FROM " + tblName + " WHERE " + tblCols[0] + "=?";
        //create query
        pst = con.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS);
        //set values
        pst.setInt(1, m_id);
        //exute query
        pst.executeUpdate();
        pst.close();

    }

    /**
     * Get all ActorInMovie with given Movie ID
     *
     * @param mId movie ID
     * @return an ArrayList<Movie> whose size > 0 if success. Otherwise, size = 0
     * @throws SQLException
     */
    public ArrayList<ActorInMovie> getActorInMovie(int mId) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "SELECT * FROM " + tblName + " WHERE " + tblCols[0] + "=?";
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //set values
        pst.setInt(1, mId);
        //excute query
        rs = pst.executeQuery();
        //get return data
        ArrayList<ActorInMovie> resultList = new ArrayList<>();
        while (rs.next() != false) {
            int m_id = rs.getInt("m_id");
            int a_id = rs.getInt("a_id");
            //add to result list
            resultList.add(new ActorInMovie(m_id, a_id));
        }
        return resultList;

    }
}
