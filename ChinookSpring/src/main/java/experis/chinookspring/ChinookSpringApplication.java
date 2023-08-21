package experis.chinookspring;

import experis.chinookspring.Database.ChinookDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChinookSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChinookSpringApplication.class, args);

        ChinookDAO dao = new ChinookDAO();

        dao.test();

    }
}
