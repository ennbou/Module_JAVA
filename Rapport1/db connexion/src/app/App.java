package app;

import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.2.2.2/test_db?useSSL=false", "ennbou", "ennbou10");
            Statement stm = c.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM table1");
            if(r.next()){
                System.out.println(r.getString("name"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}