package cs366onlinestore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrder {

    private int orderId;
    private int customerId;
    private LocalDateTime orderTime;

    private List<OrderLine> orderLines = new ArrayList<>();

    public CustomerOrder(int orderId, int customerId, LocalDateTime orderTime) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderTime = orderTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public float calculateTotalPrice() {
        float total = 0.0f;
        for (OrderLine line : orderLines) {
            total += line.getLineTotal();
        }
        return total;
    }

    public void addOrderLine(OrderLine orderLine) {
        if (orderLine != null) {
            orderLines.add(orderLine);
        }
    }

    public void removeOrderLine(OrderLine orderLine) {
        orderLines.remove(orderLine);
    }

    public void cancelOrder() {
        orderLines.clear();

    }

}
