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
public class GenreInMovieModel {
    //attributes

    private static Connection con;
    private static GetConnection getCon;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static MovieModel movieM;
    private static GenreModel genreM;
    //table info
    private static String tblName = "GenreInMovie";
    private static String[] tblCols = {"m_id", "g_id"};

    /**
     * Create new GenreInMovie Model
     */
    public GenreInMovieModel() {
        getCon = new GetConnection();
        movieM = new MovieModel();
        genreM = new GenreModel();
    }

    /**
     * Insert new GenreInMovie in DB
     *
     * @param m_id movie ID
     * @param g_id genre ID
     * @return m_id if success, -1 if not
     * @throws SQLException
     */
    public int insert(int m_id, int g_id) throws SQLException {
        //check if Movie ID and Genre ID valid
        if (movieM.isIdExist(m_id) && genreM.isIdExist(g_id)) {
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
            pst.setInt(2, g_id);

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
     * Update values of GenreInMovie
     *
     * @param m_id movie ID
     * @param g_id genre ID
     * @throws SQLException
     * @throws GenreInMovieException
     */
    public void update(int m_id, int g_id) throws SQLException, GenreInMovieException {
        if (movieM.isIdExist(m_id) && genreM.isIdExist(g_id)) {
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
            pst.setInt(1, m_id);
            pst.setInt(2, g_id);
            //execute query
            pst.executeUpdate();
            pst.close();
        }
    }

    /**
     * Delete all genre references to a Movie with given movie ID
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
     * @param mId
     * @return
     * @throws SQLException
     */
    public ArrayList<GenreInMovie> getGenreInMovie(int mId) throws SQLException {
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
        ArrayList<GenreInMovie> resultList = new ArrayList<>();
        while (rs.next() != false) {
            int m_id = rs.getInt("m_id");
            int g_id = rs.getInt("g_id");
            //add to result list
            resultList.add(new GenreInMovie(m_id, g_id));
        }
        return resultList;

    }
}
