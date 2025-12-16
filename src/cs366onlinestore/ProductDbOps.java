/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs366onlinestore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Efe
 */
public class ProductDbOps {

    private final Connection dbcon;

    public ProductDbOps(Connection conn) {
        this.dbcon = conn;
    }

    public List<Integer> getProviderInfo() {
        List<Integer> validIds = new ArrayList<>();
        String sql = "SELECT provider_id, provider_name FROM provider ORDER BY provider_id";
       
        try {
            PreparedStatement stmt = dbcon.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("--- Provider List ---");
            while (rs.next()) {
                int id = rs.getInt("provider_id");
                String name = rs.getString("provider_name");

                System.out.println("ID: " + id + " | Provider: " + name);

                validIds.add(id);
            }
        } catch (SQLException e) {
            System.out.println("Error loading providers: " + e.getMessage());
        }

        return validIds;
    }
    
    public List<Integer> getCategoryInfo() {
        List<Integer> validIds = new ArrayList<>();
        String sql = "SELECT category_id, category_name FROM category ORDER BY category_id";
       
        try {
            PreparedStatement stmt = dbcon.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("--- Category List ---");
            while (rs.next()) {
                int id = rs.getInt("category_id");
                String name = rs.getString("category_name");

                System.out.println("ID: " + id + " | Category: " + name);

                validIds.add(id);
            }
        } catch (SQLException e) {
            System.out.println("Error loading categories: " + e.getMessage());
        }

        return validIds;
    }
    

    public void insertProductInDb(Product prod) {
        String sql = "INSERT INTO product (provider_id, category_id, product_name, description, quantity, unit_price) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = dbcon.prepareStatement(sql)) {
            // Set parameters for the prepared statement
            pstmt.setInt(1, prod.getProviderId());
            pstmt.setInt(2, prod.getCategory());
            pstmt.setString(3, prod.getName());
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
        }
    }

    public Product getProductRecord(String productName, int category) {
        String getProduct = "SELECT * FROM product WHERE product_name = ? AND category_id = ?";

        try (PreparedStatement pstmt = dbcon.prepareStatement(getProduct)) {
            // Set parameters for the prepared statement
            pstmt.setString(1, productName);
            pstmt.setInt(2, category);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int productId = rs.getInt("product_id");
                int providerId = rs.getInt("provider_id");
                int categoryId = rs.getInt("category_id");
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
            return null;
        }
    }

    public void editProductRecord(Product prod) {
        String updateProduct = "UPDATE product "
                + "SET provider_id = ?, category_id = ?, product_name = ?, "
                + "description = ?, quantity = ?, unit_price = ? "
                + "WHERE product_id = ?";

        try (PreparedStatement pstmt = dbcon.prepareStatement(updateProduct)) {
            // Set the values in order
            pstmt.setInt(1, prod.getProviderId());
            pstmt.setInt(2, prod.getCategory());
            pstmt.setString(3, prod.getName());
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
        }
    }

    public ResultSet getProductPriceChangeHistory(String prodName, String prodCategory) {
        try {
            String getProductPriceHistory = "SELECT pc.old_price, pc.new_price, pc.reason_changed, pc.changed_at "
                    + "FROM price_change pc "
                    + "JOIN product AS p "
                    + "ON pc.product_id = p.product_id "
                    + "JOIN category AS c "
                    + "ON p.category_id = c.category_id "
                    + "WHERE "
                    + "p.product_name = ? AND c.category_name = ?";

            PreparedStatement pstmt = dbcon.prepareStatement(getProductPriceHistory);
            pstmt.setString(1, prodName);
            pstmt.setString(2, prodCategory);

            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return null;
        }
    }

    public ResultSet getMostExpensiveProducts(int n) {
        try {
            String getMostExpensiveProducts = "SELECT p.product_name AS product_name, c.category_name, p.unit_price "
                    + "FROM Product p "
                    + "JOIN "
                    + "Category c ON p.category_id = c.category_id "
                    + "ORDER BY "
                    + "p.unit_price DESC "
                    + "LIMIT ?";

            PreparedStatement pstmt = dbcon.prepareStatement(getMostExpensiveProducts);
            pstmt.setInt(1, n);

            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return null;
        }
    }

    public ResultSet getMostPopularProduct() {
        try {
            String sql
                    = "SELECT p.*, SUM(ol.quantity) AS total_sold "
                    + "FROM product p "
                    + "JOIN order_line ol ON p.product_id = ol.product_id "
                    + "GROUP BY p.product_id "
                    + "ORDER BY total_sold DESC "
                    + "LIMIT 1";

            PreparedStatement pstmt = dbcon.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            return rs;

        } catch (SQLException e) {
            System.out.println("Error retrieving most popular product: " + e.getLocalizedMessage());
        }
        return null;
    }
}
