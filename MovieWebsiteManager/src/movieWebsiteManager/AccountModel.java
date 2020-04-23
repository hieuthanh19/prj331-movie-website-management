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

/**
 *
 * @author ThanhKH
 */
public class AccountModel {

    //attributes
    private static Connection con;
    private static GetConnection getCon;
    private static PreparedStatement pst;
    private static ResultSet rs;
    //table info
    private static String tblName = "Account";
    private static String[] tblCols = {"acc_username", "acc_password", "acc_level", "acc_status"};

    /**
     * Create new Account Model
     */
    public AccountModel() {
        getCon = new GetConnection();
    }

    /**
     * Check if given username is duplicate
     *
     * @param acc_username
     * @return
     * @throws SQLException
     */
    public boolean isUsernameExist(String acc_username) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //Create sql string
        String sqlStr = "select top 1 " + tblCols[0] + " from " + tblName + " where " + tblCols[0] + "= ?";
        //crete query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, acc_username);
        //execute query
        rs = pst.executeQuery();
        //if result found
        if (rs.next() != false) {
            return true;
        }
        return false;

    }

    /**
     * Check if password is correct
     *
     * @param password account password
     * @param username account username
     * @return true if password is correct. Otherwise, false
     * @throws SQLException
     */
    public boolean isPasswordCorrect(String password, String username) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //Create sql string
        String sqlStr = "select " + tblCols[1] + " from " + tblName + " where " + tblCols[0] + "= ?";
        //crete query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, username);
        //execute query
        rs = pst.executeQuery();
        //if result found
        if (rs.next() != false) {
            String acc_password = rs.getString("acc_password");
            if (acc_password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether an ID is exist
     *
     * @param acc_id account ID
     * @return true if ID exist in DB. Otherwise, false
     * @throws SQLException
     */
    public boolean isIdExist(int acc_id) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select top 1 acc_id from " + tblName + " where acc_id = ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, acc_id);
        //eecute query
        rs = pst.executeQuery();
        //if result found
        if (rs.next() != false) {
            return true;
        }
        return false;
    }

    /**
     * Insert new Account into DB
     *
     * @param acc_username
     * @param acc_password
     * @param acc_level security level of an account. User is 0, Mod is 1 and
     * Admin is 2
     * @param acc_status status of an account. 0 is under banned and 1 is normal
     * @return acc_id or -1 if error
     * @throws SQLException
     */
    public int insert(String acc_username, String acc_password, int acc_level, int acc_status) throws SQLException, AccountException {
        //check if username is valid or not
        if (!isUsernameExist(acc_username)) {
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
            pst.setString(1, acc_username);
            pst.setString(2, acc_password);
            pst.setInt(3, acc_level);
            pst.setInt(4, acc_status);
            //execute query
            pst.executeUpdate();
            //get account ID
            rs = pst.getGeneratedKeys();
            rs.next();
            int acc_id = rs.getInt(1);
            pst.close();
            return acc_id;
        } else {
            return -1;
        }

    }

    /**
     * Update all information of Account
     *
     * @param acc_id
     * @param acc_password
     * @param acc_level security level of an account. User is 0, Mod is 1 and
     * Admin is 2
     * @param acc_status status of an account. 0 is under banned and 1 is normal
     * @throws SQLException
     * @throws movieWebsiteManager.AccountException
     */
    public void update(int acc_id, String acc_password, int acc_level, int acc_status) throws SQLException, AccountException {
        //valid: account ID must exist in DB
        if (isIdExist(acc_id)) {
            con = getCon.getConnection();
            //Create sql string
            String sqlStr = "UPDATE " + tblName + " SET " + tblCols[1] + " =?,"
                    + tblCols[2] + " =?,"
                    + tblCols[3] + " =?"
                    + " WHERE acc_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setString(1, acc_password);
            pst.setInt(2, acc_level);
            pst.setInt(3, acc_status);
            pst.setInt(4, acc_id);
            //excute query
            pst.executeUpdate();
            pst.close();
        }//invalid 
        else {
            throw new AccountException("Account ID is not exist!");
        }
    }

    /**
     * Get account from username and password
     *
     * @param username account username
     * @param password account password
     * @return Account if success. Otherwise, null
     * @throws SQLException
     * @throws AccountException
     */
    public Account getAccount(String username, String password) throws SQLException, AccountException {
        //connect to DB
        con = getCon.getConnection();
        //Create sql string
        String sqlStr = "select * from " + tblName + " where " + tblCols[0] + "= ?";
        //crete query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, username);
        //execute query
        rs = pst.executeQuery();
        //if result found
        if (rs.next() != false) {
            int acc_id = rs.getInt("acc_id");
            String acc_username = rs.getString("acc_username");
            String acc_password = rs.getString("acc_password");
            int acc_level = rs.getInt("acc_level");
            int acc_status = rs.getInt("acc_status");
            //check if password is correct
            if (acc_password.equals(password)) {
                Account a = new Account(acc_id, acc_username, acc_password, acc_level, acc_status);
                return a;
            }
        }
        return null;
    }

}
