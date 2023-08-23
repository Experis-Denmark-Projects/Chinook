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

    public CustomerRepositoryImpl(
            @Value("${spring.datasource.url}")  String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customersList = new ArrayList<>();

        String sql = "select customer_id, first_name, last_name, country, postal_code, phone, email FROM customer";

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
        int result =1;
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
}
