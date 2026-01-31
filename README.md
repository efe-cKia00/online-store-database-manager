
# Online Store Management System

Online Store Management System is a Java-based software application for tracking products, customers, orders, and providers. The authors designed this application as their final project for their datbase system course and it demonstrates their expert use of JDBC with a PostgreSQL database to manage an e-commerce backend.

## Features

- **Product Management:** Add, update, and view products with details such as name, description, price, quantity, category, and provider.
- **Customer Management:** Store and update customer profiles, including contact and address information.
- **Order Management:** Create and view customer orders, including order lines and pricing history.
- **Provider Management:** Track product providers and their contact information.
- **Category Management:** Organize products by category.
- **Price Change Tracking:** Maintain a history of price changes for each product.
- **Database Integration:** Uses PostgreSQL for persistent storage.

## Project Structure

```
src/cs366onlinestore/
  Category.java           # Product category model
  CS366OnlineStore.java   # Main application entry point
  Customer.java           # Customer model
  CustomerDbOps.java      # Customer database operations
  CustomerOrder.java      # Customer order model
  CustomerOrderDbOps.java # Customer order database operations
  dbManager.java          # Database connection manager
  OrderLine.java          # Order line model
  PriceChange.java        # Price change tracking
  Product.java            # Product model
  ProductDbOps.java       # Product database operations
  Provider.java           # Provider model
```

## Requirements

- Java 8 or higher
- PostgreSQL database
- PostgreSQL JDBC driver (see `nbproject/project.properties` for driver path)

## Setup Instructions

1. **Clone the repository:**
	```
	git clone <repo-url>
	```

2. **Configure the Database:**
	- Ensure PostgreSQL is running and create a database named `online-store`.
	- Update the credentials in `dbManager.java` if needed:
	  - Default: `username=server_username`, `password=password`, `jdbcURL=jdbc:postgresql://url`
	- Import or create the required tables (customer, product, provider, order, etc.).
    > The dbManager.jdbc, dbManager.username, and dbManager.password would need to be configured for your specific localhost PostgreSQL database
    > This source code does NOT contain the SQL script containing the dummy data needed to build the PostgreSQL database.

3. **Add the PostgreSQL JDBC Driver:**
	- Download the driver from [PostgreSQL JDBC](https://jdbc.postgresql.org/).
	- Place the JAR file in the referenced path (see `nbproject/project.properties`).

4. **Build and Run:**
	- Use NetBeans or run with Ant using `build.xml`:
	  ```
	  ant clean
	  ant build
	  ant run
	  ```

## Usage

The application runs as a console program. On startup, it connects to the PostgreSQL database and provides options to manage products, customers, orders, and providers.

## Authors

- [Zachary Adolphsen](https://www.linkedin.com/in/zach-adolphsen/)
- [Efe Awo-Osagie](https://www.linkedin.com/in/efe-awo-osagie-b4b796381/)
- [Kal Larson](https://github.com/kalanlarson)

## License

This project is for educational purposes.
