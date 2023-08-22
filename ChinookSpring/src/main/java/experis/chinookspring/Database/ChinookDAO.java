package experis.chinookspring.Database;

import experis.chinookspring.Models.Customer;
import experis.chinookspring.Models.CustomerCountry;
import experis.chinookspring.Models.CustomerGenre;
import experis.chinookspring.Models.CustomerSpender;
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
    //SELECT c.customer_id, g.name, COUNT(*) AS count FROM customer c JOIN invoice i ON i.customer_id = c.customer_id JOIN invoice_line il ON il.invoice_id = i.invoice_id JOIN track t ON t.track_id = il.track_id JOIN genre g ON g.genre_id = t.genre_id GROUP BY c.customer_id, g.name ORDER BY count DESC LIMIT 1
    //SELECT c.customer_id, i.genre_name, COUNT(*) AS count FROM customer c JOIN invoice i ON
    public void mostPopularGenre() {
        String sql = " SELECT customer_id, genre_id FROM GenreCount where rank=1 WITH GenreCount AS (SELECT c.customer_id, genre_name, COUNT(*)  ) ";


// From customer c
// join invoice i ON c.customer.id = i.customer_id
// join invoice_line x ON i.invoice_id = x.invoice_id
// join track t ON x.track_id = t.track_id
// join genre p ON t.


        try (Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            CustomerGenre customerGenre = null;
            while (result.next()){
                customerGenre = new CustomerGenre(result.getInt("id"), result.getString("genre"), result.getInt("count"));

            }
            System.out.println("Genre: "+ customerGenre.genre() +" "+"Count: "+customerGenre.count());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void highestSpender(){
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

    public void countryWithMostCustomers(){
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


    public void updateFirstNameCustomer(int id, String value) {
        updateCustomer(id,"first_name",value);
    }
    public void updateLastNameCustomer(int id, String value) {
        updateCustomer(id,"last_name",value);
    }
    public void updateCountryCustomer(int id, String value) {
        updateCustomer(id,"country",value);
    }
    public void updatePostalCodeCustomer(int id, String value) {
        updateCustomer(id,"postal_code",value);
    }

    public void updatePhoneCustomer(int id, String value) {
        updateCustomer(id,"phone",value);
    }
    public void updateEmailCustomer(int id, String value) {
        updateCustomer(id,"email",value);
    }

    private void updateCustomer(int id, String parameter ,String value){
        String sql = "UPDATE customer SET "+parameter+"="+ "'" +value+"' WHERE customer_id="+id;
        try (Connection conn = DriverManager.getConnection(url,username,password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            //statement.setString(1, value);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
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
