import java.sql.*;

public class Test {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.2.2.2:3306/test_db?useSSL=false", "ennbou", "ennbou10");
            Statement stm = c.createStatement();
            ResultSet r = stm.executeQuery("SELECT * from table1");
            r.next();
            System.out.println(r.getString("name"));
        } catch (Exception e) {
            // System.out.println(e.getStackTrace);
        }
    }
}