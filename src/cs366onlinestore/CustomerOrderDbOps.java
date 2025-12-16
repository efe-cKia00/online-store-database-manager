package cs366onlinestore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CustomerOrderDbOps {

    private Connection dbcon;

    public CustomerOrderDbOps(Connection conn) {
        this.dbcon = conn;
    }

    // --------------------------------------------------
    // INSERT CUSTOMER ORDER
    // --------------------------------------------------
    public int insertCustomerOrder(CustomerOrder order) {
        try {
            String insertOrder =
                    "INSERT INTO customer_order (customer_id, order_date) " +
                    "VALUES (?, ?) RETURNING order_id";

            PreparedStatement pstmt = dbcon.prepareStatement(insertOrder);
            pstmt.setInt(1, order.getCustomerId());
            pstmt.setTimestamp(2, Timestamp.valueOf(order.getOrderTime()));

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("order_id");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting customer order: " + e.getLocalizedMessage());
        }
        return -1;
    }

    // --------------------------------------------------
    // GET ALL ORDERS FOR A CUSTOMER
    // --------------------------------------------------
    public ResultSet getOrdersByCustomer(int customerId) {
        try {
            String getOrders =
                    "SELECT * FROM customer_order WHERE customer_id = ? ORDER BY order_date";

            PreparedStatement pstmt = dbcon.prepareStatement(getOrders);
            pstmt.setInt(1, customerId);

            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error retrieving customer orders: " + e.getLocalizedMessage());
        }
        return null;
    }

    // --------------------------------------------------
    // GET A SINGLE ORDER BY ID
    // --------------------------------------------------
    public ResultSet getOrderById(int orderId) {
        try {
            String getOrder =
                    "SELECT * FROM customer_order WHERE order_id = ?";

            PreparedStatement pstmt = dbcon.prepareStatement(getOrder);
            pstmt.setInt(1, orderId);

            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error retrieving order: " + e.getLocalizedMessage());
        }
        return null;
    }

    // --------------------------------------------------
    // DELETE ORDER (order_line cascades automatically)
    // --------------------------------------------------
    public void deleteOrder(int orderId) {
        try {
            String deleteOrder =
                    "DELETE FROM customer_order WHERE order_id = ?";

            PreparedStatement pstmt = dbcon.prepareStatement(deleteOrder);
            pstmt.setInt(1, orderId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting order: " + e.getLocalizedMessage());
        }
    }
}