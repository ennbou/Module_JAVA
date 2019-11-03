package app;

import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.2.2.2/test_db?useSSL=false", "ennbou", "ennbou10");
            DriverManager.println("first");
            Statement stm = c.createStatement();
            DriverManager.println("second");
            ResultSet r = stm.executeQuery("SELECT * FROM table1");
            DriverManager.println("therd");
            if(r.next()){
                System.out.println(r.getString("name"));
                DriverManager.println("fourth");
                System.out.println("ennd");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}