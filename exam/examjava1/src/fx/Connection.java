package fx;

import java.sql.DriverManager;

public final class Connection {
    private static java.sql.Connection connection;
    private static final String URL = "jdbc:mysql://127.2.2.2/gestion_magasin?useSSL=false";
    private static final String USER = "ennbou";
    private static final String PASSWORD = "ennbou10";

    public static java.sql.Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return connection;
    }
}