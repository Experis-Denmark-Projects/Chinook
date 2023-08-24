package experis.chinookspring.Runner;

import experis.chinookspring.Models.Customer;
import experis.chinookspring.repository.CustomerRepository;
import experis.chinookspring.repository.CustomerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/***/
@Component
public class CustomerRunner implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerRunner(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        /********** Customer requirements - Tasks **********/

        //1. Find and display all customers
        customerRepository.displayCustomer(customerRepository.findAll());

        //2. Find and display a specific customer by id.

        //3. Find and display a specific customer by name.

        //4. Get customer page using limit and offset.

        //5. Add a new customer.
        //System.out.println("Should print 1 if inserted: "+customerRepository.insert(new Customer(1,"steve", "andersen","denmark", "9000","12121212","stuff@stuff.dk")));
        //6. Update an existing customer.

        //7. Get the country with the most customers.

        //8. Get the customer who is the highest spender.

        //9. Get the most popular music genre for a specific customer.
    }
}
