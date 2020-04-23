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
public class MovieModel {
    //attributes

    private static Connection con;
    private static GetConnection getCon;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static StudioModel studioM;
    private static CountryModel countryM;
    //table info
    private static String tblName = "Movie";
    private static String[] tblCols = {"m_name", "m_year", "m_views", "m_duration", "m_description", "m_trailer", "m_thumbnail", "s_id", "country_id"};

    /**
     * Create new Movie Model
     */
    public MovieModel() {
        getCon = new GetConnection();
        studioM = new StudioModel();
        countryM = new CountryModel();
    }

    /**
     * Check if ID is exist
     *
     * @param m_id movie ID
     * @return true if exist, false if not
     * @throws SQLException
     */
    public boolean isIdExist(int m_id) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select top 1 m_id from " + tblName + " where m_id = ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, m_id);
        //eecute query
        rs = pst.executeQuery();
        //if result found
        if (rs.next() != false) {
            return true;
        }
        return false;
    }

    /**
     * Check if a movie name exist
     *
     * @param movieName
     * @return true if exist, false if not
     * @throws SQLException
     */
    public boolean isNameExist(String movieName) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select * from " + tblName + " where " + tblCols[0] + " like ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, movieName);
        //eecute query
        rs = pst.executeQuery();
        //if result found
        if (rs.next() != false) {
            return true;
        }
        return false;
    }

    /**
     * Insert data into Movie
     *
     * @param m_name name
     * @param m_year released year
     * @param m_views number of views
     * @param m_duration duration
     * @param m_description description
     * @param m_trailer URL to trailer
     * @param m_thumbnail URL to thumbnail
     * @param s_id studio ID
     * @param country_id country ID
     * @return movie ID if success, -1 if not
     * @throws SQLException
     */
    public int insert(String m_name, int m_year, int m_views, int m_duration, String m_description, String m_trailer, String m_thumbnail, int s_id, int country_id) throws SQLException {
        //check if Stadio ID and Country ID valid
        if (studioM.isIdExist(s_id) && countryM.isIdExist(country_id)) {
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
            pst.setString(1, m_name);
            pst.setInt(2, m_year);
            pst.setInt(3, m_views);
            pst.setInt(4, m_duration);
            pst.setString(5, m_description);
            pst.setString(6, m_trailer);
            pst.setString(7, m_thumbnail);
            pst.setInt(8, s_id);
            pst.setInt(9, country_id);
            //execute query
            pst.executeUpdate();
            //get account ID
            rs = pst.getGeneratedKeys();
            rs.next();
            int m_id = rs.getInt(1);
            pst.close();
            return m_id;
        } else {
            return -1;
        }
    }

    /**
     * Update Movie data
     *
     * @param m_id ID
     * @param m_name name
     * @param m_year released year
     * @param m_views number of views
     * @param m_duration duration
     * @param m_description description
     * @param m_trailer URL to trailer
     * @param m_thumbnail URL to thumbnail
     * @param s_id studio ID
     * @param country_id country ID
     * @throws SQLException
     * @throws MovieException
     */
    public void update(int m_id, String m_name, int m_year, int m_views, int m_duration, String m_description, String m_trailer, String m_thumbnail, int s_id, int country_id)
            throws SQLException, MovieException {
        //valid: movie ID, studio ID and country ID must exist in DB
        if (studioM.isIdExist(s_id) && countryM.isIdExist(country_id) && isIdExist(m_id)) {
            con = getCon.getConnection();
            //Create sql string
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
            pst.setString(1, m_name);
            pst.setInt(2, m_year);
            pst.setInt(3, m_views);
            pst.setInt(4, m_duration);
            pst.setString(5, m_description);
            pst.setString(6, m_trailer);
            pst.setString(7, m_thumbnail);
            pst.setInt(8, s_id);
            pst.setInt(9, country_id);
            pst.setInt(10, m_id);
            //excute query
            pst.executeUpdate();
            pst.close();
        } else {
            throw new MovieException("Movie ID, Studio ID or Country ID is not exist!");
        }
    }

    /**
     * Get all movie in DB
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Movie> getAllMovie() throws SQLException {
        ArrayList<Movie> resultList = new ArrayList<>();
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "SELECT * FROM " + tblName;
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //excute query
        rs = pst.executeQuery();
        //get data
        while (rs.next() != false) {
            int m_id = rs.getInt("m_id");
            String m_name = rs.getString("m_name");
            int m_year = rs.getInt("m_year");
            int m_views = rs.getInt("m_views");
            int m_duration = rs.getInt("m_duration");
            String m_trailer = rs.getString("m_trailer");
            String m_thubnail = rs.getString("m_thumbnail");
            int s_id = rs.getInt("s_id");
            int country_id = rs.getInt("country_id");
            String m_description = rs.getString("m_description");
            //create new Movie
            resultList.add(new Movie(m_id, m_name, m_year, m_views, m_duration, m_trailer, m_thubnail, m_description, s_id, country_id));
        }
        pst.close();
        return resultList;
    }

    /**
     * Get Movie from m_id
     *
     * @param mId movie ID
     * @return Movie if success, null if not
     * @throws SQLException
     */
    public Movie getMovie(int mId) throws SQLException {
        Movie result = null;
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "SELECT * FROM " + tblName + " WHERE m_id = ?";
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //set values
        pst.setInt(1, mId);
        //excute query
        rs = pst.executeQuery();
        //get data
        while (rs.next() != false) {
            int m_id = rs.getInt("m_id");
            String m_name = rs.getString("m_name");
            int m_year = rs.getInt("m_year");
            int m_views = rs.getInt("m_views");
            int m_duration = rs.getInt("m_duration");
            String m_trailer = rs.getString("m_trailer");
            String m_thubnail = rs.getString("m_thumbnail");
            int s_id = rs.getInt("s_id");
            int country_id = rs.getInt("country_id");
            String m_description = rs.getString("m_description");
            //create new Movie
            result = new Movie(m_id, m_name, m_year, m_views, m_duration, m_trailer, m_thubnail, m_description, s_id, country_id);
        }
        pst.close();
        return result;
    }

    /**
     * Get movie with given name
     *
     * @param mName movie name
     * @return Movie if success, null if not
     * @throws SQLException
     */
    public Movie getMovie(String mName) throws SQLException {
        Movie result = null;
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "Select * from " + tblName + " where " + tblCols[0] + " like ?";
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, mName);
        //excute query
        rs = pst.executeQuery();
        //get data
        while (rs.next() != false) {
            int m_id = rs.getInt("m_id");
            String m_name = rs.getString("m_name");
            int m_year = rs.getInt("m_year");
            int m_views = rs.getInt("m_views");
            int m_duration = rs.getInt("m_duration");
            String m_trailer = rs.getString("m_trailer");
            String m_thubnail = rs.getString("m_thumbnail");
            int s_id = rs.getInt("s_id");
            int country_id = rs.getInt("country_id");
            String m_description = rs.getString("m_description");
            //create new Movie
            result = new Movie(m_id, m_name, m_year, m_views, m_duration, m_trailer, m_thubnail, m_description, s_id, country_id);
        }
        pst.close();
        return result;
    }

    /**
     * Get random movie
     *
     * @param numOfMovie number of random movie
     * @return
     * @throws SQLException
     */
    public ArrayList<Movie> getRandomMovie(int numOfMovie) throws SQLException {
        ArrayList<Movie> result = new ArrayList<>();
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "SELECT TOP " + numOfMovie + " * FROM " + tblName + " ORDER BY NEWID()";
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //excute query
        rs = pst.executeQuery();
        //get data
        while (rs.next() != false) {
            int m_id = rs.getInt("m_id");
            String m_name = rs.getString("m_name");
            int m_year = rs.getInt("m_year");
            int m_views = rs.getInt("m_views");
            int m_duration = rs.getInt("m_duration");
            String m_trailer = rs.getString("m_trailer");
            String m_thubnail = rs.getString("m_thumbnail");
            int s_id = rs.getInt("s_id");
            int country_id = rs.getInt("country_id");
            String m_description = rs.getString("m_description");
            //create new Movie
            result.add(new Movie(m_id, m_name, m_year, m_views, m_duration, m_trailer, m_thubnail, m_description, s_id, country_id));
        }
        pst.close();
        return result;
    }

    /**
     * Get top movies based on views
     *
     * @param numOfMovie number of top movie movie
     * @return
     * @throws SQLException
     */
    public ArrayList<Movie> getTopViewMovie(int numOfMovie) throws SQLException {
        ArrayList<Movie> result = new ArrayList<>();
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "SELECT TOP " + numOfMovie + " * FROM " + tblName + " ORDER BY " + tblCols[2] + " desc";
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //excute query
        rs = pst.executeQuery();
        //get data
        while (rs.next() != false) {
            int m_id = rs.getInt("m_id");
            String m_name = rs.getString("m_name");
            int m_year = rs.getInt("m_year");
            int m_views = rs.getInt("m_views");
            int m_duration = rs.getInt("m_duration");
            String m_trailer = rs.getString("m_trailer");
            String m_thubnail = rs.getString("m_thumbnail");
            int s_id = rs.getInt("s_id");
            int country_id = rs.getInt("country_id");
            String m_description = rs.getString("m_description");
            //create new Movie
            result.add(new Movie(m_id, m_name, m_year, m_views, m_duration, m_trailer, m_thubnail, m_description, s_id, country_id));
        }
        pst.close();
        return result;
    }

    /**
     * Search for movie by search string
     *
     * @param str string to search
     * @return
     * @throws SQLException
     */
    public ArrayList<Movie> searchMovie(String str) throws SQLException {
        ArrayList<Movie> result = new ArrayList<>();
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String searchStr = "'%" + str + "%'";
        String sqlStr = "SELECT * FROM " + tblName + " WHERE " + tblCols[0] + " LIKE " + searchStr;
        //create query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        //excute query
        rs = pst.executeQuery();
        //get data
        while (rs.next() != false) {
            int m_id = rs.getInt("m_id");
            String m_name = rs.getString("m_name");
            int m_year = rs.getInt("m_year");
            int m_views = rs.getInt("m_views");
            int m_duration = rs.getInt("m_duration");
            String m_trailer = rs.getString("m_trailer");
            String m_thubnail = rs.getString("m_thumbnail");
            int s_id = rs.getInt("s_id");
            int country_id = rs.getInt("country_id");
            String m_description = rs.getString("m_description");
            //create new Movie
            result.add(new Movie(m_id, m_name, m_year, m_views, m_duration, m_trailer, m_thubnail, m_description, s_id, country_id));
        }
        pst.close();
        return result;
    }

    /**
     * Sort movie list
     *
     * @param mList ArrayList to be sorted
     */
    public void sort(ArrayList<Movie> mList) {
        Collections.sort(mList, new Comparator<Movie>() {
            @Override
            public int compare(Movie t, Movie t1) {
                return t.getM_name().compareTo(t1.getM_name());
            }
        });
    }
}
