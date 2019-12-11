package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Produit {
    private long code;
    private String designation;
    private double prix;

    public Produit(Long code, String designation, double prix) {
        this.code = code;
        this.designation = designation;
        this.prix = prix;
    }

    public long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

}

interface ProduitDAO {

    static final String SQL_SELECT = "SELECT * from table1";
    static final String SQL_SELECT_ID = "SELECT * from table1 WHERE id = ?";
    static final String SQL_INSERT = "INSERT INTO table1 VALUES(null,?,?)";
    static final String SQL_SELECT_NOM = "SELECT * FROM table WHERE designiation = ?";
    static final String SQL_UPDATE = "UPDATE table1 SET designation = ? , prix = ? WHERE id = ?";
    static final String SQL_DELETE = "DELETE FROM table1  WHERE id = ?";

    Produit find(Long id);

    void create(Produit obj);

    Boolean modifier(long code, String designation, double prix);

    Boolean supprime(long code);

    List<Produit> findAll();

    List<Produit> findAll(String key);
}

class ProduitDAOIMPL implements ProduitDAO {
    private static Connection connection = null;
    @SuppressWarnings("unused")
    private Statement stm;
    private PreparedStatement prStm;

    @Override
    public Produit find(Long id) {
        try {
            connection = DataConnection.getC();
            prStm = connection.prepareStatement(SQL_SELECT_ID);
            prStm.setLong(1, id);
            ResultSet r = prStm.executeQuery();
            if (r.next()) {
                return new Produit(r.getLong(1), r.getString(2), r.getDouble(3));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void create(Produit obj) {
        System.out.println("ennbou");
        try {
            if (connection == null)
                connection = DataConnection.getC();
            prStm = connection.prepareStatement(SQL_INSERT);
            prStm.setString(1, obj.getDesignation());
            prStm.setDouble(2, obj.getPrix());
            if (!prStm.execute())
                System.out.println("Produit ADD");

        } catch (Exception e) {
            System.out.println("hh");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean modifier(long code, String designation, double prix) {
        try {
            connection = DataConnection.getC();
            prStm = connection.prepareStatement(SQL_UPDATE);
            prStm.setString(1, designation);
            prStm.setDouble(2, prix);
            prStm.setLong(3, code);
            return (prStm.executeUpdate() == 1) ? true : false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean supprime(long code) {
        try {
            connection = DataConnection.getC();
            prStm = connection.prepareStatement(SQL_DELETE);
            prStm.setLong(1, code);
            return (prStm.executeUpdate() == 1) ? true : false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Produit> findAll() {
        List<Produit> list = new ArrayList<>();
        try {
            connection = DataConnection.getC();
            prStm = connection.prepareStatement(SQL_SELECT);
            ResultSet r = prStm.executeQuery();
            while (r.next()) {
                list.add(new Produit(r.getLong(1), r.getString(2), r.getDouble(3)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Produit> findAll(String key) {
        List<Produit> list = new ArrayList<>();
        try {
            connection = DataConnection.getC();
            prStm = connection.prepareStatement(SQL_SELECT_NOM);
            prStm.setString(1, key);
            ResultSet r = prStm.executeQuery();
            while (r.next()) {
                list.add(new Produit(r.getLong(1), r.getString(2), r.getDouble(3)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

}
