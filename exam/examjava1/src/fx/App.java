package fx;

import java.util.Map;

import app.Data;
import app.PlatPrincipal;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        FlowPane flow = new FlowPane();
        Scene scene = new Scene(flow);

        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setVgap(4);
        flow.setHgap(4);
        // flow.setPrefWrapLength(300);

        Data data = Data.getInstance();

        Map<String, PlatPrincipal> plats = data.getDataPlats();

        for (Map.Entry<String, PlatPrincipal> plat : plats.entrySet()) {
            Button t = new Button(plat.getValue().getNom());
            t.setStyle(
                    "-fx-font-size: 14px;-fx-font-weight: 700;-fx-background-color: darkslateblue;-fx-text-fill: white;-fx-pref-width: 300");
            flow.getChildren().add(t);
            t.setOnAction(e -> {
                Stage s = new Plat(plat.getValue());
                s.showAndWait();
            });
        }

        window.setScene(scene);

        window.show();
    }

}