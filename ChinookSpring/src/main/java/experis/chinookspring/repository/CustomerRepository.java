package experis.chinookspring.repository;

import experis.chinookspring.Models.Customer;

import java.util.List;

public interface CustomerRepository extends CRUDRepository<Customer, Integer, String> {


    List<Customer> findAll();
    Customer findByName(String name);

    int insert(Customer customer);

    void update(Integer id, String parameter, String value);

    List<Customer> findPage(int offset, int limit);

    void returnCountry();

    void highestSpender();

    void mostPopularGenre();


}
