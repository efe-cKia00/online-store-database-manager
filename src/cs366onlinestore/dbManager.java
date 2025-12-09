/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs366onlinestore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbManager {
    
    private String dburl = "";
    private String username = "";
    private String password = "";
    
    public Connection createConnection() throws SQLException{
        Connection conn = DriverManager.getConnection(dburl, username, password);
        return conn;
    }
    
    
}
