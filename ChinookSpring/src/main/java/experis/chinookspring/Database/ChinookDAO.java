package experis.chinookspring.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChinookDAO {

    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String username= "postgres";
    private String password = "postgres";


    public ChinookDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public ChinookDAO() {

    }

    public void test() {
        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            System.out.println("Connected to Postgres...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




}
