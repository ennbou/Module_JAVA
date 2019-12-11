package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static final String TITLE_WIND = "Gestion Produit version 0.1.45 FX";

    public static void main(String[] args) {
        System.out.println("Starting App");
        launch();
    }

    @Override
    public void start(Stage windows) throws Exception {
        windows.setTitle(TITLE_WIND);
        Scene scene = new WindowsV();
        windows.setScene(scene);
        windows.show();

    }
}