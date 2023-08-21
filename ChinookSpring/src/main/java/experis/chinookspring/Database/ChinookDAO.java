package experis.chinookspring.Database;

import experis.chinookspring.Models.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChinookDAO {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    public ChinookDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public ChinookDAO() {

    }

    public void test() {
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to Postgres...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int insertCustomer(Customer customer) {
        int id= customer.customer_id();
        String name=customer.first_name();
        String lastName=customer.last_name();
        String country =customer.country();
        String postalCode = customer.postal_code();
        String phone = customer.phone();
        String email = customer.email();
       // "+name+","+lastName+","+country+","+postalCode+","+phone+","+email+"
        String sql = "INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        int result =1;
        try (Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setString(3,lastName);
            statement.setString(8,country);
            statement.setString(9,postalCode);
            statement.setString(10,phone);
            statement.setString(12,email);
            result = statement.executeUpdate();
        }catch (SQLException e) {
            System.out.println("FUCKING ERRORS MAN: "+e.getMessage());
        }

        return result;
    }

    public List<Customer> getCustomer() {
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

    public List<Customer> getCustomer(int id) {
        List<Customer> customersList = new ArrayList<>();

        String sql = "select customer_id, first_name, last_name, country, postal_code, phone, email FROM customer WHERE customer_id="+id;

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

    public List<Customer> getCustomer(String name) {
        List<Customer> customersList = new ArrayList<>();

        String sql = "select customer_id, first_name, last_name, country, postal_code, phone, email FROM customer WHERE first_name LIKE"+"'"+name+"'";
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

    public List<Customer> getCustomerPage(int offset, int limit) {
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

    public void displayCustomer(List<Customer> customersList) {
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
}
