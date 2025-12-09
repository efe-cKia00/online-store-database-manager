/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package cs366onlinestore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @author Zachary Adolphsen
 * @author Efe Awo-Osagie
 * @author Kal Larson
 */

public class CS366OnlineStore {
    
    private static dbManager dbManager = new dbManager();
    private static Connection dbcon;  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //create connection
        try{
        dbcon = dbManager.createConnection();
        System.out.println("Successful connection");
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}
