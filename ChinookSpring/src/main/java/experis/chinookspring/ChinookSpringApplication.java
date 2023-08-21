package experis.chinookspring;

import experis.chinookspring.Database.ChinookDAO;
import experis.chinookspring.Models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChinookSpringApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ChinookSpringApplication.class, args);
    }

    final
    ChinookDAO dao;

    public ChinookSpringApplication(ChinookDAO dao) {
        this.dao = dao;
    }



    @Override
    public void run(String... args) throws Exception {
        dao.test();
        //dao.displayCustomer(dao.getCustomers());
        //dao.displayCustomer(dao.getCustomer(1));
        //dao.displayCustomer(dao.getCustomer("Kara"));
        //dao.displayCustomer(dao.getCustomerPage(10,5));
        Customer customer = new Customer(60,"Sigurd", "Andersen", "Danmark","8370","1111111111","stuff@stuff.dk");
        dao.insertCustomer(customer);
    }
}
