package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class WindowsP extends Scene {

    private static final double WIDTH_SCENE = 800.0;
    private static final double HEIGHT_SCENE = 500.0;

    private BorderPane layout;
    private StackPane layoutTitle;
    private VBox layoutS;
    private VBox layoutBtn;
    private GridPane layoutFld;

    private Button ajouter;
    private Button modifier;
    private Button supprimer;

    private Text title;
    private Text codeTxt;
    private Text designationTxt;
    private Text prixTxt;

    private TextField searchFld;
    private TextField codeFld;
    private TextField designationFld;
    private TextField prixFld;

    private ProduitDAO produitGestion;

    private ObservableList<Produit> data;
    private TableView<Produit> layoutTabl;
    private TableColumn<Produit, Long> codeCol;
    private TableColumn<Produit, String> designationCol;
    private TableColumn<Produit, Double> prixCol;
    private FilteredList<Produit> listFilter;

    public WindowsP() {
        super(new BorderPane(), WIDTH_SCENE, HEIGHT_SCENE);
        layout = (BorderPane) getRoot();
        initVars();
        structLayouts();
        stylingNode();
        setActions();
    }

    public void initVars() {
        title = new Text("gestion de produits");
        layoutTitle = new StackPane();
        layoutBtn = new VBox();
        layoutS = new VBox();
        layoutFld = new GridPane();

        ajouter = new Button("ajouter");
        modifier = new Button("modifier");
        supprimer = new Button("supprimer");

        codeTxt = new Text("id");
        designationTxt = new Text("designation");
        prixTxt = new Text("prix");

        codeFld = new TextField();
        designationFld = new TextField();
        prixFld = new TextField();

        searchFld = new TextField();

        data = FXCollections.observableArrayList();
        layoutTabl = new TableView<>(data);

        codeCol = new TableColumn<Produit, Long>("Code");
        designationCol = new TableColumn<Produit, String>("Designation");
        prixCol = new TableColumn<Produit, Double>("Prix");

        codeCol.setCellValueFactory(new PropertyValueFactory<Produit, Long>("code"));
        designationCol.setCellValueFactory(new PropertyValueFactory<Produit, String>("designation"));
        prixCol.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));

        codeCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        prixCol.setStyle("-fx-alignment: CENTER-RIGHT;");

        ProduitDAO produitGestion = new ProduitDAOIMPL();

        data.addAll(produitGestion.findAll());

        layoutTabl.setEditable(true);
        codeCol.setEditable(true);
    }

    public void stylingNode() {

        getStylesheets().add("app/style/style.css");

        title.getStyleClass().add("title");

        layoutBtn.setPrefWidth(WIDTH_SCENE / 4);
        layoutBtn.setMinWidth(120);
        layoutBtn.setFillWidth(true);

        ajouter.getStyleClass().add("btn");
        modifier.getStyleClass().add("btn");
        supprimer.getStyleClass().add("btn");

        layoutBtn.setSpacing(10);
        layoutBtn.setAlignment(Pos.BASELINE_CENTER);

        layoutBtn.getStyleClass().add("layoutBtn");

        layoutFld.setHgap(10);
        layoutFld.setVgap(10);
        layoutFld.setPadding(new Insets(20, 5, 20, 5));

        title.setFont(Font.font("arial", 14));

        codeTxt.getStyleClass().add("codetxt");
        designationTxt.getStyleClass().add("designationtxt");
        prixTxt.getStyleClass().add("prixtxt");

        layoutTitle.setBackground(
                new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        layoutTitle.setPrefHeight(80);
        title.setFill(Color.WHITE);

    }

    public void structLayouts() {
        layoutTitle.getChildren().add(title);

        layoutBtn.getChildren().addAll(ajouter, modifier, supprimer);

        layoutS.getChildren().addAll(searchFld, layoutTabl);

        layoutFld.add(codeTxt, 0, 0);
        layoutFld.add(designationTxt, 0, 1);
        layoutFld.add(prixTxt, 0, 2);

        layoutFld.add(codeFld, 1, 0);
        layoutFld.add(designationFld, 1, 1);
        layoutFld.add(prixFld, 1, 2);

        layout.setTop(layoutTitle);
        layout.setLeft(layoutBtn);
        layout.setCenter(layoutFld);
        layout.setRight(layoutS);

        layoutTabl.getColumns().add(codeCol);
        layoutTabl.getColumns().add(designationCol);
        layoutTabl.getColumns().add(prixCol);
    }

    public void setActions() {
        ajouter.setOnAction(e -> {
            Produit p = new Produit(Long.parseLong(codeFld.getText()), designationFld.getText(),
                    Double.parseDouble(prixFld.getText()));
            data.add(p);
            produitGestion.create(p);
        });

        FilteredList<Produit> listFilter = new FilteredList<>(data);
        searchFld.textProperty().addListener((observable, oldValue, newValue) -> {
            listFilter.setPredicate(produit -> newValue.isEmpty() || produit.getDesignation().toLowerCase().contains(newValue.toLowerCase()));
            layoutTabl.setItems(listFilter);
        });
    }

}