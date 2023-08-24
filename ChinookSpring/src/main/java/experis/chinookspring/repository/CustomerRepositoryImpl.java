package experis.chinookspring.repository;

import experis.chinookspring.Models.Customer;
import experis.chinookspring.Models.CustomerCountry;
import experis.chinookspring.Models.CustomerSpender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{

    private final String url;
    private final String username;
    private final String password;

    /**
     * Constructor that takes the fields values for the database connections.
     * The values are stored in application.properties and fetched using  spring boot @Value annotation
     *
     */
    public CustomerRepositoryImpl(
            @Value("${spring.datasource.url}")  String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * findAll makes a list og customers and fetches id, name, country, postal code, phone and emmail from all of them via the SQL statement.
     * THen it uses prepared statements to avoid SQL injection.
     * @return It returns a list of customers.
     */
    @Override
    public List<Customer> findAll() {
        List<Customer> customersList = new ArrayList<>();

        String sql = "select customer_id, first_name, last_name, country, postal_code, phone, email FROM customer";

        try(Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            /**
             * Here the fetched values are added to a customer and continuously added to the customer list.
             */
            while(result.next()) {
                Customer customer = new Customer(result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customersList.add(customer);
            }
        }catch (SQLException e) {
            System.out.println("FUCKING ERRORS MAN: "+e.getMessage());
        }
        return customersList;
    }

    /**Find specific customer by id
     * This method execute
     * @param id this is the identifier used to find the customer.
     * @return this method returns a Customer object if the id matches a customer_id in the Chinook database otherwise it returns null.
     * */
    @Override
    public Customer findById(Integer id) {
        Customer customer = null;

        String sql = String.format("select customer_id, first_name, last_name, country, postal_code, phone, email FROM customer WHERE customer_id=%s", id);

        try(Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                customer = new Customer(result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
            }
        }catch (SQLException e) {
            System.out.println("FUCKING ERRORS MAN: "+e.getMessage());
        }
        return customer;
    }

    /**
     * This finds a specific customer by name.
     * @param name  This is the name of the customer that needs to be found.
     * @return a customer object if the name matches otherwise null.
     */
    @Override
    public Customer findByName(String name) {
        Customer customer = null;

        String sql = "select customer_id, first_name, last_name, country, postal_code, phone, email FROM customer WHERE first_name LIKE"+"'"+name+"'";
        try(Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                customer = new Customer(result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );

            }
        }catch (SQLException e) {
            System.out.println("FUCKING ERRORS MAN: "+e.getMessage());
        }
        return customer;
    }

    /**
     * This finds all customer within a set start and end range.
     * @param offset This is the starting range.
     * @param limit This is the end range.
     * @return a list of customer within the range.
     */
    @Override
    public List<Customer> findPage(int offset, int limit) {
        List<Customer> customersList = new ArrayList<>();
        // select customer_id, first_name, last_name, country, postal_code, phone, email FROM customer LIMIT 10 OFFSET 2;
        String sql = "select customer_id, first_name, last_name, country, postal_code, phone, email FROM customer LIMIT "+limit+" OFFSET "+offset;
        try(Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Customer customer = new Customer(result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customersList.add(customer);
            }
        }catch (SQLException e) {
            System.out.println("FUCKING ERRORS MAN: "+e.getMessage());
        }
        return customersList;

    }

    /**
     * Inserts a new customer into database
      * @param customer Customer object that is is inserted
     * @return 1 if a customer was added, otherwise 0.
     */
    @Override
    public int insert(Customer customer) {
        String name=customer.first_name();
        String lastName=customer.last_name();
        String country =customer.country();
        String postalCode = customer.postal_code();
        String phone = customer.phone();
        String email = customer.email();
        // "+name+","+lastName+","+country+","+postalCode+","+phone+","+email+"
        String sql = "INSERT INTO customer (first_name, last_name, country, postal_code, phone, email) VALUES (?,?,?,?,?,?)";
        int result =0;
        try (Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            //statement.setInt(1,id);
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setString(3,country);
            statement.setString(4,postalCode);
            statement.setString(5,phone);
            statement.setString(6,email);
            result = statement.executeUpdate();
        }catch (SQLException e) {
            System.out.println("FUCKING ERRORS MAN: "+e.getMessage());
        }

        return result;
    }

    /**
     * Updates a customer on a specified parameter.
     * @param id this is the identifier of the entry.
     * @param parameter this is the name of the column to
     * @param value this is the new value to replace the older value.
     */
    @Override
    public void update(Integer id, String parameter, String value) {
        String sql = String.format("UPDATE customer SET "+parameter+"="+ "'" +value+"' WHERE customer_id=");
        try (Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            //statement.setString(1, value);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the country with the most customers and prints the country and the count.
     */
    @Override
    public void returnCountry() {
        String sql = "SELECT country, COUNT(*) AS num FROM customer GROUP BY country ORDER BY num DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            CustomerCountry customerCountry = null;
            while (result.next()){
                customerCountry = new CustomerCountry(result.getInt("num"), result.getString("country"));

            }
            System.out.println("Country: "+ customerCountry.country() +" "+"Count: "+customerCountry.count());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the customer by name that had the highest total on the invoice.
     */
    @Override
    public void highestSpender() {
        String sql = "SELECT  c.first_name, c.last_name, SUM(i.total) AS amount FROM customer c INNER JOIN invoice i ON i.customer_id = c.customer_id GROUP BY c.customer_id ORDER BY amount DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            CustomerSpender customerSpender = null;
            while (result.next()){
                customerSpender = new CustomerSpender(result.getString("first_name"), result.getString("last_name"),result.getDouble("amount"));

            }
            System.out.println("First Name: "+ customerSpender.name() +" "+"Last Name: "+customerSpender.lastName() + " amount: " + customerSpender.amount());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void mostPopularGenre() {

    }

    /** Display list of Customers. Helper function
     * This method iterates through the customerList and prints all fields in a concatenated string on a single line.
     * @param customersList this is a list of Customer objects.
     * */
    @Override
    public void displayListOfCustomer(List<Customer> customersList) {
        for (Customer customer: customersList) {
            System.out.println(
                    customer.customer_id() + " " +
                            customer.first_name()+ " " +
                            customer.last_name()+ " " +
                            customer.country()+ " " +
                            customer.postal_code()+ " " +

                            customer.phone()+ " " +
                            customer.email());
        }

    }

    /**
     * Print a customer. Helper function
     * @param customer This is the customer to be printed
     */
    @Override
    public void displayCustomer(Customer customer) {
            System.out.println(
                    customer.customer_id() + " " +
                            customer.first_name()+ " " +
                            customer.last_name()+ " " +
                            customer.country()+ " " +
                            customer.postal_code()+ " " +

                            customer.phone()+ " " +
                            customer.email());
        }


}
