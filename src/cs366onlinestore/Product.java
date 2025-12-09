
package cs366onlinestore;

import java.util.ArrayList;
import java.util.List;


public class Product {
    
    private int productId;
    private int providerId;
    private String category;
    private String name;
    private String description;
    private int quantity;
    private float unitPrice;
    
    
    // ---------- Constructors ----------

    public Product() {}
    
    public Product(int productId,
                   int providerId,
                   String category,
                   String name,
                   String description,
                   int quantity,
                   float unitPrice) {

        this.productId = productId;
        this.providerId = providerId;
        this.category = category;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
    
}
