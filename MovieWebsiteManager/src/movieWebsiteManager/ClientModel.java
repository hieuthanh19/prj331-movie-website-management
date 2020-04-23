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
public class ClientModel {

    //attributes
    private static Connection con;
    private static GetConnection getCon;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static AccountModel accM;
    //table info
    private static String tblName = "Client";
    private static String[] tblCols = {"c_name", "c_email", "c_gender", "acc_id"};

    /**
     * Create new Client Model
     */
    public ClientModel() {
        getCon = new GetConnection();
        accM = new AccountModel();
    }

    /**
     * Check whether Client ID exist or not
     *
     * @param c_id client ID
     * @return true if exist, false if not
     * @throws SQLException
     */
    public boolean isIdExist(int c_id) throws SQLException {
        //connect to DB
        con = getCon.getConnection();
        //create sql string
        String sqlStr = "select top 1 c_id from " + tblName + " where c_id = ?";
        //creaste query
        pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, c_id);
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
     * Insert new Client to DB
     *
     * @param c_name client name
     * @param c_email client email
     * @param c_gender client gender (0: female, 1: male)
     * @param acc_id account ID
     * @return client ID if success, -1 if not
     * @throws SQLException
     */
    public int insert(String c_name, String c_email, int c_gender, int acc_id) throws SQLException {
        //check if ID valid
        if (accM.isIdExist(acc_id)) {
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
            pst.setString(1, c_name);
            pst.setString(2, c_email);
            pst.setInt(3, c_gender);
            pst.setInt(4, acc_id);
            //execute query
            pst.executeUpdate();
            //get account ID
            rs = pst.getGeneratedKeys();
            rs.next();
            int c_id = rs.getInt(1);
            pst.close();
            return c_id;
        } else {
            return -1;
        }
    }

    /**
     * Update info of a Client
     *
     * @param c_id client ID
     * @param c_name client Name
     * @param c_email client Email
     * @param c_gender client gender (0: female, 1: male)
     * @param acc_id account Id
     * @throws SQLException
     * @throws movieWebsiteManager.ClientException
     */
    public void update(int c_id, String c_name, String c_email, int c_gender, int acc_id) throws SQLException, ClientException {
        //valid: acc_id and c_id must exist in DB
        if (accM.isIdExist(acc_id) && isIdExist(c_id)) {
            con = getCon.getConnection();
            //Create sql string
            String sqlStr = "UPDATE " + tblName + " SET " + tblCols[0] + " =?,"
                    + tblCols[1] + " =?, "
                    + tblCols[2] + " =?, "
                    + tblCols[3] + "=? "
                    + " WHERE c_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setString(1, c_name);
            pst.setString(2, c_email);
            pst.setInt(3, c_gender);
            pst.setInt(4, acc_id);
            pst.setInt(5, c_id);
            //excute query
            pst.executeUpdate();
            pst.close();
        } else {
            throw new ClientException("Client ID or Account ID is not exist!");
        }
    }

    /**
     * Get Client info from account ID
     *
     * @param accId account ID
     * @return Client or null
     * @throws SQLException
     * @throws ClientException
     */
    public Client getClient(int accId) throws SQLException, ClientException {
        //check whether Account ID is exist
        if (accM.isIdExist(accId)) {
            //connect to DB
            con = getCon.getConnection();
            //create sql string
            String sqlStr = "SELECT * FROM " + tblName + " WHERE acc_id = ?";
            //create query
            pst = con.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);
            //set values
            pst.setInt(1, accId);
            //excute query
            rs = pst.executeQuery();
            //get data
            while (rs.next() != false) {
                int c_id = rs.getInt("c_id");
                String c_name = rs.getString("c_name");
                String c_email = rs.getString("c_email");
                int c_gender = rs.getInt("c_gender");
                int acc_id = rs.getInt("acc_id");
                //create new Client
                Client result = new Client(c_id, c_name, c_email, c_gender, acc_id);
                pst.close();
                return result;
            }
        } else {
            pst.close();
            throw new ClientException("Account ID is not exist!");
        }
        return null;
    }
}
