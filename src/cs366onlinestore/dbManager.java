package cs366onlinestore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbManager {
    static String jdbcURL = "jdbc:postgresql://url";
    static String username = "postgre_server_name";
    static String password = "database_password";
    
    public Connection createConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Database connection established successfully!");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Got SQL exception");
            e.printStackTrace();
            throw e;
        }
    }
}