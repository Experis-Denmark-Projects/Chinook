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
        System.out.println("Requirement 1: ");
        System.out.println("=============================================");
            customerRepository.displayListOfCustomer(customerRepository.findAll());
        System.out.println("=============================================");
        //2. Find and display a specific customer by id.
        System.out.println("Requirement 2: ");
        customerRepository.displayCustomer(customerRepository.findById(1));
        System.out.println("=============================================");
        //3. Find and display a specific customer by name.
        System.out.println("Requirement 3: ");
            customerRepository.displayCustomer(customerRepository.findByName("Kara"));
        System.out.println("=============================================");
        //4. Get customer page using limit and offset.
        System.out.println("Requirement 4: ");
            customerRepository.displayListOfCustomer(customerRepository.findPage(5,5));
        System.out.println("=============================================");
        //5. Add a new customer.
        System.out.println("Requirement 5: ");
            //System.out.println("Should print 1 if inserted: "+customerRepository.insert(new Customer(1,"steve", "andersen","denmark", "9000","12121212","stuff@stuff.dk")));
        System.out.println("=============================================");
        //6. Update an existing customer.
        System.out.println("Requirement 6: ");
            //customerRepository.update(70,"first_name","John");
        System.out.println("=============================================");
        //7. Get the country with the most customers.
        System.out.println("Requirement 7: ");
            //customerRepository.returnCountry();
        System.out.println("=============================================");
        //8. Get the customer who is the highest spender.
        System.out.println("Requirement 8: ");
            //customerRepository.highestSpender();
        System.out.println("=============================================");
        //9. Get the most popular music genre for a specific customer.
        System.out.println("Requirement 9: ");

        System.out.println("=============================================");
    }
}
