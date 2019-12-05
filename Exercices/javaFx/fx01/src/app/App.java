package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    static double WIDTH = 800;
    static double HEIGHT = 500;
    private static final String TITLE_WIND = "Gestion Produit version 0.1.45 FX";

    public static void t() {

    }

    public static void main(String[] args) {
        System.out.println("Starting App");
        launch();
    }

    @Override
    public void start(Stage windows) throws Exception {
        windows.setTitle(TITLE_WIND);
        // ProduitDAO produitGestion = new ProduitDAOIMPL();

        // BorderPane layout = new BorderPane();
        // Scene scene = new Scene(layout, WIDTH, HEIGHT);

        // StackPane titleLyt = new StackPane();
        // titleLyt.setPrefSize(WIDTH, 50);
        // titleLyt.setStyle("-fx-text-fill: white;-fx-background-color: cornflowerblue;");
        // Text title = new Text("gestion de produits");
        // title.setTextAlignment(TextAlignment.CENTER);
        // title.setFont(Font.font("Verdana", 20));
        // title.setStyle("-fx-text-fill: white;");
        // titleLyt.getChildren().add(title);

        // VBox layoutBtn = new VBox(); // pour les buttons
        // VBox layoutS = new VBox();
        // GridPane layoutFld = new GridPane();

        // ObservableList<Produit> data = FXCollections.observableArrayList();
        // for (Produit p : produitGestion.findAll()) {
        //     data.add(p);
        // }

        // TableView<Produit> layoutTabl = new TableView<>(data);

        // TableColumn<Produit, Long> codeCol = new TableColumn<Produit, Long>("Designation");
        // TableColumn<Produit, String> designationCol = new TableColumn<Produit, String>("Designation");
        // TableColumn<Produit, Double> prixCol = new TableColumn<Produit, Double>("Designation");

        // codeCol.setCellValueFactory(new PropertyValueFactory<Produit, Long>("code"));
        // designationCol.setCellValueFactory(new PropertyValueFactory<Produit, String>("designation"));
        // prixCol.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));

        // layoutTabl.getColumns().add(codeCol);
        // layoutTabl.getColumns().add(designationCol);
        // layoutTabl.getColumns().add(prixCol);

        // layoutBtn.setAlignment(Pos.BASELINE_CENTER);
        // layoutBtn.setPrefWidth(WIDTH / 4);
        // layoutBtn.setMinWidth(100.0);
        // layoutBtn.setSpacing(10);

        // layoutFld.setPrefWidth(WIDTH / 2);
        // layoutFld.setMinWidth(200);
        // layoutFld.setHgap(10);
        // layoutFld.setVgap(10);
        // layoutFld.setPadding(new Insets(10, 20, 20, 10));

        // Button ajouter = new Button("ajouter");
        // Button modifier = new Button("modifier");
        // Button supprimer = new Button("supprimer");

        // ajouter.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        // modifier.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        // supprimer.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // ajouter.setFont(Font.font("Verdana", 16));
        // modifier.setFont(Font.font("Verdana", 16));
        // supprimer.setFont(Font.font("Verdana", 16));

        // ajouter.setStyle(
        //         "-fx-text-fill: #f5f5f5; -fx-background-color: cornflowerblue;-fx-padding: 5px;-fx-border-radius: 0;-fx-box-sizing: border-box");
        // modifier.setStyle(
        //         "-fx-text-fill: #f5f5f5; -fx-background-color: cornflowerblue;-fx-padding: 5px;-fx-border-radius: 0;-fx-box-sizing: border-box");
        // supprimer.setStyle(
        //         "-fx-text-fill: #f5f5f5; -fx-background-color: cornflowerblue;-fx-padding: 5px;-fx-border-radius: 0;-fx-box-sizing: border-box");

        // layoutBtn.getChildren().addAll(ajouter, modifier, supprimer);

        // TextField searchFld = new TextField();

        // layoutS.getChildren().addAll(searchFld, layoutTabl);

        // Text idTxt = new Text("id");
        // Text designationTxt = new Text("designation");
        // Text prixTxt = new Text("prix");

        // TextField idFld = new TextField();
        // TextField designationFld = new TextField();
        // TextField prixFld = new TextField();

        // ajouter.setOnAction(e -> {
        //     System.out.println(idFld.getText());
        //     System.out.println(designationFld.getText());
        //     System.out.println(prixFld.getText());

        //     Produit p = new Produit(Long.parseLong(idFld.getText()), designationFld.getText(),
        //             Double.parseDouble(prixFld.getText()));
        //     data.add(p);
        //     produitGestion.create(p);
        // });

        // FilteredList<Produit> listFilter = new FilteredList<>(data, p -> true);
        // searchFld.textProperty().addListener((observable, oldValue, newValue) -> {
        //     listFilter.setPredicate(produit -> {
        //         if (newValue == null || newValue.isEmpty()) {
        //             return true;
        //         }
        //         String lowerCaseFilter = newValue.toLowerCase();
        //         if (produit.getDesignation().toLowerCase().contains(lowerCaseFilter))
        //             return true;
        //         return false;
        //     });
        //     layoutTabl.setItems(listFilter);
        // });

        // idTxt.setFont(Font.font("Verdana", 16));
        // designationTxt.setFont(Font.font("Verdana", 16));
        // prixTxt.setFont(Font.font("Verdana", 16));

        // layoutFld.add(idTxt, 0, 0);
        // layoutFld.add(designationTxt, 0, 1);
        // layoutFld.add(prixTxt, 0, 2);

        // layoutFld.add(idFld, 1, 0);
        // layoutFld.add(designationFld, 1, 1);
        // layoutFld.add(prixFld, 1, 2);

        // layout.setTop(titleLyt);
        // layout.setLeft(layoutBtn);
        // layout.setCenter(layoutFld);
        // layout.setRight(layoutS);
        Scene scene = new WindowsP();
        windows.setScene(scene);
        windows.show();

    }
}