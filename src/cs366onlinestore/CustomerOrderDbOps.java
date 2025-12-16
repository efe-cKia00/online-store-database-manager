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

//    // --------------------------------------------------
//    // INSERT CUSTOMER ORDER (NOT USED FOR FINAL PROJECT DEMO)
//    // --------------------------------------------------
//    public int insertCustomerOrder(CustomerOrder order) {
//        try {
//            String insertOrder
//                    = "INSERT INTO customer_order (customer_id, order_date) "
//                    + "VALUES (?, ?) RETURNING order_id";
//
//            PreparedStatement pstmt = dbcon.prepareStatement(insertOrder);
//            pstmt.setInt(1, order.getCustomerId());
//            pstmt.setTimestamp(2, Timestamp.valueOf(order.getOrderTime()));
//
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("order_id");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error inserting customer order: " + e.getLocalizedMessage());
//        }
//        return -1;
//    }

    // --------------------------------------------------
    // GET ALL ORDERS FOR A CUSTOMER
    // --------------------------------------------------
    public ResultSet viewCustomerOrderHistory(String fname, String lname, String email) {
        try {
            String getOrders
                    = "SELECT co.order_id, co.order_date, SUM(ol.unit_price * ol.quantity) AS total_order_price "
                    + "FROM customer c "
                    + "JOIN customer_order co ON c.customer_id = co.customer_id "
                    + "JOIN order_line ol ON co.order_id = ol.order_id "
                    + "WHERE c.firstname = ? AND c.lastname = ? AND c.email = ? "
                    + "GROUP BY co.order_id, co.order_date "
                    + "ORDER BY co.order_date";
            PreparedStatement pstmt = dbcon.prepareStatement(getOrders);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, email);

            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error retrieving customer orders: " + e.getLocalizedMessage());
        }
        return null;
    }

    public ResultSet viewProductsOrderedByCustomer(String fname, String lname, String email) {
        try {
            String getProductOrderHistory
                    = "SELECT p.product_name AS productName, ol.quantity AS quantity_ordered, (ol.unit_price * ol.quantity) AS product_order_price "
                    + "FROM customer AS c "
                    + "JOIN customer_order AS co ON c.customer_id = co.customer_id "
                    + "JOIN order_line AS ol ON co.order_id = ol.order_id "
                    + "JOIN product AS p ON ol.product_id = p.product_id "
                    + "WHERE c.firstname = ? AND c.lastname = ? AND c.email = ?";
            PreparedStatement pstmt = dbcon.prepareStatement(getProductOrderHistory);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, email);

            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error retrieving information: " + e.getLocalizedMessage());
        }
        return null;
    }
//
//    // --------------------------------------------------
//    // GET A SINGLE ORDER BY ID (NOT USED FOR FINAL PROJECT)
//    // --------------------------------------------------
//    public ResultSet getOrderById(int orderId) {
//        try {
//            String getOrder
//                    = "SELECT * FROM customer_order WHERE order_id = ?";
//
//            PreparedStatement pstmt = dbcon.prepareStatement(getOrder);
//            pstmt.setInt(1, orderId);
//
//            return pstmt.executeQuery();
//        } catch (SQLException e) {
//            System.out.println("Error retrieving order: " + e.getLocalizedMessage());
//        }
//        return null;
//    }
//
//    // --------------------------------------------------
//    // DELETE ORDER (order_line cascades automatically)
//    // --------------------------------------------------
//    public void deleteOrder(int orderId) {
//        try {
//            String deleteOrder
//                    = "DELETE FROM customer_order WHERE order_id = ?";
//
//            PreparedStatement pstmt = dbcon.prepareStatement(deleteOrder);
//            pstmt.setInt(1, orderId);
//
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Error deleting order: " + e.getLocalizedMessage());
//        }
//    }
}
