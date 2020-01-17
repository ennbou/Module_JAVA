package fx;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import app.Commande;
import app.Ingredient;
import app.IngredientDAO;
import app.IngredientDAOIMPL;
import app.IngredientPlat;
import app.PlatPrincipal;
import app.Repa;
import app.Supplement;
import app.SupplementDAO;
import app.SupplementDAOIMPL;
import javafx.beans.Observable;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Plat extends Stage {

    static final double WIDTH = 800;
    static final double HIEGHT = 600;

    private PlatPrincipal plat;

    VBox layout;
    Scene scene;
    Text title;

    HBox l1;
    HBox l2;
    Button save;

    IngredientDAO ingredDAO;
    SupplementDAO supplDao;

    private ObservableList<IngredientPlat> data1;
    private ObservableList<Supplement2> data2;

    SimpleDoubleProperty total;
    private TextField tot;
    NumberBinding binding;

    Map<String, Supplement2> supps = new HashMap<>();

    // -------
    private VBox layout1;
    private Text title1;
    private VBox form1;
    private TextField code1;
    private TextField quantite1;
    Button ajouter1;
    // --------

    TableView<IngredientPlat> table1;
    TableColumn<IngredientPlat, String> ing1;
    TableColumn<IngredientPlat, Integer> qte1;
    TableColumn<IngredientPlat, Double> prix1;

    // -------
    private VBox layout2;
    private Text title2;
    private VBox form2;
    private TextField code2;
    private TextField quantite2;
    Button ajouter2;

    // --------

    TableView<Supplement2> table2;
    TableColumn<Supplement2, String> supp2;
    TableColumn<Supplement2, Integer> qte2;
    TableColumn<Supplement2, Double> prix2;

    public Plat(PlatPrincipal plat) {
        this.plat = plat;
        VBox layout = new VBox();
        Scene scene = new Scene(layout, WIDTH, HIEGHT);
        setScene(scene);

        getData();
        ing();
        supp();
        title = new Text(plat.getNom());

        tot = new TextField("0");
        tot.setEditable(false);

        total = new SimpleDoubleProperty();

        // total.bind(data1.get);

        scene.getStylesheets().add("fx/style.css");
        StackPane title = new StackPane();
        title.getChildren().add(this.title);
        title.getStyleClass().add("title-layout");
        layout.getChildren().add(title);
        save = new Button("save");
        layout.getChildren().add(l1);
        layout.getChildren().add(l2);
        layout.getChildren().add(save);
        layout.getChildren().add(tot);

        actions();
    }

    void getData() {
        ingredDAO = new IngredientDAOIMPL();
        supplDao = new SupplementDAOIMPL();
    }

    public void ing() {
        layout1 = new VBox();
        title1 = new Text("Ajouter un Ing");
        form1 = new VBox();
        code1 = new TextField();
        quantite1 = new TextField();
        ajouter1 = new Button("Ajouter");

        Label la1 = new Label("Le Code");
        Label la2 = new Label("La Quantite");
        la1.getStyleClass().addAll("w-200", "f-14");
        la2.getStyleClass().addAll("w-200", "f-14");
        HBox code1 = new HBox();
        HBox quantite1 = new HBox();
        code1.getChildren().addAll(la1, this.code1);
        quantite1.getChildren().addAll(la2, this.quantite1);

        layout1.getChildren().addAll(title1, code1, quantite1, ajouter1);

        data1 = FXCollections.observableArrayList();

        // data1.addAll(new IngredientPlat(250, ingredDAO.getOne("01")), new
        // IngredientPlat(1, ingredDAO.getOne("02")));

        l1 = new HBox();
        table1 = new TableView<IngredientPlat>(data1);
        ing1 = new TableColumn<>("Inredient");
        qte1 = new TableColumn<>("Qte");
        prix1 = new TableColumn<>("Prix");

        ing1.setCellValueFactory(i -> new SimpleStringProperty(i.getValue().getIngredient().getNom()));
        qte1.setCellValueFactory(i -> (new SimpleIntegerProperty(i.getValue().getQuantite())).asObject());
        prix1.setCellValueFactory(i -> i.getValue().totalProperty().asObject());

        table1.getColumns().add(ing1);
        table1.getColumns().add(qte1);
        table1.getColumns().add(prix1);

        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        l1.getChildren().addAll(layout1, table1);
    }

    public void supp() {
        layout2 = new VBox();
        title2 = new Text("Ajouter un Supp");
        form2 = new VBox();
        code2 = new TextField();
        quantite2 = new TextField();
        ajouter2 = new Button("Ajouter");

        Label la1 = new Label("Le Code");
        Label la2 = new Label("La Quantite");
        la1.getStyleClass().addAll("w-200", "f-14");
        la2.getStyleClass().addAll("w-200", "f-14");
        HBox code2 = new HBox();
        HBox quantite2 = new HBox();
        code2.getChildren().addAll(la1, this.code2);
        quantite2.getChildren().addAll(la2, this.quantite2);

        layout2.getChildren().addAll(title2, code2, quantite2, ajouter2);

        data2 = FXCollections.observableArrayList(s -> new Observable[] {
                new SimpleStringProperty(s.getSupp().getNom()), s.qteProperty(), s.totalProperty() });
        // data2.addAll(supplDao.getOne("01"), supplDao.getOne("02"));
        // supps.put("01", new Supplement2(supplDao.getOne("01"), 1));
        data2.addAll(supps.values());
        l2 = new HBox();
        table2 = new TableView<>(data2);
        supp2 = new TableColumn<>("Supplement");
        qte2 = new TableColumn<>("QTE");
        prix2 = new TableColumn<>("Prix");

        supp2.setCellValueFactory(i -> new SimpleStringProperty(i.getValue().getSupp().getNom()));
        qte2.setCellValueFactory(i -> i.getValue().qteProperty().asObject());
        prix2.setCellValueFactory(i -> i.getValue().totalProperty().asObject());

        table2.getColumns().add(supp2);
        table2.getColumns().add(qte2);
        table2.getColumns().add(prix2);

        table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        l2.getChildren().addAll(layout2, table2);
    }

    void actions() {

        ajouter1.setOnMouseClicked(e -> {
            if (code1.getText().isEmpty() || quantite1.getText().isEmpty())
                return;
            String code = code1.getText();
            int qte = Integer.parseInt(quantite1.getText());
            Ingredient i = ingredDAO.getOne(code);
            if (i == null)
                return;
            IngredientPlat ip = new IngredientPlat(qte, i);
            data1.add(ip);
            if (binding == null) {
                // binding.add(ip.getIngredient().getPrixUnitaire());
                binding = ip.totalProperty().add(0);
            } else {
                binding = binding.add(ip.totalProperty());
            }
            //
            total.bind(binding);
            tot.setText(total.get() + " DH");
        });

        ajouter2.setOnMouseClicked(e -> {
            if (code2.getText().isEmpty() || quantite2.getText().isEmpty())
                return;
            String code = code2.getText();
            int qte = Integer.parseInt(quantite2.getText());
            Supplement s = supplDao.getOne(code);

            if (s == null)
                return;
            Supplement2 ss;
            if (supps.containsKey(s.getCode())) {
                ss = supps.get(s.getCode());
                if (ss == null)
                    return;
                ss.setQte(qte + ss.getQte());
            } else {
                ss = new Supplement2(s, qte);
                supps.put(code, ss);
                if (binding == null) {
                    binding = ss.totalProperty().add(0);
                } else {
                    binding = binding.add(ss.totalProperty());
                }
                total.bind(binding);
            }
            data2.setAll(supps.values());

            tot.setText(total.get() + " DH");
        });

        save.setOnMouseClicked(e -> {
            // Repa r = new Repa(1);
            // r.setPlatPrincipal(plat);
            // for (IngredientPlat ing : data1) {
            // plat.addIngedient(ing);
            // }
            // for (Supplement2 s : data2) {
            // r.addSupplement(s.getSupp());
            // }
            // Commande c = new Commande(1);

            try {
                PreparedStatement stm = Connection.getConnection()
                        .prepareStatement("INSERT INTO test VALUES(null, ? , ?)");
                        stm.setString(1, 1+"");
                        stm.setDouble(2, total.get());
                    stm.execute();

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });
    }

}

class Supplement2 {
    private Supplement supp;
    private SimpleIntegerProperty qte;
    private SimpleDoubleProperty total;

    public Supplement2(Supplement supp, int qte) {
        this.supp = supp;
        this.qte = new SimpleIntegerProperty(qte);
        total = new SimpleDoubleProperty();
        total.bind(qteProperty().multiply(supp.getPrix()));
    }

    public IntegerProperty qteProperty() {
        return qte;
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public Supplement getSupp() {
        return supp;
    }

    public void setQte(int qte) {
        this.qte.set(qte);
    }

    public int getQte() {
        return qte.get();
    }
}