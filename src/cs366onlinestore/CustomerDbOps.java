/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs366onlinestore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author zlado
 */
public class CustomerDbOps {

    private Connection dbcon;

    public CustomerDbOps(Connection conn) {
        this.dbcon = conn;
    }

    public void insertCustomerInDb(Customer cust) {
        try {
            String addCustomer = "INSERT INTO customer (firstname, lastname, email, phone, street, city, state, zip) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = dbcon.prepareStatement(addCustomer);
            pstmt.setString(1, cust.getFirstName());
            pstmt.setString(2, cust.getLastName());
            pstmt.setString(3, cust.getEmail());
            pstmt.setString(4, cust.getPhone());
            pstmt.setString(5, cust.getStreet());
            pstmt.setString(6, cust.getCity());
            pstmt.setString(7, cust.getState());
            pstmt.setString(8, cust.getZip());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting customer: " + e.getLocalizedMessage());
        }
    }

    public Customer getCustomerRecord(String fname, String lname, String email) {
        try {
            String getCustomer = "SELECT * from customer "
                    + "WHERE firstname = ? AND lastname = ? AND email = ?";

            PreparedStatement pstmt = dbcon.prepareStatement(getCustomer);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("customer_id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String getEmail = rs.getString("email");
                String phone = rs.getString("phone");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip");

                Customer cust = new Customer(id, firstname, lastname, getEmail, phone, street, city, state, zip);
                return cust;
            } else {
                System.out.println("No Customers returned");
            }
        } catch (SQLException e) {
            System.out.println("Error getting customer" + e.getLocalizedMessage());
        }

        return null;
    }

    public void editCustomerRecord(Customer cust) {
        try {
            String updateCustomer = "UPDATE customer "
                    + "SET firstname = ?, lastname = ?, email = ?, "
                    + "phone = ?, street = ?, city = ?, state = ?, zip = ? "
                    + "WHERE customer_id = ?";
            PreparedStatement pstmt = dbcon.prepareStatement(updateCustomer);
            pstmt.setString(1, cust.getFirstName());
            pstmt.setString(2, cust.getLastName());
            pstmt.setString(3, cust.getEmail());
            pstmt.setString(4, cust.getPhone());
            pstmt.setString(5, cust.getStreet());
            pstmt.setString(6, cust.getCity());
            pstmt.setString(7, cust.getState());
            pstmt.setString(8, cust.getZip());
            pstmt.setInt(9, cust.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Updating Customer: " + e.getLocalizedMessage());
        }
    }

    public void removeCustomerRecord(String fname, String lname, String email, String phone) {
        try {
            String removeCustomer = "DELETE FROM customer WHERE "
                    + "firstname = ? AND lastname = ? AND email = ? AND phone = ?";
            PreparedStatement pstmt = dbcon.prepareStatement(removeCustomer);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }
    
    public ResultSet getAllCustomers(){
        try {
            String removeCustomer = "SELECT * FROM customer";
            PreparedStatement pstmt = dbcon.prepareStatement(removeCustomer);
            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Error retreiving customers: " + e.getLocalizedMessage());
        }
        
        return null;
    }

}
