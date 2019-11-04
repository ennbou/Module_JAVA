package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class GestionClient {
    private static List<Client> clients;

    // JDBC
    private static Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    // IO
    private static Scanner scanner;

    private Client client = null;

    public GestionClient() {
        scanner = new Scanner(System.in);
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.2.2.2/test_db?useSSL=false", "ennbou",
                    "ennbou10");
            statement = connection.createStatement();
        } catch (Exception e) {

        }
    }

    public GestionClient(int idex) {
        GestionClient();
        client = getClientById(index);
    }

    public void createClient() {
        if (client != null)
            return;
        client = new Client(scanner.nextInt(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine(),
                scanner.nextLine());

    }

    public void insert() {
        if (client == null)
            return;
        preparedStatement = connection.prepareStatement("INSERT INTO client VALUES(?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, client.getId());
        preparedStatement.setString(2, client.getNom());
        preparedStatement.setString(3, client.getPrenom());
        preparedStatement.setString(4, client.getTele());
        preparedStatement.setString(5, client.getEmail());

        if (preparedStatement.executeUpdate()) {
            System.out.println("le client " + client.getId + " inserie dans la base de donnee");
        } else {
            System.out.println("il y a un probleme de l'insertion de cilent " + client.getEmail());
        }
    }

    public void modifier(){
        
    }

    public Client getClientById(int index) {
        preparedStatement = connection.prepareStatement("SELECT * FROM client WHERE id = ?");
        preparedStatement.setInt(1, index);
        resultSet = preparedStatement.executeQuery();
        return new Client(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"),
                resultSet.getString("tele"), resultSet.getString("email"));
    }

}