/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ThanhKH
 */
public class GetConnection {
    private static String URL = "jdbc:sqlserver://localhost; databaseName=WebsiteManager";
    private static String UID = "bnt";
    private static String PWD = "bnt";
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public GetConnection() {
    }

    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        con = (Connection) DriverManager.getConnection(URL, UID, PWD);        
        return con;
    }
    public void test(){
        
    }

}
