package experis.chinookspring.repository;

import experis.chinookspring.Models.Customer;

import java.util.List;

public interface CustomerRepository extends CRUDRepository<Customer, Integer, String> {
    /**
     * These are the specific methods for customers.
     * It implements the 4 basic methods from the CRUD and 5 methods specific to the customer.
     * The final method displayCustomer is a simple helper function that prints the output from findAll, findByName, findPage in a nicer fashion.
     *
     */
    List<Customer> findAll();
    Customer findByName(String name);

    int insert(Customer customer);

    void update(Integer id, String parameter, String value);

    List<Customer> findPage(int offset, int limit);

    void returnCountry();

    void highestSpender();

    void mostPopularGenre();

    void displayCustomer(List<Customer> customersList);
}
