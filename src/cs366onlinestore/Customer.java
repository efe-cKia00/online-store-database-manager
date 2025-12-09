
package cs366onlinestore;


import java.util.ArrayList;
import java.util.List;



public class Customer {
    
    private int customerId;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String phone;
    
    private String street;
    
    private String city;
    
    private String state;
    
    private int zip;
    
    
        // ---------- Constructors ----------

    
    public Customer(){}
    
    public Customer(int customerId,
                    String firstName,
                    String lastName,
                    String email,
                    String phone,
                    String street,
                    String city,
                    String state,
                    int zip) {

        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    
        // ---------- Getters / Setters ----------

    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }
    
    
       // ---------- Methods from UML ----------

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }

    public void updateAddress(String street, String city, String state, int zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public void updateName(String fname, String lname) {
        this.firstName = fname;
        this.lastName = lname;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Deletes the given customer from the system.
     * In a real application this would call your DatabaseManager / DAO.
     */
    public void deleteCustomer(Customer cust) {
        // TODO: implement deletion logic (e.g., remove from DB or collection)
        // This is just a placeholder to match the UML.
    }

    /**
     * Returns all orders for the given customer.
     * For now this just returns an empty list so the method compiles.
     */
    public List<CustomerOrder> listAllOrders(Customer cust) {
        // TODO: query database or order repository using cust.customerId
        return new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                '}';
    }
}
