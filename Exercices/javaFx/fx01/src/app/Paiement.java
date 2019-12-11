package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Paiement {
    private int id;
    private long idVente;
    private int num;
    private double montant;
    private String date;
    private String type;

    public Paiement(int id, long idVente, int num, double montant, String date, String type) {
        this.id = id;
        this.idVente = idVente;
        this.num = num;
        this.montant = montant;
        this.date = date;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdVente() {
        return idVente;
    }

    public void setIdVente(long idVente) {
        this.idVente = idVente;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

interface PaiementDAO {

    static final String SQL_SELECT = "SELECT * from paiement";
    static final String SQL_SELECT_ID_V = "SELECT * from paiement WHERE idVente = ?";
    static final String SQL_SELECT_ID = "SELECT * from paiement WHERE id = ?";
    static final String SQL_INSERT = "INSERT INTO paiement VALUES(null,?,?,?,?,?)";
    static final String SQL_UPDATE = "UPDATE paiement SET designation = ? , prix = ? WHERE id = ?";

    Paiement find(int id);

    Boolean create(Paiement obj);

    List<Paiement> findAll(int idVente);

    double getTotal();

}

class PaiementDAOIMPL implements PaiementDAO {
    private static Connection connection = null;
    @SuppressWarnings("unused")
    private Statement stm;
    private PreparedStatement prStm;
    public static Double total;

    @Override
    public Paiement find(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean create(Paiement obj) {
        System.out.println("ennbou");
        try {
            if (connection == null)
                connection = DataConnection.getC();
            prStm = connection.prepareStatement(SQL_INSERT);
            prStm.setLong(1, obj.getIdVente());
            prStm.setInt(2, obj.getNum());
            prStm.setDouble(3, obj.getMontant());
            prStm.setString(4, obj.getDate());
            prStm.setString(5, obj.getType());

            return prStm.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("not add");
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public List<Paiement> findAll(int idVente) {
        List<Paiement> list = new ArrayList<>();
        System.out.println("..");
        try {
            connection = DataConnection.getC();
            prStm = connection.prepareStatement(SQL_SELECT_ID_V);
            prStm.setInt(1, idVente);
            ResultSet r = prStm.executeQuery();
            total = 0.0;
            while (r.next()) {
                list.add(new Paiement(r.getInt("id"), (long) idVente, r.getInt("num"), r.getDouble("montant"),
                        r.getString("date"), r.getString("type")));
                total += r.getDouble("montant");
                // (int id, long idVente, int num, double montant, String date, String type)
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    @Override
    public double getTotal() {
        return total;
    }
}

final class DataConnection {
    private static Connection c = null;
    private static final String url = "jdbc:mysql://127.2.2.2/test_db?useSSL=false";
    private static final String user = "ennbou";
    private static final String password = "ennbou10";

    public static Connection getC() throws SQLException {
        if (c == null) {
            c = DriverManager.getConnection(url, user, password);
        }
        return c;
    }
}