
package cs366onlinestore;

public class OrderLine {
    
    private int orderLineId;
    
    private int orderId;
    
    private int productId;
    
    private float unitPrice;
    
    private int quantity;
    
    // Constructors
    public OrderLine(){}
    
    public OrderLine(int orderLineId, int orderId, int productId, 
            float unitPrice, int quantity){
        this.orderLineId = orderLineId;
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
    
    // ---------- Getters / Setters ----------

    public int getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(int orderLineId) {
        this.orderLineId = orderLineId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getLineTotal( ) {
        return unitPrice * quantity;
    }
    
    @Override
    public String toString() {
        return "OrderLine{" +
                "orderLineId=" + orderLineId +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", lineTotal=" + getLineTotal() +
                '}';
    }
    
}
