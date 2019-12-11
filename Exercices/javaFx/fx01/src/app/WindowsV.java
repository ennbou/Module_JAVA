package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WindowsV extends Scene {

    private static final double WIDTH_SCENE = 800.0;
    private static final double HEIGHT_SCENE = 500.0;

    private VBox layout;
    private StackPane layoutTitle;
    private VBox layoutLeft;
    private VBox layoutRight;
    private VBox layout3;
    private HBox layoutBtn;
    private HBox subLayout;
    private HBox layoutCodeVente;
    private HBox layoutTotalPay;
    private HBox layoutTotal;
    private HBox layoutReste;
    private GridPane layoutFld;

    private Button enregistrer;
    private Button annuuler;

    private Text title;
    private Text codeVenteTxt;
    private Text dateVenteTxt;
    private Text totalpayTxt;
    private Text totalpayTxt1;
    private Text totalTxt;
    private Text totalTxt1;
    private Text resteTxt;
    private Text restTxt1;
    private Text crTxt;
    private Text nTxt;
    private Text dateTxt;
    private Text montantTxt;
    private Text typeTxt;

    private TextField codeVenteFld;
    private TextField nFld;
    private TextField dateFld;
    private TextField montantFld;
    private TextField typeFld;

    private PaiementDAO paiementGestion;

    private ObservableList<Paiement> data;
    private TableView<Paiement> layoutTabl;
    private TableColumn<Paiement, String> dateCol;
    private TableColumn<Paiement, Integer> nCol;
    private TableColumn<Paiement, Double> montantCol;
    private TableColumn<Paiement, String> typeCol;

    public WindowsV() {
        super(new VBox(), WIDTH_SCENE, HEIGHT_SCENE);
        layout = (VBox) getRoot();
        paiementGestion = new PaiementDAOIMPL();
        initVars();
        structLayouts();
        stylingNode();
        setActions();
    }

    public void initVars() {

        layoutTitle = new StackPane();

        subLayout = new HBox();

        layoutLeft = new VBox();
        layoutRight = new VBox();
        layout3 = new VBox();
        layoutBtn = new HBox();
        layoutCodeVente = new HBox();
        layoutTotalPay = new HBox();
        layoutTotal = new HBox();
        layoutReste = new HBox();

        layoutFld = new GridPane();

        title = new Text("gestion de paiement");
        codeVenteTxt = new Text("Paiement de la Vente N: ");
        dateVenteTxt = new Text("Du: 10/09/2019");
        totalpayTxt = new Text("Total paye: ");
        totalpayTxt1 = new Text("");
        totalTxt = new Text("Total: ");
        totalTxt1 = new Text("");
        resteTxt = new Text("Reste: ");
        restTxt1 = new Text("");
        crTxt = new Text("Creeer paiement");
        nTxt = new Text("N: ");
        dateTxt = new Text("Date: ");
        montantTxt = new Text("Montant: ");
        typeTxt = new Text("Type: ");

        enregistrer = new Button("enregstre");
        annuuler = new Button("annuler");

        codeVenteFld = new TextField();
        nFld = new TextField();
        dateFld = new TextField();
        montantFld = new TextField();
        typeFld = new TextField();

        data = FXCollections.observableArrayList();
        layoutTabl = new TableView<>(data);

        dateCol = new TableColumn<Paiement, String>("Date");
        nCol = new TableColumn<Paiement, Integer>("N");
        montantCol = new TableColumn<Paiement, Double>("Montant");
        typeCol = new TableColumn<Paiement, String>("Type");

        dateCol.setCellValueFactory(new PropertyValueFactory<Paiement, String>("date"));
        nCol.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("num"));
        montantCol.setCellValueFactory(new PropertyValueFactory<Paiement, Double>("montant"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Paiement, String>("type"));

        PaiementDAO paiementGestion = new PaiementDAOIMPL();

        if (!codeVenteFld.getText().isEmpty())
            data.setAll(paiementGestion.findAll(Integer.parseInt(codeVenteFld.getText())));

        nCol.setEditable(false);
        nFld.setEditable(false);
    }

    public void stylingNode() {

        getStylesheets().add("app/style/style1.css");

        layout.setStyle("-fx-background-color: #ffffff; -fx-spacing: 10");

        subLayout.setStyle("-fx-spacing: 10; -fx-padding: 10");

        layoutRight.setStyle("-fx-spacing: 10;");

        layoutTitle.setStyle("-fx-min-height: 40; -fx-background-color: #263238;");
        title.setStyle("-fx-fill: #ffffff;-fx-font-size: 16px;");
        codeVenteTxt.setStyle("-fx-fill: #FF3D00;-fx-font-size: 16px;");

        dateVenteTxt.setStyle("-fx-fill: #616161;-fx-font-size: 16px; -fx-padding: 15px;-fx-border-insets: 15px;-fx-background-insets: 15px;");

        layoutLeft.setPrefWidth(2 * widthProperty().get() / 3);
        layoutRight.setPrefWidth(widthProperty().get() / 3);
        HBox.setHgrow(enregistrer, Priority.ALWAYS);
        HBox.setHgrow(annuuler, Priority.ALWAYS);;

        layoutTotalPay.getStyleClass().add("totalp-l");

        layoutTotal.getStyleClass().add("total-l");
        layoutReste.getStyleClass().add("total-l");

        layoutBtn.getStyleClass().add("btn-l");

        layout3.setStyle("-fx-background-color: #ffffff; -fx-border-color: #616161; -fx-border-width: 2; -fx-pref-height: 250");

        totalpayTxt.getStyleClass().add("totalp");
        totalTxt.getStyleClass().add("total");
        resteTxt.getStyleClass().add("reste");

        enregistrer.getStyleClass().add("btn");
        annuuler.getStyleClass().add("btn");

        nCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        montantCol.setStyle("-fx-alignment: CENTER-RIGHT;");
    }

    public void structLayouts() {
        layoutTitle.getChildren().add(title);

        subLayout.getChildren().addAll(layoutLeft, layoutRight);

        layoutTotalPay.getChildren().addAll(totalpayTxt, totalpayTxt1);
        layoutTotal.getChildren().addAll(totalTxt, totalTxt1);
        layoutReste.getChildren().addAll(resteTxt, restTxt1);

        layoutCodeVente.getChildren().addAll(codeVenteTxt, codeVenteFld);

        layoutLeft.getChildren().addAll(layoutCodeVente, dateVenteTxt, layoutTabl, layoutTotalPay);

        layoutBtn.getChildren().addAll(enregistrer, annuuler);

        layout3.getChildren().addAll(crTxt, layoutFld, layoutBtn);

        layoutRight.getChildren().addAll(layoutTotal, layoutReste, layout3);

        layoutFld.add(nTxt, 0, 0);
        layoutFld.add(dateTxt, 0, 1);
        layoutFld.add(montantTxt, 0, 2);
        layoutFld.add(typeTxt, 0, 3);

        layoutFld.add(nFld, 1, 0);
        layoutFld.add(dateFld, 1, 1);
        layoutFld.add(montantFld, 1, 2);
        layoutFld.add(typeFld, 1, 3);

        layout.getChildren().add(layoutTitle);
        layout.getChildren().add(subLayout);

        layoutTabl.getColumns().add(dateCol);
        layoutTabl.getColumns().add(nCol);
        layoutTabl.getColumns().add(montantCol);
        layoutTabl.getColumns().add(typeCol);
        layoutTabl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


    }

    public void setActions() {
        enregistrer.setOnAction(e -> {
            Paiement p = new Paiement(Integer.parseInt(nFld.getText()), Long.parseLong(codeVenteFld.getText()),
                    Integer.parseInt(nFld.getText()), Double.parseDouble(montantFld.getText()), dateFld.getText(),
                    typeFld.getText());
            if (paiementGestion.create(p)) {
                data.add(p);
                nFld.clear();
                montantFld.clear();
                typeFld.clear();

            }
        });

        codeVenteFld.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                if (newValue.matches("[1-9][0-9]{0,4}")) {
                    data.setAll(paiementGestion.findAll(Integer.parseInt(newValue)));
                    totalpayTxt1.setText(paiementGestion.getTotal() + "");
                    nFld.setText(data.size() + 1 + "");
                } else {
                    codeVenteFld.setText(oldValue);
                }
            } else {
                clearAll();
            }
        });
    }

    public void clearAll() {
        data.clear();
        nFld.clear();
        dateFld.clear();
        montantFld.clear();
        typeFld.clear();
        totalTxt1.setText("");
        totalpayTxt1.setText("");
        restTxt1.setText("");

    }

}