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
 * @author Efe
 */

public class ProductDbOps {
    
    private Connection dbcon;

    public ProductDbOps(Connection conn) {
        this.dbcon = conn;
    }
    
    public void insertProductInDb(Product prod) {
        String sql = "INSERT INTO products (provider_id, category, product_name, description, quantity, unit_price) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = dbcon.prepareStatement(sql)) {
            // Set parameters for the prepared statement
            pstmt.setInt(1, prod.getProviderId());
            pstmt.setInt(2, prod.getCategory());
            pstmt.setString(3, prod.getProductName());
            pstmt.setString(4, prod.getDescription());
            pstmt.setInt(5, prod.getQuantity());
            pstmt.setFloat(6, prod.getUnitPrice());
            
            // Execute the insert
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Product successfully added to database!");
            } else {
                System.out.println("Failed to add product to database.");
            }
            
        } catch (SQLException e) {
            System.out.println("Error inserting product: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    
    public Product getProductRecord(String productName, int category) {
    String getProduct = "SELECT * FROM products WHERE product_name = ? AND category = ?";
    
    try (PreparedStatement pstmt = dbcon.prepareStatement(getProduct)) {
        // Set parameters for the prepared statement
        pstmt.setString(1, productName);
        pstmt.setInt(2, category);
        
        // Execute the query
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            int productId = rs.getInt("product_id");
            int providerId = rs.getInt("provider_id");
            int categoryId = rs.getInt("category");
            String prodName = rs.getString("product_name");
            String description = rs.getString("description");
            int quantity = rs.getInt("quantity");
            float unitPrice = rs.getFloat("unit_price");
            
            // Create and return the Product object
            Product prod = new Product(
                productId,
                providerId,
                categoryId,
                quantity,
                unitPrice,
                prodName,
                description                                
            );
            
            System.out.println("Product found: " + prodName);
            return prod;
        } else {
            System.out.println("No product found with name: " + productName + " and category: " + category);
            return null;
        }
        
        } catch (SQLException e) {
            System.out.println("Error retrieving product: " + e.getLocalizedMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public void editProductRecord(Product prod) {
    String updateProduct = "UPDATE products "
            + "SET provider_id = ?, category = ?, product_name = ?, "
            + "description = ?, quantity = ?, unit_price = ? "
            + "WHERE product_id = ?";
    
    try (PreparedStatement pstmt = dbcon.prepareStatement(updateProduct)) {
        // Set the values in order
        pstmt.setInt(1, prod.getProviderId());
        pstmt.setInt(2, prod.getCategory());
        pstmt.setString(3, prod.getProductName());
        pstmt.setString(4, prod.getDescription());
        pstmt.setInt(5, prod.getQuantity());
        pstmt.setFloat(6, prod.getUnitPrice());
        pstmt.setInt(7, prod.getProductId()); // WHERE clause
        
        int rowsAffected = pstmt.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Product successfully updated!");
        } else {
            System.out.println("No product found with that ID.");
        }
        
        } catch (SQLException e) {
            System.out.println("Error Updating Product: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
