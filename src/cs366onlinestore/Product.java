package cs366onlinestore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

public class Product {
    
    private int productId;
    private int providerId;
    private int categoryId;
    private int quantity;
    private float unitPrice;
    private String name;
    private String description;       
    
    // ---------- Constructors ----------
    public Product(int productId,
                   int providerId,
                   int categoryId,
                   int quantity,
                   float unitPrice,
                   String name,
                   String description) {
        this.productId = productId;
        this.providerId = providerId;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.name = name;
        this.description = description;    
    }
    
    // ---------- Getters / Setters ----------
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getCategory() {
        return categoryId;
    }

    public void setCategory(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    // ---------- Methods from UML ----------

    // NOTE: per your plan, actual DB queries will be written in main,
    // so these methods are just stubs to match the UML.

    public List<Product> filterByProvider(String providerName) {
        // TODO: run SELECT ... WHERE providerName = ? in main or another class.
        return new ArrayList<>();
    }

    public List<Product> filterByCategory(String category) {
        // TODO: run SELECT ... WHERE category = ? in main or another class.
        return new ArrayList<>();
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void addQuantity(int amount) {
        if (amount > 0) {
            quantity += amount;
        }
    }

    public List<Product> getMostPopularProduct(int n) {
        // TODO: run a query in main for "most popular" products, then return them.
        return new ArrayList<>();
    }

    public Product getMostExpensiveProduct() {
        // TODO: run a query in main (ORDER BY unitPrice DESC LIMIT 1) and return it.
        return null;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", providerId=" + providerId +
                ", category='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }

    String getProductName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
