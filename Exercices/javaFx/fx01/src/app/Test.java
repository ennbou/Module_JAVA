package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;;

public class Test {

    private static Connection c = null;
    private static final String url = "jdbc:mysql://127.2.2.2/test_db?useSSL=false";
    private static final String user = "ennbou";
    private static final String password = "ennbou10";

    public static void main(String[] args) {
        try {
            c = DriverManager.getConnection(url, user, password);
            PreparedStatement prStm = c.prepareStatement("INSERT INTO table1 VALUES(null,'vvv',100)",
                    Statement.RETURN_GENERATED_KEYS);
            prStm.executeUpdate();
            ResultSet r = prStm.getGeneratedKeys();

            while (r.next()) {
                int last = r.getInt(1);
                System.out.println("last id : " + last);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
