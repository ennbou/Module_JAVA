package app;

import javafx.application.Application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class Produit {
    private long code;
    private String designation;
    private double prix;

    public Produit(Long code, String designation, double prix) {
        this.code = code;
        this.designation = designation;
        this.prix = prix;
    }

    public Long getCode() {
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

    Produit find(Long id);

    void create(Produit obj);

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

class DataConnection {
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

public class App extends Application {

    static double WIDTH = 800;
    static double HEIGHT = 500;

    public static void main(String[] args) {
        System.out.println("Starting App");
        launch();
    }

    @Override
    public void start(Stage windows) throws Exception {
        windows.setTitle("Gestion Produit version 0.1.45 FX");
        ProduitDAO produitGestion = new ProduitDAOIMPL();

        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout, WIDTH, HEIGHT);

        StackPane titleLyt = new StackPane();
        titleLyt.setPrefSize(WIDTH, 50);
        titleLyt.setStyle("-fx-text-fill: white;-fx-background-color: cornflowerblue;");
        Text title = new Text("gestion de produits");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("Verdana", 20));
        title.setStyle("-fx-text-fill: white;");
        titleLyt.getChildren().add(title);

        VBox layoutBtn = new VBox(); // pour les buttons
        GridPane layoutFld = new GridPane();

        ObservableList<Produit> data = FXCollections.observableArrayList();
        for(Produit p : produitGestion.findAll()){
            data.add(p);
        }

        TableView<Produit> layoutTabl = new TableView<>(data);

        TableColumn<Produit, Long> codeCol = new TableColumn<Produit, Long>("Designation");
        codeCol.setCellValueFactory(new Callback<CellDataFeatures<Produit, Long>, ObservableValue<Long>>() {
            public ObservableValue<Long> call(CellDataFeatures<Produit, Long> p) {
                return new SimpleLongProperty(p.getValue().getCode()).asObject();
            }
        });

        TableColumn<Produit, String> designationCol = new TableColumn<Produit, String>("Designation");
        designationCol.setCellValueFactory(new Callback<CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Produit, String> p) {
                return new SimpleStringProperty(p.getValue().getDesignation());
            }
        });

        TableColumn<Produit, Double> prixCol = new TableColumn<Produit, Double>("Designation");
        prixCol.setCellValueFactory(new Callback<CellDataFeatures<Produit, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(CellDataFeatures<Produit, Double> p) {
                return new SimpleDoubleProperty(p.getValue().getPrix()).asObject();
            }
        });

        layoutTabl.getColumns().add(codeCol);
        layoutTabl.getColumns().add(designationCol);
        layoutTabl.getColumns().add(prixCol);

        layoutBtn.setAlignment(Pos.BASELINE_CENTER);
        layoutBtn.setPrefWidth(WIDTH / 4);
        layoutBtn.setMinWidth(100.0);
        layoutBtn.setSpacing(10);

        layoutFld.setPrefWidth(WIDTH / 2);
        layoutFld.setMinWidth(200);
        layoutFld.setHgap(10);
        layoutFld.setVgap(10);
        layoutFld.setPadding(new Insets(10, 20, 20, 10));

        Button ajouter = new Button("ajouter");
        Button modifier = new Button("modifier");
        Button supprimer = new Button("supprimer");

        ajouter.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        modifier.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        supprimer.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        ajouter.setFont(Font.font("Verdana", 16));
        modifier.setFont(Font.font("Verdana", 16));
        supprimer.setFont(Font.font("Verdana", 16));

        ajouter.setStyle(
                "-fx-text-fill: #f5f5f5; -fx-background-color: cornflowerblue;-fx-padding: 5px;-fx-border-radius: 0;-fx-box-sizing: border-box");
        modifier.setStyle(
                "-fx-text-fill: #f5f5f5; -fx-background-color: cornflowerblue;-fx-padding: 5px;-fx-border-radius: 0;-fx-box-sizing: border-box");
        supprimer.setStyle(
                "-fx-text-fill: #f5f5f5; -fx-background-color: cornflowerblue;-fx-padding: 5px;-fx-border-radius: 0;-fx-box-sizing: border-box");

        layoutBtn.getChildren().addAll(ajouter, modifier, supprimer);

        Text idTxt = new Text("id");
        Text designationTxt = new Text("designation");
        Text prixTxt = new Text("prix");

        TextField idFld = new TextField();
        TextField designationFld = new TextField();
        TextField prixFld = new TextField();

        ajouter.setOnAction(e -> {
            System.out.println(idFld.getText());
            System.out.println(designationFld.getText());
            System.out.println(prixFld.getText());

            produitGestion.create(new Produit(Long.parseLong(idFld.getText()), designationFld.getText(),
                    Double.parseDouble(prixFld.getText())));
        });

        idTxt.setFont(Font.font("Verdana", 16));
        designationTxt.setFont(Font.font("Verdana", 16));
        prixTxt.setFont(Font.font("Verdana", 16));

        layoutFld.add(idTxt, 0, 0);
        layoutFld.add(designationTxt, 0, 1);
        layoutFld.add(prixTxt, 0, 2);

        layoutFld.add(idFld, 1, 0);
        layoutFld.add(designationFld, 1, 1);
        layoutFld.add(prixFld, 1, 2);

        layout.setTop(titleLyt);
        layout.setLeft(layoutBtn);
        layout.setCenter(layoutFld);
        layout.setRight(layoutTabl);

        windows.setScene(scene);
        windows.show();

    }
}