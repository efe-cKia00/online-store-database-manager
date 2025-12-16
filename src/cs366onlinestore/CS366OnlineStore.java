/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cs366onlinestore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @author Zachary Adolphsen
 * @author Efe Awo-Osagie
 * @author Kal Larson
 */
public class CS366OnlineStore {

    private static dbManager dbManager = new dbManager();
    private static Connection dbcon;
    private static CustomerDbOps custDbOp;
    private static CustomerOrderDbOps custOrderDbOp;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            dbcon = dbManager.createConnection();
            if (dbcon == null) {
                System.out.println("CRITICAL ERROR: dbManager returned a null connection. Check your DB path!");
                return; // Stop the program here!
            }
            custDbOp = new CustomerDbOps(dbcon);
            custOrderDbOp = new CustomerOrderDbOps(dbcon);
            System.out.println("Success");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        Scanner scan = new Scanner(System.in);

        boolean running = true;

        System.out.println("Welcome to the Online Store Management System");
        while (running) {
            System.out.println("======================================================");
            System.out.println("What would you like to do?");
            System.out.println("======================================================");
            System.out.println("1) Work with products");
            System.out.println("2) Work with customer");
            System.out.println("3) Work with customer orders");
            System.out.println("0) Exit Application");
            System.out.print("\nEnter choice: ");

            int choice = getUserInput(scan);

            switch (choice) {
                case 1 -> { //runProductMenu
                }
                case 2 ->
                    runCustomerMenu(scan);
                case 3 ->
                    runCustomerOrderMenu(scan);
                case 0 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default ->
                    System.out.println("Invalid selection. Please try again.");
            }
        }
        scan.close();
    }

    private static int getUserInput(Scanner scan) {
        try {
            String input = scan.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1; // Return -1 to trigger "Invalid selection" in switch cases
        }
    }

    private static void runCustomerMenu(Scanner s) {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- CUSTOMER MANAGEMENT MENU ---");
            System.out.println("1) Add new customer");
            System.out.println("2) Edit existing customer");
            System.out.println("3) Remove existing customer");
            System.out.println("4) View all customers");
            System.out.println("0) Back to Main Menu");
            System.out.print("Select Action: ");

            int choice = getUserInput(s);

            switch (choice) {
                case 1 ->
                    AddCustomer(s);
                case 2 ->
                    EditExistingCustomer(s);
                case 3 ->
                    RemoveCustomer(s);
                case 4 ->
                    GetAllCustomers();
                case 0 ->
                    inMenu = false;
                default ->
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void runCustomerOrderMenu(Scanner s) {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- CUSTOMER ORDER MANAGEMENT MENU ---");
            System.out.println("1) View all orders for a given customer");
            System.out.println("2) View all products ordered by customer");
            System.out.println("0) Back to Main Menu");
            System.out.print("Select Action: ");

            int choice = getUserInput(s);

            switch (choice) {
                case 1 ->
                    GetCustomerOrderHistory(s);
                case 2 ->
                    GetCustomerOrderedProductHistory(s);
                case 0 ->
                    inMenu = false;
                default ->
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void AddCustomer(Scanner s) {
        String fname, lname, email, phone, street, city, state, zip;

        // FIRST NAME
        while (true) {
            System.out.println("Enter First Name:");
            fname = s.nextLine().trim();
            if (fname.matches("[a-zA-Z]+")) {
                break;
            }
            System.out.println("Invalid First Name. Only letters allowed");
        }

        while (true) {
            System.out.println("Enter Last Name:");
            lname = s.nextLine().trim();
            if (lname.matches("[a-zA-Z]+")) {
                break;
            }
            System.out.println("Invalid Last Name. Only letters allowed");
        }

        while (true) {
            System.out.println("Enter Email: ");
            email = s.nextLine().trim();
            if (email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
                break;
            }
            System.out.println("Invalid Email.");
        }

        while (true) {
            System.out.println("Enter Phone Number (i.e. 1234567890): ");
            phone = s.nextLine().trim();
            if (phone.matches("\\d{10}")) {
                break;
            }
            System.out.println("Invalid Phone Number. Only numbers allowed");
        }

        while (true) {
            System.out.println("Enter Street: ");
            street = s.nextLine().trim();
            if (street.matches("^[a-zA-Z0-9]+\\s+[a-zA-Z0-9\\s.,'-]+$")) {
                break;
            }
            System.out.println("Invalid Street. Include house num, street name, and street type");
        }

        while (true) {
            System.out.println("Enter City: ");
            city = s.nextLine().trim();
            if (city.matches("^[a-zA-Z .]+$")) {
                break;
            }
            System.out.println("Invalid City. Only letters allowed");
        }

        while (true) {
            System.out.println("Enter State (i.e. ND, MN, WA): ");
            state = s.nextLine().trim();
            if (state.matches("^[A-Z]{2}$")) {
                break;
            }
            System.out.println("Invalid State. Use 2-letter abbreviation");
        }

        while (true) {
            System.out.println("Enter Zip: ");
            zip = s.nextLine().trim();
            if (zip.matches("[0-9]{5}")) {
                break;
            }
            System.out.println("Invalid Zip Code. Zip must be exactly 5 digits");
        }

        // creating customer object (placeholder id)
        Customer cust = new Customer(0, fname, lname, email, phone, street, city, state, zip);

        // insert known data into db
        custDbOp.insertCustomerInDb(cust);
    }

    public static void EditExistingCustomer(Scanner s) {
        // Get fname and lname of Customer to edit
        System.out.println("Enter Details of Customer to edit");
        System.out.println("Customers First Name:");
        String firstname = s.next();
        System.out.println("Customers Last Name: ");
        String lastname = s.next();
        System.out.println("Customers Email: ");
        String custEmail = s.next();

        s.nextLine(); // prevent skipping fname edit

        //Get Customer record from DB
        Customer cust = custDbOp.getCustomerRecord(firstname.strip(), lastname.strip(), custEmail.strip());
        if (cust == null) {
            System.out.println("No Customer Returned");
            return;
        }
        System.out.println("=====================================");
        System.out.println("SUCCESS GETTING CUSTOMER!");
        System.out.println("=====================================");

        String fname, lname, email, phone, street, city, state, zip;

        // Edit Customers Details
        System.out.println("EDIT DETAILS");
        System.out.println("=====================================");

        while (true) {
            System.out.println("Edit First Name? (Current = " + cust.getFirstName() + "): ");
            String input = s.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Skipping Firstname");
                fname = cust.getFirstName();
                break;
            }
            if (input.matches("[a-zA-Z]+")) {
                fname = input;
                break;
            }
            System.out.println("Invalid first name. Must only contain letters");
        }

        while (true) {
            System.out.println("Edit Last Name? (Current = " + cust.getLastName() + "): ");
            String input = s.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Skipping Lastname");
                lname = cust.getLastName();
                break;
            }
            if (input.matches("[a-zA-Z]+")) {
                lname = input;
                break;
            }
            System.out.println("Invalid last name. Must only contain letters");
        }

        while (true) {
            System.out.println("Edit Email? (Current = " + cust.getEmail() + "): ");
            String input = s.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Skipping Email");
                email = cust.getEmail();
                break;
            }
            if (input.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
                email = input;
                break;
            }
            System.out.println("Invalid email");
        }

        while (true) {
            System.out.println("Edit Phone? (Current = " + cust.getPhone() + "): ");
            String input = s.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Skipping Phone");
                phone = cust.getPhone();
                break;
            }
            if (input.matches("\\d{10}")) {
                phone = input;
                break;
            }
            System.out.println("Invalid phone number. Must only contain numbers");
        }

        while (true) {
            System.out.println("Edit Street? (Current = " + cust.getStreet() + "): ");
            String input = s.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Skipping Street");
                street = cust.getStreet();
                break;
            }
            if (input.matches("^[a-zA-Z0-9]+\\s+[a-zA-Z0-9\\s.,'-]+$")) {
                street = input;
                break;
            }
            System.out.println("Invalid Street. Must only contain numbers, letters, and spaces");
        }

        while (true) {
            System.out.println("Edit City? (Current = " + cust.getCity() + "): ");
            String input = s.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Skipping City");
                city = cust.getCity();
                break;
            }
            if (input.matches("^[a-zA-Z .]+$")) {
                city = input;
                break;
            }
            System.out.println("Invalid City. Must only contain letters");
        }

        while (true) {
            System.out.println("Edit State? (Current = " + cust.getState() + "): ");
            String input = s.nextLine().trim().toUpperCase();
            if (input.isEmpty()) {
                System.out.println("Skipping State");
                state = cust.getState();
                break;
            }
            if (input.matches("^[A-Z]{2}$")) {
                state = input;
                break;
            }
            System.out.println("Invalid State. Must be 2 letter abbreviation");
        }

        while (true) {
            System.out.println("Edit ZIP? (Current = " + cust.getZip() + "): ");
            String input = s.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Skipping ZIP");
                zip = cust.getZip();
                break;
            }
            if (input.matches("[0-9]{5}")) {
                zip = input;
                break;
            }
            System.out.println("Invalid ZIP. Must only be 5 numbers in length");
        }
        // make updated customer
        Customer updatedCust = new Customer(
                cust.getId(),
                fname,
                lname,
                email,
                phone,
                street,
                city,
                state,
                zip
        );

        //send it to the db
        custDbOp.editCustomerRecord(updatedCust);
    }

    public static void RemoveCustomer(Scanner s) {
        // Get fname, lname, email, phone_num of Customer to drop
        System.out.println("Enter Details of Customer to edit");
        System.out.println("Customers First Name:");
        String firstname = s.nextLine().trim();
        System.out.println("Customers Last Name: ");
        String lastname = s.nextLine().trim();
        System.out.println("Customers Email: ");
        String custEmail = s.nextLine().trim();
        System.out.println("Customers Phone Number: ");
        String phone = s.nextLine().trim();

        custDbOp.removeCustomerRecord(firstname, lastname, custEmail, phone);
    }

    public static void GetAllCustomers() {
        ResultSet rs = custDbOp.getAllCustomers();
        if (rs == null) {
            System.out.println("No Customers Found :(");
            return;
        }
        try {
            System.out.println("Customer Info");
            while (rs.next()) {
                System.out.println("=====================================");
                System.out.println("Name: " + rs.getString("firstname") + " " + rs.getString("lastname"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone Number: " + rs.getString("phone"));
                System.out.println("Address: " + rs.getString("street") + " "
                        + rs.getString("city") + ", "
                        + rs.getString("state") + ", "
                        + rs.getString("zip"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all customer records: " + e.getLocalizedMessage());
        }

    }
    
    public static void GetCustomerOrderHistory(Scanner s){
         // Get fname and lname of Customer to edit
        System.out.println("Enter Details of Customer");
        System.out.println("Customers First Name:");
        String firstname = s.next().trim();
        System.out.println("Customers Last Name: ");
        String lastname = s.next().trim();
        System.out.println("Customers Email: ");
        String custEmail = s.next().trim();

        s.nextLine();
        
        ResultSet rs = custOrderDbOp.viewCustomerOrderHistory(firstname, lastname, custEmail);
        
        if (rs == null) {
            System.out.println("No Orders Found");
            return;
        }
        try {
            System.out.println("Customer Orders");
            while (rs.next()) {
                System.out.println("=====================================");
                System.out.println("Order Id: " + rs.getString("order_id"));
                System.out.println("Order Time: " + rs.getString("order_date"));
                System.out.println("Order Price: " + rs.getString("total_order_price"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all customer records: " + e.getLocalizedMessage());
        }
    }
    
    public static void GetCustomerOrderedProductHistory(Scanner s){
         // Get fname and lname of Customer to edit
        System.out.println("Enter Details of Customer");
        System.out.println("Customers First Name:");
        String firstname = s.next().trim();
        System.out.println("Customers Last Name: ");
        String lastname = s.next().trim();
        System.out.println("Customers Email: ");
        String custEmail = s.next().trim();

        s.nextLine();
        
        ResultSet rs = custOrderDbOp.viewProductsOrderedByCustomer(firstname, lastname, custEmail);
        
        if (rs == null) {
            System.out.println("No Orders Found");
            return;
        }
        try {
            System.out.println("Customer Orders");
            while (rs.next()) {
                System.out.println("=====================================");
                System.out.println("Product Name: " + rs.getString("productName"));
                System.out.println("Quantity Ordered: " + rs.getString("quantity_ordered"));
                System.out.println("Product Order Price: " + rs.getString("product_order_price"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all customer records: " + e.getLocalizedMessage());
        }
        Scanner scan = new Scanner(System.in);

        AddCustomer(scan);
    }

    public static void AddCustomer(Scanner s) {
        System.out.println("Enter First Name:");
        String fname = s.nextLine();
        System.out.println("Enter Last Name: ");
        String lname = s.nextLine();
        System.out.println("Enter Email: ");
        String email = s.nextLine();
        System.out.println("Enter Phone Number: ");
        String phone = s.nextLine();
        System.out.println("Enter Street: ");
        String street = s.nextLine();
        System.out.println("Enter City: ");
        String city = s.nextLine();
        System.out.println("Enter State: ");
        String state = s.nextLine();
        System.out.println("Enter Zip: ");
        String zip = s.nextLine();

        // creating customer object
        Customer cust = new Customer(fname, lname, email, phone, street, city, state, zip);

        // insert known data into db
        custDbOp.insertCustomerInDb(cust);
    }

}
