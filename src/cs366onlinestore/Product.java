package cs366onlinestore;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int productId;
    private int providerId;
    private int categoryId;
    private int quantity;
    private float unitPrice;
    private String name;
    private String description;

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

    public List<Product> filterByProvider(String providerName) {
        return new ArrayList<>();
    }

    public List<Product> filterByCategory(String category) {
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
}
